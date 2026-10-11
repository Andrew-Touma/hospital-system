public class LabTestService extends Service{
    private String testType;
    private double discountRate;

      public LabTestService(int serviceId,String name, double baseCost,String testType,double discountRate){
        super(serviceId,name,baseCost);
        this.testType = testType;
        this.discountRate = discountRate;
    }

    public double calculateCost(){
        double discount = getBaseCost() * discountRate;
        return getBaseCost()-discount;
    }

    public String getTestType(){
        return testType;
    }
    public void setTestType(String newTestType){
        this.testType = newTestType;
    }
    public double getDiscountRate(){
        return discountRate;
    }
    public void setDiscountRate(double newDiscountRate){
        this.discountRate = newDiscountRate;
    }
}
