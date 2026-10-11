abstract class Service implements Comparable<Service> {
    private int serviceId;
    private String name;
    private double baseCost;

    public  Service (int serviceId,String name,double baseCost){
        this.serviceId = serviceId;
        this.name = name;
        this.baseCost = baseCost;
    }
    abstract double calculateCost();
    public String toString(){
        return "Service Id : " +serviceId+ ", service name : " +name+ ", service baseCost : "+baseCost;
    }

    public int compareTo(Service s){
        return Double.compare(this.calculateCost(), s.calculateCost());
    }

    public String getServiceName(){
        return name;
    }
    public void setServiceName(String newName){
        this.name = newName;
    }

     public int getServiceId(){
        return serviceId;
    }
    public void setServiceId(int newServiceId){
        this.serviceId = newServiceId;
    }

     public double getBaseCost(){
        return baseCost;
    }
    public void setBaseCost(double newBaseCost){
        this.baseCost = newBaseCost;
    }
}
