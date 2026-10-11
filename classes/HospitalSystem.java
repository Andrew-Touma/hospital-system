import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;

public class HospitalSystem {
    ArrayList<Doctor> doctors;
    ArrayList<Patient> patients;
    ArrayList<Appointment> appointments;
    ArrayList<Bill> bills;
    ArrayList<Service> services;

    public HospitalSystem() {
        this.doctors = new ArrayList<>();
        this.patients = new ArrayList<>();
        this.appointments = new ArrayList<>();
        this.bills = new ArrayList<>();
        this.services = new ArrayList<>();
    }

    public void addDoctor(Doctor doctor) throws Exception {
        doctors.add(doctor);
        BufferedWriter bw = new BufferedWriter(new FileWriter("./src/doctor.txt", true));
        String data = doctor.getDoctorId() + "," + doctor.getfullName() + "," + doctor.getSpecialization();
        bw.write(data);
        bw.newLine();
        bw.close();
    }

    public void addPatient(Patient patient) throws Exception {
        patients.add(patient);
        BufferedWriter bw = new BufferedWriter(new FileWriter("./src/patient.txt", true));
        String data = patient.getPatientId() + "," + patient.getfullName() + "," + patient.getPhone();
        bw.write(data);
        bw.newLine();
        bw.close();
    }

    public Doctor findDoctor(int id) {
        for (Doctor x : doctors) {
            if (x.getDoctorId() == id) {
                return x;
            }
        }
        return null;
    }

    public Patient findPatient(int id) {
        for (Patient x : patients) {
            if (x.getPatientId() == id) {
                return x;
            }
        }
        return null;
    }

    public void bookAppointments(int appointmentId, String date, String time, boolean completed, Patient patient,
            Doctor doctor, Service service) throws Exception {
        Appointment a = new Appointment(appointmentId, date, time, false, patient, doctor, service);
        appointments.add(a);
        doctor.addAppointment(a);
        patient.bookAppointment(a);
        services.add(service);

        BufferedWriter bw = new BufferedWriter(new FileWriter("./src/appointment.txt", true));
        String data = a.getAppointmentId() + "," + a.getDoctor().getDoctorId() + "," + a.getPatient().getPatientId()
                + "," + a.getDate() + "," + a.getTime() + "," + a.getService().getServiceName() + ","
                + a.getCompleted();
        bw.write(data);
        bw.newLine();
        bw.close();
    }

    public Bill generateBill(int billId, Patient patient, ArrayList<Service> services) throws Exception {
        Bill b = new Bill(billId, patient, false);
        for (Service s : services) {
            b.addService(s);
            this.services.add(s);
        }
        bills.add(b);
        BufferedWriter bw = new BufferedWriter(new FileWriter("./src/bill.txt", true));
        String data = b.getBillId() + "," + b.getPatient().getPatientId() + "," + b.calculateTotal() + ","
                + b.getPaid();
        bw.write(data);
        bw.newLine();
        bw.close();
        return b;
    }

    public void printAllDoctors() {
        for (Doctor x : doctors) {
            System.out.println("Doctor id : " + x.getDoctorId() + ", name : " + x.getfullName() + ", specialization : "
                    + x.getSpecialization());
        }
    }

    public void filterServices(ServiceFilter filter) {
        for (Service x : services) {
            if (filter.apply(x)) {
                System.out.println(x.getServiceId());
            }

        }
    }

    public void filterAppointments(AppointmentFilter filter) {
        for (Appointment x : appointments) {
            if (filter.apply(x)) {
                System.out.println(x.getAppointmentId());
            }
        }
    }

    public void filterBills(BillFilter filter) {
        for (Bill x : bills) {
            if (filter.apply(x)) {
                System.out.println(x.getBillId());
            }
        }
    }

    public void loadDoctors() throws Exception {

        BufferedReader br = new BufferedReader(
                new FileReader("./src/doctor.txt"));

        String line = br.readLine();

        while (line != null) {

            String[] part = line.split(",");

            int id = Integer.parseInt(part[0]);
            String name = part[1];
            String specialization = part[2];

            Doctor doctor = new Doctor(
                    id,
                    name,
                    specialization);

            doctors.add(doctor);

            line = br.readLine();
        }

        br.close();
    }

    public void loadPatients() throws Exception {

        BufferedReader br = new BufferedReader(
                new FileReader("./src/patient.txt"));

        String line = br.readLine();

        while (line != null) {

            String[] part = line.split(",");

            int id = Integer.parseInt(part[0]);
            String name = part[1];
            String phone = part[2];

            Patient patient = new Patient(
                    id,
                    name,
                    phone);

            patients.add(patient);

            line = br.readLine();
        }

        br.close();
    }

    public void loadAppointments() throws Exception {

        BufferedReader br = new BufferedReader(
                new FileReader("./src/appointment.txt"));

        String line = br.readLine();

        while (line != null) {

            String[] part = line.split(",");

            int appointmentId = Integer.parseInt(part[0]);

            int doctorId = Integer.parseInt(part[1]);

            int patientId = Integer.parseInt(part[2]);

            String date = part[3];
            String time = part[4];

            boolean completed = Boolean.parseBoolean(part[6]);

            Doctor doctor = findDoctor(doctorId);
            Patient patient = findPatient(patientId);

            if (doctor == null || patient == null) {
                System.out.println("Skipping appointment " + appointmentId
                        + ": doctor or patient not found.");
                line = br.readLine();
                continue;
            }

            Service service = new ConsultationService(
                    1,
                    part[5],
                    100);

            if (doctor == null || patient == null) {
                System.out.println("Skipping appointment " + appointmentId
                        + ": doctor or patient not found.");
                line = br.readLine();
                continue;
            }

            Appointment appointment = new Appointment(
                    appointmentId, date, time, completed, patient, doctor, service);
            appointments.add(appointment);
            doctor.addAppointment(appointment);
            patient.bookAppointment(appointment);
            services.add(service);
            line = br.readLine();
        }

        br.close();
    }

    public void loadBills() throws Exception {

        BufferedReader br = new BufferedReader(
                new FileReader("./src/bill.txt"));

        String line = br.readLine();
        while (line != null) {
            String[] part = line.split(",");
            int billId = Integer.parseInt(part[0]);
            int patientId = Integer.parseInt(part[1]);
            double total = Double.parseDouble(part[2]);
            Boolean paid = Boolean.parseBoolean(part[3]);
            Patient patient = findPatient(patientId);
            if (patient != null) {
                Bill bill = new Bill(billId, patient, paid);
                bill.setStoredTotal(total);
                bills.add(bill);
            }
            line = br.readLine();
        }
        br.close();
    }

    public void sortAppointmentsByDate() {
        Collections.sort(appointments,
                (a1, a2) -> a1.getDate().compareTo(a2.getDate()));
    }

    public void sortAppointmentsByDoctor() {
        Collections.sort(appointments,
                (a1, a2) -> a1.getDoctor().getfullName()
                        .compareTo(a2.getDoctor().getfullName()));
    }
}