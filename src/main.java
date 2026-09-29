import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * This principal main is for read and process 
 * the information of salesmen, products and sales
 */
public class main {

    private static final String SALESMEN_INFO_FILE =
        "salesmen.txt";

    private static final String PRODUCTS_FILE =
        "products.txt";

    private static final String SALES_FOLDER =
        "sales";

    private static final String SALESMEN_REPORT =
        "salesmen_report.csv";

    private static final String PRODUCTS_REPORT =
        "products_report.csv";

    public static void main(String[] args) {

        try {

            System.out.println(
                "Iniciando procesamiento..."
            );

            ArrayList<Product> products =
                loadProducts();

            ArrayList<Salesman> salesmen =
                loadSalesmen();

            File[] salesFiles =
                loadSalesFiles();

            processSales(
                salesFiles,
                salesmen,
                products
            );

            generateSalesmanReport(salesmen);

            generateProductReport(products);

            System.out.println(
                "Productos cargados: "
                + products.size()
            );

            System.out.println(
                "Vendedores cargados: "
                + salesmen.size()
            );

            System.out.println(
                "Archivos de ventas procesados: "
                + salesFiles.length
            );

            System.out.println(
                "Los reportes fueron generados correctamente."
            );

        } catch (IOException e) {

            System.out.println(
                "Ocurrió un error durante el procesamiento:"
            );

            System.out.println(
                e.getMessage()
            );
        }
    }

    /**
     * Read the products file
     */
    private static ArrayList<Product> loadProducts()
            throws IOException {

        ArrayList<Product> products =
            new ArrayList<Product>();

        BufferedReader reader =
            new BufferedReader(
                new FileReader(PRODUCTS_FILE)
            );

        String line;
        
        // Start reading before a line break
        while ((line = reader.readLine()) != null) {

            if (line.trim().isEmpty()) {
                continue;
            }

            String[] data =
                line.split(";");

            int id =
                Integer.parseInt(data[0]);

            String name =
                data[1];

            double price =
                Double.parseDouble(data[2]);
            
            

            // Extracting each product we can destructuring the object and send to create a Product in products
            Product product =
                new Product(
                    id,
                    name,
                    price
                );

            products.add(product);
        }

        reader.close();

        return products;
    }

    /**
     * Read the salesman file
     */
    private static ArrayList<Salesman> loadSalesmen()
            throws IOException {

        ArrayList<Salesman> salesmen =
            new ArrayList<Salesman>();

        BufferedReader reader =
            new BufferedReader(
                new FileReader(
                    SALESMEN_INFO_FILE
                )
            );

        String line;

        while ((line = reader.readLine()) != null) {

            if (line.trim().isEmpty()) {
                continue;
            }

            String[] data =
                line.split(";");

            String documentType =
                data[0];

            long documentNumber =
                Long.parseLong(data[1]);

            String name =
                data[2];

            String lastName =
                data[3];

            Salesman salesman =
                new Salesman(
                    documentType,
                    documentNumber,
                    name,
                    lastName
                );

            salesmen.add(salesman);
        }

        reader.close();

        return salesmen;
    }

    /**
     * Obtiene todos los archivos de ventas de la carpeta.
     *
     * @return archivos de ventas encontrados
     */
    private static File[] loadSalesFiles() {

        File folder =
            new File(SALES_FOLDER);

        if (!folder.exists()
                || !folder.isDirectory()) {

            return new File[0];
        }

        File[] files =
            folder.listFiles();

        if (files == null) {
            return new File[0];
        }

        return files;
    }

    /**
     * Procesa todos los archivos de ventas.
     *
     * @param salesFiles archivos de ventas
     * @param salesmen lista de vendedores
     * @param products lista de productos
     * @throws IOException si ocurre un error
     */
    private static void processSales(
            File[] salesFiles,
            ArrayList<Salesman> salesmen,
            ArrayList<Product> products)
            throws IOException {

        for (File file : salesFiles) {

            if (!file.isFile()) {
                continue;
            }

            processSalesFile(
                file,
                salesmen,
                products
            );
        }
    }

