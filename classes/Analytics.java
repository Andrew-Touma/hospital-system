import java.util.ArrayList;
public class Analytics{
    ArrayList<Doctor> doctors;
    ArrayList<Patient> patients;
    ArrayList<Bill> bills;
    ArrayList<Appointment> appointments;

    public Analytics(ArrayList<Doctor> doctors,ArrayList<Patient> patients,ArrayList<Bill> bills,ArrayList<Appointment> appointments){
        this.doctors = doctors;
        this.patients = patients; 
        this.bills = bills;
        this.appointments = appointments; 
    }

    public double getTotalRevenue(){
        double total = 0;
        for(Bill x : bills){
            if(x.getPaid()){
                total+=x.calculateTotal();
            }
        }
        return total;
    }

    public Doctor getMostVisitedDoctor(){
        Doctor top = null;
        int max = 0;
        for(Doctor x : doctors){
            int count = x.appointments.size();
            if(count > max){
                max = count;
                top = x;
            }
        }
        return top;
    }

    public int getTotalAppointments(){
        return appointments.size();
    }

    public int getCompletedAppointments(){
        int count = 0;
        for(Appointment x :appointments){
            if(x.getCompleted()){
                count++;
            }
        }
        return count;
    }

    public double getAverageServiceCost(){
        double total = 0;
        int count = 0;
        for(Bill x : bills){
            for(Service y : x.services){
                total+=y.calculateCost();
                count++;
            }
        }
        if(count == 0){
            return 0;
        }
        return total/count;
    }

    public Patient getTopSpendingPatient(){
        Patient top = null;
        double max = 0;
        for(Patient x : patients){
            double total = x.getTotalSpent();
            if(total > max){
                max = total;
                top = x;
            }
        }
        return top;
    }
}