class ConsultationService extends Service {
    public ConsultationService(int serviceId,String name,double baseCost){
        super(serviceId,name,baseCost);
    }
    public double calculateCost(){
        return getBaseCost();
    }
}
