
/**
 * Representa a un vendedor del sistema.
 */
public class Salesman {

    private String documentType;
    private long documentNumber;
    private String name;
    private String lastName;
    private double totalSales;

    /**
     * Construye un vendedor.
     *
     * @param documentType tipo de documento
     * @param documentNumber número de documento
     * @param name nombres del vendedor
     * @param lastName apellidos del vendedor
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
     * Obtiene el tipo de documento.
     *
     * @return tipo de documento
     */
    public String getDocumentType() {
        return documentType;
    }

    /**
     * Obtiene el número de documento.
     *
     * @return número de documento
     */
    public long getDocumentNumber() {
        return documentNumber;
    }

    /**
     * Obtiene los nombres del vendedor.
     *
     * @return nombres del vendedor
     */
    public String getName() {
        return name;
    }

    /**
     * Obtiene los apellidos del vendedor.
     *
     * @return apellidos del vendedor
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Obtiene el nombre completo del vendedor.
     *
     * @return nombre completo
     */
    public String getFullName() {
        return name + " " + lastName;
    }

    /**
     * Obtiene el total recaudado por el vendedor.
     *
     * @return total de ventas
     */
    public double getTotalSales() {
        return totalSales;
    }

    /**
     * Agrega una cantidad al total de ventas.
     *
     * @param amount cantidad de dinero
     */
    public void addSale(double amount) {
        totalSales += amount;
    }
}

