
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
     * Obtiene el identificador del producto.
     *
     * @return identificador del producto
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene el nombre del producto.
     *
     * @return nombre del producto
     */
    public String getName() {
        return name;
    }

    /**
     * Obtiene el precio del producto.
     *
     * @return precio por unidad
     */
    public double getPrice() {
        return price;
    }

    /**
     * Obtiene la cantidad de unidades vendidas.
     *
     * @return cantidad vendida
     */
    public int getQuantitySold() {
        return quantitySold;
    }

    /**
     * Aumenta la cantidad de unidades vendidas.
     *
     * @param quantity cantidad vendida
     */
    public void addQuantitySold(int quantity) {
        quantitySold += quantity;
    }
}

