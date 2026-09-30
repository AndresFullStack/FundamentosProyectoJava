
/**
 * Represents a salesman
 */
public class Salesman {

    private String documentType;
    private long documentNumber;
    private String name;
    private String lastName;
    private double totalSales;

    /**
     * Salesman constructor object 
     */
    public Salesman(
            String documentType,
            long documentNumber,
            String name,
            String lastName) {

        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.name = name;
        this.lastName = lastName;
        this.totalSales = 0;
    }

    /**
     * Get the type document
     */
    public String getDocumentType() {
        return documentType;
    }

    /**
     * Get the document number
     */
    public long getDocumentNumber() {
        return documentNumber;
    }

    /**
     * Get the name of the salesman
     */
    public String getName() {
        return name;
    }

    /**
     * Get the lastnames of the salesman
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Get the fullname of the salesman
     */
    public String getFullName() {
        return name + " " + lastName;
    }

    /**
     * Get the total of sales
     */
    public double getTotalSales() {
        return totalSales;
    }

    /**
     * Add the sales for the products of salesman
     */
    public void addSale(double amount) {
        totalSales += amount;
    }
}

