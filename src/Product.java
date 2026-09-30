
/**
 * Represents a enabled product for sales
 */
public class Product {

    private int id;
    private String name;
    private double price;
    private int quantitySold;

    /**
     * Build a product
     */
    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantitySold = 0;
    }

    /**
     * Get the id of the product
     */
    public int getId() {
        return id;
    }

    /**
     * Get the name of the product
     */
    public String getName() {
        return name;
    }

    /**
     * Get the price of the product
     */
    public double getPrice() {
        return price;
    }

    /**
     * Get the quantity sold of the product
     */
    public int getQuantitySold() {
        return quantitySold;
    }

    /**
     * Add the quantity sold of the product
     */
    public void addQuantitySold(int quantity) {
        quantitySold += quantity;
    }
}

