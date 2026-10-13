
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
 * Clase principal encargada de leer y procesar la información
 * de vendedores, productos y ventas.
 *
 * Carga los datos desde los archivos de entrada, calcula el valor
 * total vendido por cada vendedor y la cantidad de unidades vendidas
 * por producto. Finalmente, genera los reportes ordenados según
 * los requisitos del proyecto.
 *
 * @author william dussan
 * @version 1.0
 */
public class main {

    // Nombre del archivo que contiene la información de los vendedores.
    private static final String SALESMEN_INFO_FILE =
        "salesmen.txt";

    // Nombre del archivo que contiene la información de los productos.
    private static final String PRODUCTS_FILE =
        "products.txt";

    // Nombre de la carpeta que contiene los archivos de ventas.
    private static final String SALES_FOLDER =
        "sales";

    // Nombre del archivo de reporte de vendedores.
    private static final String SALESMEN_REPORT =
        "salesmen_report.csv";

    // Nombre del archivo de reporte de productos.
    private static final String PRODUCTS_REPORT =
        "products_report.csv";

    /**
     * Método principal que coordina el procesamiento de la información.
     *
     * Carga los productos y vendedores, obtiene los archivos de ventas,
     * procesa las transacciones y genera los dos reportes solicitados.
     * Al finalizar, muestra un mensaje con el resultado del proceso.
     *
     * @param args argumentos recibidos al ejecutar el programa; no se utilizan.
     */
    public static void main(String[] args) {

        try {

            System.out.println(
                "Iniciando procesamiento..."
            );

            // Carga la información de los productos y los vendedores.
            ArrayList<Product> products =
                loadProducts();

            ArrayList<Salesman> salesmen =
                loadSalesmen();

            // Obtiene los archivos que contienen los registros de ventas.
            File[] salesFiles =
                loadSalesFiles();

            // Procesa las ventas y actualiza los totales de vendedores y productos.
            processSales(
                salesFiles,
                salesmen,
                products
            );

            // Genera los reportes finales con la información procesada.
            generateSalesmanReport(salesmen);

            generateProductReport(products);

            // Muestra un resumen de los datos procesados.
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

            // Informa si ocurre un error al leer o escribir archivos.
            System.out.println(
                "Ocurrió un error durante el procesamiento:"
            );

            System.out.println(
                e.getMessage()
            );
        }
    }

    /**
     * Lee el archivo de productos y construye una lista de objetos Product.
     *
     * Cada línea del archivo debe contener el identificador, el nombre
     * y el precio del producto, separados por punto y coma.
     *
     * @return lista de productos cargados desde el archivo.
     * @throws IOException si ocurre un error al leer el archivo.
     */
    private static ArrayList<Product> loadProducts()
            throws IOException {

        ArrayList<Product> products =
            new ArrayList<Product>();

        // Abre el archivo que contiene la información de los productos.
        BufferedReader reader =
            new BufferedReader(
                new FileReader(PRODUCTS_FILE)
            );

        String line;

        // Lee el archivo línea por línea hasta llegar al final.
        while ((line = reader.readLine()) != null) {

            // Omite las líneas vacías.
            if (line.trim().isEmpty()) {
                continue;
            }

            // Separa los campos del producto utilizando el punto y coma.
            String[] data =
                line.split(";");

            int id =
                Integer.parseInt(data[0]);

            String name =
                data[1];

            double price =
                Double.parseDouble(data[2]);

            // Crea el objeto Product con los datos leídos.
            Product product =
                new Product(
                    id,
                    name,
                    price
                );

            // Agrega el producto a la lista.
            products.add(product);
        }

        // Cierra el archivo después de completar la lectura.
        reader.close();

        return products;
    }

