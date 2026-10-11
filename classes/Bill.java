import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Bill {
    private int billId;
    private Patient patient;
    ArrayList<Service> services;
    private boolean paid;
    private double storedTotal;

    public Bill(int billId, Patient patient, boolean paid) {
        this.billId = billId;
        this.patient = patient;
        this.paid = paid;
        this.services = new ArrayList<>();
    }

    public void addService(Service service) {
        services.add(service);
    }

    public double calculateTotal() {
        if (services.isEmpty()) {
            return storedTotal;
        }
        double total = 0;
        for (Service x : services) {
            total += x.calculateCost();
        }
        return total;
    }

    public void markAsPaid() throws Exception {
        paid = true;
        File mainFile = new File("./src/bill.txt");
        File tempFile = new File("./src/bill_temp.txt");

        BufferedReader br = new BufferedReader(new FileReader(mainFile));
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

        String line = br.readLine();
        while (line != null) {
            String[] part = line.split(",");
            int id = Integer.parseInt(part[0]);
            if (id == billId) {
                part[3] = "true"; // column 3 = paid
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

    public void printBill() {
        System.out.println("BillId : " + billId + ", Patient: " + patient.getfullName() + ", total : "
                + calculateTotal() + ", Paid : " + paid);
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int newBillId) {
        this.billId = newBillId;
    }

    public boolean getPaid() {
        return paid;
    }

    public void setPaid(boolean newPaid) {
        this.paid = newPaid;
    }

    public Patient getPatient() {
        return patient;
    }

    public double getStoredTotal() {
        return storedTotal;
    }

    public void setStoredTotal(double newStoredTotal) {
        this.storedTotal = newStoredTotal;
    }

}
