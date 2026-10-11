import java.util.ArrayList;
public class Patient {
    private int patientId;
    private String fullName;
    private String phone;
    ArrayList<Appointment> appointments;

    public Patient( int patientId,String fullName,String phone){
        this.patientId = patientId;
        this.fullName = fullName;
        this.phone = phone;
        appointments = new ArrayList<>();
    }

    public int getPatientId(){
        return patientId;
    }
    public void setPatientId(int newPatientId){
        this.patientId = newPatientId;
    }
    public String getfullName(){
        return fullName;
    }
    public void setFullName(String newFullName){
        this.fullName = newFullName;
    }
    public String getPhone(){
        return phone;
    }
    public void setPhone(String newPhone){
        this.phone = newPhone;
    }

    public void bookAppointment(Appointment appointment){
        appointments.add(appointment);
    }
    public void cancelAppointment(Appointment appointment){
        appointments.remove(appointment);
    }
    public void printAppointments(){
        for(Appointment x : appointments){
            x.getAppointmentDetails();
        }
    }
    public double getTotalSpent(){
        double total = 0;
        for(Appointment x : appointments){
            if(x.getCompleted()){
                total +=x.getService().calculateCost();
            }
        }
        return total;
    }
}