    /**
     * Procesa un archivo de ventas.
     *
     * @param file archivo de ventas
     * @param salesmen lista de vendedores
     * @param products lista de productos
     * @throws IOException si ocurre un error
     */
    private static void processSalesFile(
            File file,
            ArrayList<Salesman> salesmen,
            ArrayList<Product> products)
            throws IOException {

        BufferedReader reader =
            new BufferedReader(
                new FileReader(file)
            );

        String line =
            reader.readLine();

        if (line == null) {
            reader.close();
            return;
        }

        String[] salesmanData =
            line.split(";");

        long salesmanId =
            Long.parseLong(
                salesmanData[1]
            );

        Salesman salesman =
            findSalesman(
                salesmanId,
                salesmen
            );

        if (salesman == null) {
            reader.close();
            return;
        }

        while ((line = reader.readLine()) != null) {

            if (line.trim().isEmpty()) {
                continue;
            }

            String[] saleData =
                line.split(";");

            int productId =
                Integer.parseInt(
                    saleData[0]
                );

            int quantity =
                Integer.parseInt(
                    saleData[1]
                );

            Product product =
                findProduct(
                    productId,
                    products
                );

            if (product != null) {

                double saleValue =
                    product.getPrice()
                    * quantity;

                salesman.addSale(
                    saleValue
                );

                product.addQuantitySold(
                    quantity
                );
            }
        }

        reader.close();
    }

    /**
     * Busca un vendedor por número de documento.
     *
     * @param documentNumber número de documento
     * @param salesmen lista de vendedores
     * @return vendedor encontrado o null
     */
    private static Salesman findSalesman(
            long documentNumber,
            ArrayList<Salesman> salesmen) {

        for (Salesman salesman : salesmen) {

            if (salesman.getDocumentNumber()
                    == documentNumber) {

                return salesman;
            }
        }

        return null;
    }

    /**
     * Busca un producto por su identificador.
     *
     * @param productId identificador del producto
     * @param products lista de productos
     * @return producto encontrado o null
     */
    private static Product findProduct(
            int productId,
            ArrayList<Product> products) {

        for (Product product : products) {

            if (product.getId() == productId) {
                return product;
            }
        }

        return null;
    }

    /**
     * Genera el reporte de vendedores.
     *
     * El reporte se ordena de mayor a menor
     * cantidad de dinero recaudado.
     *
     * @param salesmen lista de vendedores
     * @throws IOException si ocurre un error
     */
    private static void generateSalesmanReport(
            ArrayList<Salesman> salesmen)
            throws IOException {

        Collections.sort(
            salesmen,
            new Comparator<Salesman>() {

                @Override
                public int compare(
                        Salesman salesman1,
                        Salesman salesman2) {

                    return Double.compare(
                        salesman2.getTotalSales(),
                        salesman1.getTotalSales()
                    );
                }
            }
        );

        PrintWriter writer =
            new PrintWriter(
                new FileWriter(
                    SALESMEN_REPORT
                )
            );

        for (Salesman salesman : salesmen) {

            writer.println(
                salesman.getFullName()
                + ";"
                + salesman.getTotalSales()
            );
        }

        writer.close();
    }

    /**
     * Genera el reporte de productos.
     *
     * Los productos se ordenan de mayor a menor
     * cantidad de unidades vendidas.
     *
     * @param products lista de productos
     * @throws IOException si ocurre un error
     */
    private static void generateProductReport(
            ArrayList<Product> products)
            throws IOException {

        Collections.sort(
            products,
            new Comparator<Product>() {

                @Override
                public int compare(
                        Product product1,
                        Product product2) {

                    return Integer.compare(
                        product2.getQuantitySold(),
                        product1.getQuantitySold()
                    );
                }
            }
        );

        PrintWriter writer =
            new PrintWriter(
                new FileWriter(
                    PRODUCTS_REPORT
                )
            );

        for (Product product : products) {

            writer.println(
                product.getName()
                + ";"
                + product.getPrice()
            );
        }

        writer.close();
    }
}
