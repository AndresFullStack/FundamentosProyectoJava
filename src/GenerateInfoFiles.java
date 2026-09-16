import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;


public class GenerateInfoFiles {

	// These files save the directory of the files	
    private static final String SALES_FOLDER = "sales";
    private static final String SALESMEN_FILE = "salesmen.txt";
    private static final String PRODUCTS_FILE = "products.txt";

    private static final Random RANDOM = new Random();

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
     * This method main create the files and save the information in each one these files
     */
    public static void main(String[] args) {

        try {
            int salesmanCount = 10;
            int productsCount = 20;
            int randomSalesCount = 10;

            createProductsFile(productsCount);
            createSalesManInfoFile(salesmanCount);

            // With this for it can create the each file of sales for the salesmen            
            for (int i = 1; i <= salesmanCount; i++) {
                String name = NAMES[(i - 1) % NAMES.length];
                long id = 1000000000L + i;

                createSalesMenFile(
                    randomSalesCount,
                    name,
                    id
                );
            }

            System.out.println(
                "Los archivos fueron generados correctamente."
            );

        } catch (IOException e) {
            System.out.println(
                "Ocurrió un error al generar los archivos: "
                + e.getMessage()
            );
        }
    }

    /**
     * Create each sale in the file
     *
     */
    public static void createSalesMenFile(
            int randomSalesCount,
            String name,
            long id) throws IOException {

        File folder = new File(SALES_FOLDER);
        
        
        // This validation is for evitar that re-create other folder        
        if (!folder.exists()) {
            folder.mkdirs();
        }

        String fileName = SALES_FOLDER
                + File.separator
                + name
                + "_" + id + ".txt";

        PrintWriter writer = new PrintWriter(
                new FileWriter(fileName)
        );

        writer.println("CC;" + id);

        for (int i = 0; i < randomSalesCount; i++) {
            int productId = RANDOM.nextInt(17) + 1;
            int quantity = RANDOM.nextInt(10) + 1;

            writer.println(
                productId + ";" + quantity
            );
        }

        writer.close();
    }

    /**
     * Creates the products's file
     *
     * The structure of each product is :
     * ID, name of the product y their price.
     *
     */
    public static void createProductsFile(
            int productsCount) throws IOException {

    	// We declare the products in this case is tech gadgets
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

        PrintWriter writer = new PrintWriter(
                new FileWriter(PRODUCTS_FILE)
        );

        for (int i = 1; i <= productsCount; i++) {

            String productName;

            if (i <= products.length) {
                productName = products[i - 1];
            } else {
            	// If the product doesn't exist it creates a new one            	
                productName = "Producto" + i;
            }

            int price = (RANDOM.nextInt(100) + 1) * 10000;

            // We write in the file each product 
            writer.println(
                i + ";"
                + productName + ";"
                + price
            );
        }

        writer.close();
    }

    /**
     * Create the salesman's file
     *
     * The structure for each person is:
     * Document type, document number, name y lastNames.
     *
     */
    public static void createSalesManInfoFile(
            int salesmanCount) throws IOException {

        PrintWriter writer = new PrintWriter(
                new FileWriter(SALESMEN_FILE)
        );

        for (int i = 1; i <= salesmanCount; i++) {

        	// In the array of names random choose the number between zero and the array's length   	
            String name = NAMES[
                RANDOM.nextInt(NAMES.length)
            ];

            String lastName = LAST_NAMES[
                RANDOM.nextInt(LAST_NAMES.length)
            ];

            long id = 1000000000L + i;

            writer.println(
                "CC;"
                + id + ";"
                + name + ";"
                + lastName
            );
        }

        writer.close();
    }
}

