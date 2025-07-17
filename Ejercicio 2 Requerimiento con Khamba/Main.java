import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GestorInventario inventario = new GestorInventario();

        int opcion;

        do {
            System.out.println(" MENÚ");
            System.out.println("1. Agregar producto al inventario");
            System.out.println("2. Actualizar stock de producto");
            System.out.println("3. Listado de productos con bajo stock");
            System.out.println("4. Búsqueda de productos");
            System.out.println("5. Ver productos");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    inventario.agregarProducto();
                    break;
                case 2:
                    inventario.actualizarStock();
                    break;
                case 3:
                    inventario.mostrarBajoStock();
                    break;
                case 4:
                    inventario.buscarProducto();
                    break;
                case 5:
                    inventario.mostrarProductos();
                    break;
                case 0:
                    System.out.println(" Saliendo del programa...");
                    break;
                default:
                    System.out.println(" Opción no válida. Inténtalo de nuevo.");
                    break;
            }

        } while (opcion != 0);
        sc.close(); 
    }
}
