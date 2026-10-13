import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Genera los archivos de entrada necesarios para ejecutar el proyecto
 * de procesamiento de ventas.
 *
 * Crea archivos con información de productos, vendedores y ventas
 * utilizando datos pseudoaleatorios.
 *
 * Los archivos generados son:
 * - products.txt: contiene el identificador, nombre y precio de cada producto.
 * - salesmen.txt: contiene los datos personales de los vendedores.
 * - Carpeta sales: contiene un archivo de ventas por cada vendedor.
 *
 * @author william dussan
 * @version 1.0
 */
public class GenerateInfoFiles {

    // Rutas de los archivos y carpeta donde se almacena la información generada.
    private static final String SALES_FOLDER = "sales";
    private static final String SALESMEN_FILE = "salesmen.txt";
    private static final String PRODUCTS_FILE = "products.txt";

    // Generador de números pseudoaleatorios utilizado para crear los datos de prueba.
    private static final Random RANDOM = new Random();

    // Nombres disponibles para asignar a los vendedores.
    private static final String[] NAMES = {
        "William",
        "Juan",
        "Andres",
        "David",
        "Miguel",
        "Daniel",
        "Sebastian",
        "Laura",
        "Maria",
        "Camila",
        "Sofia",
        "Valentina"
    };

    // Apellidos disponibles para asignar a los vendedores.
    private static final String[] LAST_NAMES = {
        "Dussan",
        "Gonzalez",
        "Trivi",
        "Rojas",
        "Hant",
        "Castroz",
        "Hernandez",
        "Ramirez",
        "Torres",
        "Moreno",
        "Castro",
        "Vargas"
    };

    /**
     * Método principal encargado de coordinar la generación de los archivos
     * de entrada del proyecto.
     *
     * Genera el archivo de productos, el archivo de información de vendedores
     * y un archivo de ventas para cada vendedor. Al finalizar, informa si
     * la generación fue exitosa o si ocurrió un error de entrada/salida.
     *
     * @param args argumentos recibidos al ejecutar el programa; no se utilizan.
     */
    public static void main(String[] args) {

        try {
            // Cantidad de vendedores, productos y ventas por vendedor.
            int salesmanCount = 10;
            int productsCount = 20;
            int randomSalesCount = 10;

            // Genera los archivos generales de productos y vendedores.
            createProductsFile(productsCount);
            createSalesManInfoFile(salesmanCount);

            // Genera un archivo de ventas para cada vendedor.
            for (int i = 1; i <= salesmanCount; i++) {
                String name = NAMES[(i - 1) % NAMES.length];
                long id = 1000000000L + i;

                createSalesMenFile(
                    randomSalesCount,
                    name,
                    id
                );
            }

            // Informa que todos los archivos se generaron correctamente.
            System.out.println(
                "Los archivos fueron generados correctamente."
            );

        } catch (IOException e) {
            // Informa el error si ocurre un problema al crear o escribir archivos.
            System.out.println(
                "Ocurrió un error al generar los archivos: "
                + e.getMessage()
            );
        }
    }

    /**
     * Crea un archivo de ventas para un vendedor determinado.
     *
     * El archivo comienza con el tipo y número de documento del vendedor.
     * Las líneas siguientes contienen el identificador de cada producto
     * vendido y la cantidad correspondiente.
     *
     * Si la carpeta de ventas no existe, intenta crearla antes de escribir
     * el archivo.
     *
     * @param randomSalesCount cantidad de registros de venta que se generarán.
     * @param name nombre utilizado para identificar al vendedor en el archivo.
     * @param id número de documento del vendedor.
     * @throws IOException si ocurre un error al crear la carpeta o escribir el archivo.
     */
    public static void createSalesMenFile(
            int randomSalesCount,
            String name,
            long id) throws IOException {

        File folder = new File(SALES_FOLDER);

        // Crea la carpeta de ventas únicamente si todavía no existe.
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // Construye el nombre del archivo utilizando el nombre y documento del vendedor.
        String fileName = SALES_FOLDER
                + File.separator
                + name
                + "_" + id + ".txt";

        // Abre el archivo para escribir su contenido.
        PrintWriter writer = new PrintWriter(
                new FileWriter(fileName)
        );

        // Escribe el tipo y número de documento del vendedor en la primera línea.
        writer.println("CC;" + id);

        // Genera los registros de venta con identificadores y cantidades aleatorias.
        for (int i = 0; i < randomSalesCount; i++) {
            int productId = RANDOM.nextInt(17) + 1;
            int quantity = RANDOM.nextInt(10) + 1;

            // Guarda el identificador del producto y la cantidad vendida.
            writer.println(
                productId + ";" + quantity
            );
        }

        // Cierra el archivo después de completar la escritura.
        writer.close();
    }