    /**
     * Lee el archivo de vendedores y construye una lista de objetos Salesman.
     *
     * Cada línea debe contener el tipo de documento, el número de documento,
     * el nombre y el apellido del vendedor, separados por punto y coma.
     *
     * @return lista de vendedores cargados desde el archivo.
     * @throws IOException si ocurre un error al leer el archivo.
     */
    private static ArrayList<Salesman> loadSalesmen()
            throws IOException {

        ArrayList<Salesman> salesmen =
            new ArrayList<Salesman>();

        // Abre el archivo que contiene la información de los vendedores.
        BufferedReader reader =
            new BufferedReader(
                new FileReader(
                    SALESMEN_INFO_FILE
                )
            );

        String line;

        // Lee los registros de vendedores hasta llegar al final del archivo.
        while ((line = reader.readLine()) != null) {

            // Omite las líneas vacías.
            if (line.trim().isEmpty()) {
                continue;
            }

            // Separa los campos del vendedor.
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

            // Construye el objeto Salesman con la información obtenida.
            Salesman salesman =
                new Salesman(
                    documentType,
                    documentNumber,
                    name,
                    lastName
                );

            // Agrega el vendedor a la lista.
            salesmen.add(salesman);
        }

        // Cierra el archivo después de completar la lectura.
        reader.close();

        return salesmen;
    }

    /**
     * Obtiene los archivos almacenados en la carpeta de ventas.
     *
     * Comprueba que la ruta exista y corresponda a una carpeta.
     * Si la carpeta no existe o no es posible listar sus archivos,
     * devuelve un arreglo vacío.
     *
     * @return arreglo con los archivos encontrados en la carpeta de ventas.
     */
    private static File[] loadSalesFiles() {

        File folderSales =
            new File(SALES_FOLDER);

        // Comprueba que la carpeta exista y sea un directorio.
        if (!folderSales.exists()
                || !folderSales.isDirectory()) {

            return new File[0];
        }

        // Obtiene los archivos y subdirectorios contenidos en la carpeta.
        File[] salesByUser =
            folderSales.listFiles();

        // Devuelve un arreglo vacío si no se pudo obtener el listado.
        if (salesByUser == null) {
            return new File[0];
        }

        return salesByUser;
    }

    /**
     * Procesa todos los archivos de ventas encontrados.
     *
     * Omite las entradas que no sean archivos y delega el procesamiento
     * de cada archivo a processSalesFile.
     *
     * @param salesFiles archivos que contienen los registros de ventas.
     * @param salesmen lista de vendedores registrados.
     * @param products lista de productos registrados.
     * @throws IOException si ocurre un error al leer un archivo de ventas.
     */
    private static void processSales(
            File[] salesFiles,
            ArrayList<Salesman> salesmen,
            ArrayList<Product> products)
            throws IOException {

        // Recorre los archivos encontrados en la carpeta de ventas.
        for (File file : salesFiles) {

            // Omite las entradas que no sean archivos.
            if (!file.isFile()) {
                continue;
            }

            // Procesa las ventas registradas en el archivo actual.
            processSalesFile(
                file,
                salesmen,
                products
            );
        }
    }

    /**
     * Lee y procesa las ventas registradas en un archivo específico.
     *
     * La primera línea identifica al vendedor mediante su número
     * de documento. Las líneas siguientes contienen el identificador
     * del producto y la cantidad vendida.
     *
     * Para cada venta válida, acumula el valor correspondiente en el
     * total del vendedor y actualiza la cantidad vendida del producto.
     *
     * @param file archivo que contiene las ventas de un vendedor.
     * @param salesmen lista de vendedores registrados.
     * @param products lista de productos registrados.
     * @throws IOException si ocurre un error al leer el archivo.
     */
    private static void processSalesFile(
            File file,
            ArrayList<Salesman> salesmen,
            ArrayList<Product> products)
            throws IOException {

        // Abre el archivo de ventas para leer sus registros.
        BufferedReader reader =
            new BufferedReader(
                new FileReader(file)
            );

        // Lee la primera línea, que contiene los datos del vendedor.
        String line =
            reader.readLine();

        // Si el archivo está vacío, lo cierra y termina el procesamiento.
        if (line == null) {
            reader.close();
            return;
        }

        // Separa el tipo y el número de documento del vendedor.
        String[] salesmanData =
            line.split(";");

        // Obtiene el número de documento para identificar al vendedor.
        long salesmanId =
            Long.parseLong(
                salesmanData[1]
            );

        // Busca al vendedor correspondiente en la lista registrada.
        Salesman salesman =
            findSalesman(
                salesmanId,
                salesmen
            );

        // Si el vendedor no existe, cierra el archivo y lo omite.
        if (salesman == null) {
            reader.close();
            return;
        }

        // Lee y procesa cada registro de venta del archivo.
        while ((line = reader.readLine()) != null) {

            // Omite las líneas vacías.
            if (line.trim().isEmpty()) {
                continue;
            }

            // Separa el identificador del producto y la cantidad vendida.
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

            // Busca el producto correspondiente al identificador leído.
            Product product =
                findProduct(
                    productId,
                    products
                );

            // Actualiza los acumulados si el producto está registrado.
            if (product != null) {

                // Calcula el valor de la venta según el precio y la cantidad.
                double saleValue =
                    product.getPrice()
                    * quantity;

                // Acumula el valor de la venta para el vendedor.
                salesman.addSale(
                    saleValue
                );

                // Acumula las unidades vendidas para el producto.
                product.addQuantitySold(
                    quantity
                );
            }
        }

        // Cierra el archivo después de procesar todas sus ventas.
        reader.close();
    }

