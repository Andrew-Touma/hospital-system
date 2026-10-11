# Smart Hospital System

A Java-based management system designed to streamline hospital operations, including doctor and patient management, appointment scheduling, billing, and data analytics.

##  Core Features

###  Doctor & Patient Management
- **Add & Manage Doctors:** Register doctors with their ID, full name, and specialization.
- **Add & Manage Patients:** Register patients with their ID, full name, and contact information.
- **Data Persistence:** All doctor and patient records are automatically saved to and loaded from text files (`doctor.txt`, `patient.txt`).

###  Appointment Scheduling
- **Book Appointments:** Schedule appointments by linking a patient, a doctor, and a specific medical service.
- **Service Types:** Supports multiple service categories:
  - **Consultations:** Standard medical consultations.
  - **Surgeries:** Includes operation fees and surgery-specific details.
  - **Lab Tests:** Includes test types and discount rates.
- **Appointment Management:** Ability to cancel existing appointments and track completion status.
- **Flexible Sorting:** View patient appointments sorted by date or by doctor's name.

### Billing System
- **Generate Bills:** Automatically create bills for patients based on the services provided.
- **Payment Tracking:** Mark bills as paid and track unpaid balances.
- **Automatic Calculation:** Calculate total costs based on base service costs and specialized fees/discounts.

###  Analytics & Reporting
- **Financial Insights:** Track total revenue from paid bills and identify the top-spending patients.
- **Operational Metrics:** Monitor total and completed appointments, and calculate the average cost of services.
- **Doctor Performance:** Identify the most visited doctor in the system.
- **Advanced Filtering:** Filter services by cost (e.g., expensive services) or by type (e.g., only lab tests).
- **Service Sorting:** Sort all hospital services by cost or by category.

##  Technical Architecture

- **Language:** Java
- **Design Patterns:** 
  - **Inheritance & Polymorphism:** Used for the `Service` class hierarchy (`ConsultationService`, `SurgeryService`, `LabTestService`).
  - **Functional Interfaces:** Implementation of custom filters (`ServiceFilter`, `AppointmentFilter`, `BillFilter`) for dynamic data filtering.
- **Storage:** File-based persistence using `BufferedReader` and `BufferedWriter`.

##  Project Structure

- `classes/`: Contains the core logic and data models.
  - `HospitalSystem.java`: The central controller managing data and file I/O.
  - `Main.java`: The CLI entry point for user interaction.
  - `Analytics.java`: Logic for calculating hospital statistics.
  - `Patient.java`, `Doctor.java`, `Appointment.java`, `Bill.java`: Data entities.
  - `Service.java` & subclasses: Hierarchy for medical services.
- `src/`: Stores the persistent text files for doctors, patients, appointments, and bills.

##  How to Run

1. Ensure you have Java JDK installed.
2. Navigate to the project root.
3. Compile the classes:
   ```bash
   javac classes/*.java
   ```
4. Run the application:
   ```bash
   java -cp classes Main
   ```
