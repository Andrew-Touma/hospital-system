import java.util.ArrayList;
public class Doctor{
    private int doctorId;
    private String fullName;
    private String specialization;
    ArrayList<Appointment> appointments;

    public Doctor(int doctorId, String fullName,String specialization){
        this.doctorId = doctorId;
        this.fullName = fullName;
        this.specialization = specialization;
        this.appointments = new ArrayList<>();
    }

    public int getDoctorId(){
        return doctorId;
    }
    public void setDoctorId(int newDoctorId){
        this.doctorId = newDoctorId;
    }
     public String getfullName(){
        return fullName;
    }
    public void setFullName(String newFullName){
        this.fullName = newFullName;
    }
    public String getSpecialization(){
        return specialization;
    }
    public void setSpecialization(String newSpecialization){
        this.specialization = newSpecialization;
    }

    public void addAppointment(Appointment appointment)throws Exception{
        appointments.add(appointment);
    }

    public void getAvailableAppointments(){
        for (Appointment x : appointments){
            if(!x.getCompleted()){
                x.getAppointmentDetails();
            }
    
        }
    }

    public void getCompletedAppointments(){
        for (Appointment x : appointments){
            if(x.getCompleted()){
                x.getAppointmentDetails();
            }
        }
    }

    public void printDoctorSchedule(){
        for(Appointment x : appointments){
            x.getAppointmentDetails();
        }
    }

}
   

