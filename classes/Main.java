import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        HospitalSystem hs = new HospitalSystem();

        hs.loadDoctors();
        hs.loadPatients();
        hs.loadAppointments();
        hs.loadBills();

        boolean running = true;

        while (running) {

            System.out.println("\n===== SMART HOSPITAL SYSTEM =====");
            System.out.println("1. ADD_DOCTOR");
            System.out.println("2. ADD_PATIENT");
            System.out.println("3. BOOK_APPOINTMENT");
            System.out.println("4. CANCEL_APPOINTMENT");
            System.out.println("5. GENERATE_BILL");
            System.out.println("6. PAY_BILL");
            System.out.println("7. SHOW_DOCTORS");
            System.out.println("8. SHOW_PATIENT");
            System.out.println("9. ANALYTICS");
            System.out.println("10. EXIT");

            System.out.print("Choose option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:

                    System.out.print("Doctor ID: ");
                    int doctorId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Doctor Name: ");
                    String doctorName = sc.nextLine();

                    System.out.print("Specialization: ");
                    String specialization = sc.nextLine();

                    Doctor doctor = new Doctor(
                            doctorId,
                            doctorName,
                            specialization);

                    hs.addDoctor(doctor);

                    System.out.println("Doctor added.");
                    break;

                case 2:

                    System.out.print("Patient ID: ");
                    int patientId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Patient Name: ");
                    String patientName = sc.nextLine();

                    System.out.print("Phone: ");
                    String phone = sc.nextLine();

                    Patient patient = new Patient(
                            patientId,
                            patientName,
                            phone);

                    hs.addPatient(patient);

                    System.out.println("Patient added.");
                    break;

                case 3:

                    System.out.print("Appointment ID: ");
                    int appointmentId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Date (YYYY-MM-DD): ");
                    String date = sc.nextLine();

                    System.out.print("Time: ");
                    String time = sc.nextLine();

                    System.out.print("Doctor ID: ");
                    int doctorSearchId = sc.nextInt();

                    System.out.print("Patient ID: ");
                    int patientSearchId = sc.nextInt();

                    Doctor foundDoctor = hs.findDoctor(doctorSearchId);

                    Patient foundPatient = hs.findPatient(patientSearchId);

                    if (foundDoctor == null || foundPatient == null) {

                        System.out.println("Doctor or Patient not found.");
                        break;
                    }

                    System.out.println("Service type:");
                    System.out.println("1. Consultation");
                    System.out.println("2. Surgery");
                    System.out.println("3. Lab Test");
                    int serviceChoice = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Service name: ");
                    String serviceName = sc.nextLine();

                    System.out.print("Base cost: ");
                    double baseCost = sc.nextDouble();
                    sc.nextLine();

                    Service service;

                    if (serviceChoice == 2) {
                        System.out.print("Surgery type: ");
                        String surgeryType = sc.nextLine();
                        System.out.print("Operation fee: ");
                        double operationFee = sc.nextDouble();
                        sc.nextLine();
                        service = new SurgeryService(1, serviceName, baseCost, surgeryType, operationFee);

                    } else if (serviceChoice == 3) {
                        System.out.print("Test type: ");
                        String testType = sc.nextLine();
                        System.out.print("Discount rate (0.0 - 1.0): ");
                        double discountRate = sc.nextDouble();
                        sc.nextLine();
                        service = new LabTestService(1, serviceName, baseCost, testType, discountRate);

                    } else {
                        service = new ConsultationService(1, serviceName, baseCost);
                    }

                    hs.bookAppointments(
                            appointmentId,
                            date,
                            time,
                            false,
                            foundPatient,
                            foundDoctor,
                            service);

                    System.out.println("Appointment booked.");

                    break;

                case 4:

                    System.out.print("Appointment ID to cancel: ");
                    int cancelId = sc.nextInt();

                    boolean canceled = false;

                    for (Appointment a : hs.appointments) {

                        if (a.getAppointmentId() == cancelId) {

                            a.cancelAppointment(cancelId);
                            a.getDoctor().appointments.remove(a);
                            a.getPatient().appointments.remove(a);
                            hs.appointments.remove(a);
                            canceled = true;
                            System.out.println("Appointment canceled.");
                            break;
                        }
                    }

                    if (!canceled) {
                        System.out.println("Appointment not found.");
                    }

                    break;

                case 5:

                    System.out.print("Bill ID: ");
                    int billId = sc.nextInt();

                    System.out.print("Patient ID: ");
                    int billPatientId = sc.nextInt();

                    Patient billPatient = hs.findPatient(billPatientId);

                    if (billPatient == null) {

                        System.out.println("Patient not found.");
                        break;
                    }

                    ArrayList<Service> services = new ArrayList<>();

                    services.add(
                            new ConsultationService(
                                    1,
                                    "Consultation",
                                    100));

                    Bill bill = hs.generateBill(
                            billId,
                            billPatient,
                            services);

                    System.out.println("Bill generated.");
                    bill.printBill();

                    break;

                case 6:

                    System.out.print("Bill ID: ");
                    int payBillId = sc.nextInt();

                    boolean paid = false;

                    for (Bill b : hs.bills) {

                        if (b.getBillId() == payBillId) {

                            b.markAsPaid();

                            paid = true;

                            System.out.println("Bill paid.");
                            break;
                        }
                    }

                    if (!paid) {
                        System.out.println("Bill not found.");
                    }

                    break;

                case 7:

                    hs.printAllDoctors();

                    break;

                case 8:

                    System.out.print("Patient ID: ");
                    int showPatientId = sc.nextInt();

                    Patient showPatient = hs.findPatient(showPatientId);

                    if (showPatient == null) {

                        System.out.println("Patient not found.");
                        break;
                    }

                    System.out.println("1. Sort by Date");
                    System.out.println("2. Sort by Doctor Name");
                    System.out.println("3. Show Completed Appointments");

                    int patientChoice = sc.nextInt();

                    if (patientChoice == 1) {
                        Collections.sort(showPatient.appointments,
                                (a1, a2) -> a1.getDate().compareTo(a2.getDate()));
                        showPatient.printAppointments();
                    } else if (patientChoice == 2) {
                        Collections.sort(showPatient.appointments,
                                (a1, a2) -> a1.getDoctor().getfullName()
                                        .compareTo(a2.getDoctor().getfullName()));
                        showPatient.printAppointments();
                    }

                    else if (patientChoice == 3) {

                        hs.filterAppointments(
                                appointment -> appointment.getCompleted());
                    }

                    break;

                case 9:

                    Analytics analytics = new Analytics(hs.doctors, hs.patients, hs.bills, hs.appointments);

                    System.out.println("\n===== ANALYTICS =====");

                    System.out.println(
                            "1. Total Revenue");

                    System.out.println(
                            "2. Total Appointments");

                    System.out.println(
                            "3. Completed Appointments");

                    System.out.println(
                            "4. Average Service Cost");

                    System.out.println(
                            "5. Most Visited Doctor");

                    System.out.println(
                            "6. Top Spending Patient");

                    System.out.println(
                            "7. Expensive Services");

                    System.out.println(
                            "8. Lab Test Services");

                    System.out.println(
                            "9. Unpaid Bills");

                    System.out.println(
                            "10. Sort Services By Cost");

                    System.out.println(
                            "11. Sort Services By Type");
                    System.out.print("Enter your option : ");

                    int analyticsChoice = sc.nextInt();

                    switch (analyticsChoice) {

                        case 1:

                            System.out.println( "Your total is : " + analytics.getTotalRevenue());

                            break;

                        case 2:

                            System.out.println(
                                    "There is a total of : " +analytics.getTotalAppointments() + " appointments");

                            break;

                        case 3:

                            System.out.println(
                                   "there is a total of : " + analytics.getCompletedAppointments() + " completed appointments");

                            break;

                        case 4:

                            System.out.println(
                                    analytics.getAverageServiceCost());

                            break;

                        case 5:

                            Doctor topDoctor = analytics.getMostVisitedDoctor();

                            if (topDoctor != null) {

                                System.out.println(
                                        topDoctor.getfullName());
                            }

                            break;

                        case 6:

                            Patient topPatient = analytics.getTopSpendingPatient();

                            if (topPatient != null) {

                                System.out.println(
                                        topPatient.getfullName());
                            }

                            break;

                        case 7:

                            hs.filterServices(
                                    service1 -> service1.calculateCost() > 500);

                            break;

                        case 8:

                            hs.filterServices(
                                    service1 -> service1 instanceof LabTestService);

                            break;

                        case 9:

                            hs.filterBills(
                                    bill1 -> !bill1.getPaid());

                            break;

                        case 10:

                            Collections.sort(hs.services);

                            for (Service s : hs.services) {
                                System.out.println(s);
                            }

                            break;

                        case 11:

                            Collections.sort(
                                    hs.services,
                                    (s1, s2) -> s1.getClass()
                                            .getSimpleName()
                                            .compareTo(
                                                    s2.getClass()
                                                            .getSimpleName()));

                            for (Service s : hs.services) {
                                System.out.println(s);
                            }

                            break;

                        default:

                            System.out.println("Invalid option.");
                    }

                    break;

                case 10:

                    running = false;

                    System.out.println("System closed.");

                    break;

                default:

                    System.out.println("Invalid option.");
            }
        }

        sc.close();
    }
}