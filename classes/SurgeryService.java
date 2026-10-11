public class SurgeryService extends Service {
    private String surgeryType;
    private double operationFee;
    public SurgeryService(int serviceId,String name, double baseCost,String surgeryType,double operationFee){
        super(serviceId,name,baseCost);
        this.surgeryType = surgeryType;
        this.operationFee = operationFee;
    }

    public double calculateCost(){
        return getBaseCost() + operationFee;
    }

    public String getSurgeryType(){
        return surgeryType;
    }
    public void setSurgeryType(String newSurgeryType){
        this.surgeryType = newSurgeryType;
    }
    public double getOperationFee(){
        return operationFee;
    }
    public void setOperationFee(double newOperationFee){
        this.operationFee = newOperationFee;
    }

}
