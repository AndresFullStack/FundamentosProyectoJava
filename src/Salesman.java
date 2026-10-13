
/**
 * Representa a un vendedor registrado en el sistema.
 *
 * Almacena la información personal del vendedor y permite acumular
 * el valor total de las ventas realizadas durante el procesamiento
 * de los archivos de ventas.
 *
 * @author william dussan
 * @version 1.0
 */
public class Salesman {

    // Tipo de documento de identificación del vendedor.
    private String documentType;

    // Número de documento de identificación del vendedor.
    private long documentNumber;

    // Nombres del vendedor.
    private String name;

    // Apellidos del vendedor.
    private String lastName;

    // Valor total acumulado de las ventas realizadas por el vendedor.
    private double totalSales;

    /**
     * Construye un vendedor con sus datos de identificación y nombre.
     *
     * El total de ventas se inicializa en cero, ya que todavía no se
     * han acumulado ventas para este vendedor.
     *
     * @param documentType tipo de documento del vendedor.
     * @param documentNumber número de documento del vendedor.
     * @param name nombres del vendedor.
     * @param lastName apellidos del vendedor.
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
     * Obtiene el tipo de documento del vendedor.
     *
     * @return tipo de documento de identificación.
     */
    public String getDocumentType() {
        return documentType;
    }

    /**
     * Obtiene el número de documento del vendedor.
     *
     * @return número de documento de identificación.
     */
    public long getDocumentNumber() {
        return documentNumber;
    }

    /**
     * Obtiene los nombres del vendedor.
     *
     * @return nombres del vendedor.
     */
    public String getName() {
        return name;
    }

    /**
     * Obtiene los apellidos del vendedor.
     *
     * @return apellidos del vendedor.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Obtiene el nombre completo del vendedor, uniendo sus nombres
     * y apellidos mediante un espacio.
     *
     * @return nombre completo del vendedor.
     */
    public String getFullName() {
        return name + " " + lastName;
    }

    /**
     * Obtiene el valor total acumulado de las ventas del vendedor.
     *
     * @return valor total de las ventas realizadas.
     */
    public double getTotalSales() {
        return totalSales;
    }

    /**
     * Acumula el valor de una venta en el total del vendedor.
     *
     * @param amount valor monetario de la venta que se agregará al total.
     */
    public void addSale(double amount) {
        totalSales += amount;
    }
}
