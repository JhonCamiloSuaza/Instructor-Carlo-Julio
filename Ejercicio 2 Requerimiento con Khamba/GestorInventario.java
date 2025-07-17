import java.util.ArrayList;
import java.util.Scanner;

public class GestorInventario {
    public ArrayList<Producto> productos = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    String nombre, codigo, precioTexto, cantidadTexto, buscar, tipo;
    double precio;
    int cantidad;

    public void agregarProducto() {
        System.out.println(" Agregar nuevo producto ");

        System.out.print("Nombre del producto: ");
        nombre = sc.nextLine().trim();

        System.out.print("Código del producto: ");
        codigo = sc.nextLine().trim();

        System.out.print("Precio: ");
        precioTexto = sc.nextLine().trim();

        System.out.print("Cantidad: ");
        cantidadTexto = sc.nextLine().trim();

        if (nombre.equals("") || codigo.equals("") || precioTexto.equals("") || cantidadTexto.equals("")) {
            System.out.println(" Error: No puedes dejar los espacios en blanco.");
            return;
        }

        for (Producto p : productos) {
            if (p.codigo.equalsIgnoreCase(codigo)) {
                System.out.println(" Error: Código ya existe.");
                return;
            }
        }

        try {
            precio = Double.parseDouble(precioTexto);
            cantidad = Integer.parseInt(cantidadTexto);
        } catch (NumberFormatException e) {
            System.out.println(" Error: Precio o cantidad inválidos.");
            return;
        }

        Producto nuevo = new Producto(nombre, codigo, precio, cantidad);
        productos.add(nuevo);
        System.out.println(" Producto registrado correctamente.");
    }

    public void mostrarProductos() {
        if (productos.size() == 0) {
            System.out.println(" No hay productos en el inventario.");
        } else {
            System.out.println(" Lista de productos, ");
            for (Producto p : productos) {
                System.out.println("Código: " + p.codigo + " | Nombre: " + p.nombre +
                        " | Precio: $" + p.precio + " | Cantidad: " + p.cantidad);
            }
        }
    }

    public void actualizarStock() {
        System.out.println(" Actualizar stock de producto ");

        System.out.print("Código del producto: ");
        codigo = sc.nextLine().trim();

        Producto productoEncontrado = null;
        for (Producto p : productos) {
            if (p.codigo.equalsIgnoreCase(codigo)) {
                productoEncontrado = p;
                break;
            }
        }

        if (productoEncontrado == null) {
            System.out.println(" Error: Producto no encontrado.");
            return;
        }

        System.out.print("Cantidad a modificar: ");
        cantidadTexto = sc.nextLine().trim();

        try {
            cantidad = Integer.parseInt(cantidadTexto);
            if (cantidad < 0) {
                System.out.println(" Error: Cantidad no puede ser negativa.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println(" Error: Cantidad inválida.");
            return;
        }

        System.out.print("Tipo de movimiento (incrementar/disminuir): ");
        tipo = sc.nextLine().trim().toLowerCase();

        if (tipo.equals("incrementar")) {
            productoEncontrado.cantidad += cantidad;
            System.out.println(" Stock incrementado. Compra registrada.");
        } else if (tipo.equals("disminuir")) {
            if (productoEncontrado.cantidad < cantidad) {
                System.out.println(" Error: No se puede disminuir más de lo que hay en stock.");
                return;
            }
            productoEncontrado.cantidad -= cantidad;
            System.out.println(" Stock disminuido. Ajuste registrado.");
        } else {
            System.out.println(" Error: Tipo de movimiento inválido.");
        }
    }

    public void mostrarBajoStock() {
        boolean hayBajoStock = false;

        System.out.println(" Productos con bajo stock (< 10 unidades) ");
        for (Producto p : productos) {
            if (p.cantidad < 10) {
                System.out.println("Código: " + p.codigo + " | Nombre: " + p.nombre +
                        " | Cantidad: " + p.cantidad);
                hayBajoStock = true;
            }
        }

        if (!hayBajoStock) {
            System.out.println(" No hay productos con bajo stock.");
        }
    }

    public void buscarProducto() {
        System.out.println(" Buscar producto por nombre o código ");
        System.out.print("Buscar: ");
        buscar = sc.nextLine().toLowerCase();

        boolean encontrado = false;

        for (Producto p : productos) {
            if (p.nombre.toLowerCase().contains(buscar) || p.codigo.toLowerCase().contains(buscar)) {
                System.out.println(" Producto encontrado:");
                System.out.println("Código: " + p.codigo + " | Nombre: " + p.nombre +
                " | Precio: $" + p.precio + " | Cantidad: " + p.cantidad);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println(" No se encontró ningún producto con ese nombre o código.");
        }
    }
}
