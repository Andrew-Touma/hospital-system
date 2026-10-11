import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Appointment {
    private int appointmentId;
    private String date;
    private String time;
    private boolean completed;
    private Patient patient;
    private Doctor doctor;
    private Service service;

    public Appointment(int appointmentId, String date, String time, boolean completed, Patient patient, Doctor doctor,
            Service service) {
        this.appointmentId = appointmentId;
        this.date = date;
        this.time = time;
        this.completed = completed;
        this.patient = patient;
        this.doctor = doctor;
        this.service = service;
    }

    public void markCompleted() throws Exception {
        completed = true;
        File mainFile = new File("./src/appointment.txt");
        File tempFile = new File("./src/appointment_temp.txt");

        BufferedReader br = new BufferedReader(new FileReader(mainFile));
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

        String line = br.readLine();
        while (line != null) {
            String[] part = line.split(",");
            int id = Integer.parseInt(part[0]);
            if (id == appointmentId) {
                part[6] = "true";
                bw.write(String.join(",", part));
            } else {
                bw.write(line);
            }
            bw.newLine();
            line = br.readLine();
        }
        br.close();
        bw.close();
        mainFile.delete();
        tempFile.renameTo(mainFile);
    }

    public void cancelAppointment(int canceledId) throws Exception {
        File MainFile = new File("./src/appointment.txt");
        File tempFile = new File("./src/appointment_temp.txt");

        BufferedReader br = new BufferedReader(new FileReader(MainFile));
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

        String line = br.readLine();
        boolean found = false;
        while (line != null) {
            String[] part = line.split(",");
            int Aid = Integer.parseInt(part[0]);
            if (Aid == canceledId) {
                found = true;
                line = br.readLine();
                continue;
            } else {
                bw.write(line);
                bw.newLine();
            }
            line = br.readLine();
        }
        br.close();
        bw.close();

        if (found) {
            MainFile.delete();
            tempFile.renameTo(MainFile);
        } else {
            tempFile.delete();
            System.out.println("Appointment not found.");
        }
    }

    public String toString() {
        return "Appointment id: " + appointmentId
                + ", date: " + date
                + ", time: " + time
                + ", doctor: " + doctor.getfullName()
                + ", patient: " + patient.getfullName()
                + ", service: " + service.getServiceName()
                + ", completed: " + completed;
    }

    public void getAppointmentDetails() {
        System.out.println("Appointment id : " + appointmentId + " , date : " + date + " , time : " + time
                + ", completed : " + completed);
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int newAppointmentId) {
        this.appointmentId = newAppointmentId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String newDate) {
        this.date = newDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String newTime) {
        this.time = newTime;
    }

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(boolean newCompleted) {
        this.completed = newCompleted;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Service getService() {
        return service;
    }

}