    /**
     * Busca un vendedor mediante su número de documento.
     *
     * @param documentNumber número de documento que se desea encontrar.
     * @param salesmen lista de vendedores donde se realiza la búsqueda.
     * @return el vendedor encontrado o null si no existe en la lista.
     */
    private static Salesman findSalesman(
            long documentNumber,
            ArrayList<Salesman> salesmen) {

        // Recorre la lista hasta encontrar el documento solicitado.
        for (Salesman salesman : salesmen) {

            if (salesman.getDocumentNumber()
                    == documentNumber) {

                return salesman;
            }
        }

        // Indica que no se encontró un vendedor con ese documento.
        return null;
    }

    /**
     * Busca un producto mediante su identificador.
     *
     * @param productId identificador del producto que se desea encontrar.
     * @param products lista de productos donde se realiza la búsqueda.
     * @return el producto encontrado o null si no existe en la lista.
     */
    private static Product findProduct(
            int productId,
            ArrayList<Product> products) {

        // Recorre la lista hasta encontrar el identificador solicitado.
        for (Product product : products) {

            if (product.getId() == productId) {
                return product;
            }
        }

        // Indica que no se encontró un producto con ese identificador.
        return null;
    }

    /**
     * Genera el reporte de vendedores ordenado por dinero recaudado.
     *
     * Ordena la lista de mayor a menor según el valor total de ventas
     * y escribe una línea por vendedor con su nombre completo y el total
     * recaudado, separados por punto y coma.
     *
     * @param salesmen lista de vendedores que se incluirán en el reporte.
     * @throws IOException si ocurre un error al escribir el reporte.
     */
    private static void generateSalesmanReport(
            ArrayList<Salesman> salesmen)
            throws IOException {

        // Ordena los vendedores de mayor a menor según sus ventas totales.
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

        // Abre el archivo de salida del reporte de vendedores.
        PrintWriter writer =
            new PrintWriter(
                new FileWriter(
                    SALESMEN_REPORT
                )
            );

        // Escribe el nombre completo y el dinero recaudado de cada vendedor.
        for (Salesman salesman : salesmen) {

            writer.println(
                salesman.getFullName()
                + ";"
                + salesman.getTotalSales()
            );
        }

        // Cierra el archivo después de generar el reporte.
        writer.close();
    }

    /**
     * Genera el reporte de productos ordenado por cantidad vendida.
     *
     * Ordena los productos de mayor a menor según las unidades vendidas.
     * Escribe una línea por producto con su nombre y precio unitario,
     * separados por punto y coma.
     *
     * @param products lista de productos que se incluirán en el reporte.
     * @throws IOException si ocurre un error al escribir el reporte.
     */
    private static void generateProductReport(
            ArrayList<Product> products)
            throws IOException {

        // Ordena los productos de mayor a menor según las unidades vendidas.
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

        // Abre el archivo de salida del reporte de productos.
        PrintWriter writer =
            new PrintWriter(
                new FileWriter(
                    PRODUCTS_REPORT
                )
            );

        // Escribe el nombre y el precio de cada producto.
        for (Product product : products) {

            writer.println(
                product.getName()
                + ";"
                + product.getPrice()
            );
        }

        // Cierra el archivo después de generar el reporte.
        writer.close();
    }
}