    /**
     * Genera el archivo de información de productos.
     *
     * Cada línea contiene el identificador, el nombre y el precio unitario
     * de un producto, separados por punto y coma.
     *
     * Si la cantidad solicitada supera el número de nombres disponibles,
     * asigna nombres genéricos a los productos restantes.
     *
     * @param productsCount cantidad de productos que se generarán.
     * @throws IOException si ocurre un error al crear o escribir el archivo.
     */
    public static void createProductsFile(
            int productsCount) throws IOException {

        // Lista de nombres utilizados para los productos de prueba.
        String[] products = {
            "Computador",
            "Teclado",
            "Mouse",
            "Monitor",
            "Impresora",
            "Audifonos",
            "Camara",
            "Microfono",
            "Tablet",
            "Celular",
            "Cargador",
            "DiscoDuro",
            "MemoriaRAM",
            "Router",
            "CableHDMI",
            "Webcam",
            "Adaptador"
        };

        // Abre el archivo de productos para escribir la información.
        PrintWriter writer = new PrintWriter(
                new FileWriter(PRODUCTS_FILE)
        );

        // Recorre la cantidad de productos solicitada.
        for (int i = 1; i <= productsCount; i++) {

            String productName;

            // Utiliza un nombre de la lista cuando se encuentra disponible.
            if (i <= products.length) {
                productName = products[i - 1];
            } else {
                // Asigna un nombre genérico cuando se agotan los nombres disponibles.
                productName = "Producto" + i;
            }

            // Genera un precio aleatorio entre 10.000 y 1.000.000.
            int price = (RANDOM.nextInt(100) + 1) * 10000;

            // Escribe el identificador, nombre y precio del producto.
            writer.println(
                i + ";"
                + productName + ";"
                + price
            );
        }

        // Cierra el archivo después de escribir todos los productos.
        writer.close();
    }

    /**
     * Genera el archivo general con la información de los vendedores.
     *
     * Cada línea contiene el tipo de documento, el número de documento,
     * el nombre y el apellido del vendedor, separados por punto y coma.
     *
     * Los nombres y apellidos se seleccionan de manera pseudoaleatoria,
     * mientras que los números de documento se generan secuencialmente.
     *
     * @param salesmanCount cantidad de vendedores que se registrarán.
     * @throws IOException si ocurre un error al crear o escribir el archivo.
     */
    public static void createSalesManInfoFile(
            int salesmanCount) throws IOException {

        // Abre el archivo general de vendedores para escribir la información.
        PrintWriter writer = new PrintWriter(
                new FileWriter(SALESMEN_FILE)
        );

        // Genera un registro para cada vendedor.
        for (int i = 1; i <= salesmanCount; i++) {

            // Selecciona aleatoriamente un nombre y un apellido disponibles.
            String name = NAMES[
                RANDOM.nextInt(NAMES.length)
            ];

            String lastName = LAST_NAMES[
                RANDOM.nextInt(LAST_NAMES.length)
            ];

            // Genera un número de documento para identificar al vendedor.
            long id = 1000000000L + i;

            // Escribe los datos del vendedor separados por punto y coma.
            writer.println(
                "CC;"
                + id + ";"
                + name + ";"
                + lastName
            );
        }

        // Cierra el archivo después de registrar a todos los vendedores.
        writer.close();
    }
}
