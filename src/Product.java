
/**
 * Representa un producto disponible para la venta dentro del sistema.
 *
 * Almacena la información básica del producto y permite llevar el registro
 * de la cantidad total de unidades vendidas durante el procesamiento
 * de los archivos de ventas.
 *
 * @author wlliam dussan
 * @version 1.0
 */
public class Product {

    // Identificador único del producto.
    private int id;

    // Nombre del producto.
    private String name;

    // Precio por unidad del producto.
    private double price;

    // Cantidad total de unidades vendidas del producto.
    private int quantitySold;

    /**
     * Construye un producto con su identificador, nombre y precio.
     *
     * La cantidad vendida se inicializa en cero, ya que todavía no se
     * han registrado ventas para el producto.
     *
     * @param id identificador del producto.
     * @param name nombre del producto.
     * @param price precio por unidad del producto.
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
     * @return identificador del producto.
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene el nombre del producto.
     *
     * @return nombre del producto.
     */
    public String getName() {
        return name;
    }

    /**
     * Obtiene el precio por unidad del producto.
     *
     * @return precio unitario del producto.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Obtiene la cantidad total de unidades vendidas del producto.
     *
     * @return cantidad acumulada de unidades vendidas.
     */
    public int getQuantitySold() {
        return quantitySold;
    }

    /**
     * Incrementa la cantidad total de unidades vendidas del producto.
     *
     * El valor recibido se suma a la cantidad que ya se encuentra
     * registrada.
     *
     * @param quantity cantidad de unidades que se agregarán al total vendido.
     */
    public void addQuantitySold(int quantity) {
        quantitySold += quantity;
    }
}
