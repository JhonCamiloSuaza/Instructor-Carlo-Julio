import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);
    static Biblioteca biblioteca = new Biblioteca();

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n--- SISTEMA DE BIBLIOTECA ---");
            System.out.println("1. Registrar libro");
            System.out.println("2. Registrar lector");
            System.out.println("3. Prestar libro");
            System.out.println("4. Devolver libro");
            System.out.println("5. Ver libros disponibles");
            System.out.println("6. Ver libros prestados");
            System.out.println("7. Ver lectores registrados");
            System.out.println("8. Buscar libro por ISBN");
            System.out.println("9. Buscar libro por título");
            System.out.println("10. Buscar libro por autor");
            System.out.println("11. Editar libro");
            System.out.println("12. Eliminar libro");
            System.out.println("13. Ver préstamos vencidos");
            System.out.println("14. Ver historial de préstamos");
            System.out.println("15. Reservar libro");              
            System.out.println("16. Ver reservas de libros");     
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(scan.nextLine());

            switch(opcion) {
                case 1: registrarLibro(); break;
                case 2: registrarLector(); break;
                case 3: prestarLibro(); break;
                case 4: devolverLibro(); break;
                case 5: biblioteca.verLibrosDisponibles(); break;
                case 6: biblioteca.verLibrosPrestados(); break;
                case 7: biblioteca.verLectores(); break;
                case 8: buscarLibroPorISBN(); break;
                case 9: buscarLibroPorTitulo(); break;
                case 10: buscarLibroPorAutor(); break;
                case 11: editarLibro(); break;
                case 12: eliminarLibro(); break;
                case 13: biblioteca.mostrarPrestamosVencidos(); break;
                case 14: biblioteca.mostrarHistorialPrestamos(); break;
                case 15: reservarLibro(); break;                    
                case 16: biblioteca.listarReservas(); break;       
                case 0: System.out.println("¡Hasta pronto!"); break;
                default: System.out.println("Opción inválida.");
            }
        } while(opcion != 0);
    }

    static void registrarLibro() {
        System.out.print("ISBN: ");
        String isbn = scan.nextLine();
        System.out.print("Título: ");
        String titulo = scan.nextLine();
        System.out.print("Autor: ");
        String autor = scan.nextLine();
        System.out.print("Género: ");
        String genero = scan.nextLine();
        System.out.print("Fecha de publicación (yyyy-mm-dd): ");
        String fecha = scan.nextLine();
        System.out.println(biblioteca.registrarLibro(isbn, titulo, autor, genero, fecha));
    }

    static void registrarLector() {
        System.out.print("ID (carné o cédula): ");
        String id = scan.nextLine();
        System.out.print("Nombre: ");
        String nombre = scan.nextLine();
        System.out.print("Apellido: ");
        String apellido = scan.nextLine();
        System.out.print("Correo electrónico: ");
        String correo = scan.nextLine();
        System.out.println(biblioteca.registrarLector(id, nombre, apellido, correo));
    }

    static void prestarLibro() {
        System.out.print("ISBN del libro: ");
        String isbn = scan.nextLine();
        System.out.print("ID del lector: ");
        String idLector = scan.nextLine();
        System.out.println(biblioteca.registrarPrestamo(isbn, idLector));
    }

    static void devolverLibro() {
        System.out.print("ISBN del libro a devolver: ");
        String isbn = scan.nextLine();
        System.out.println(biblioteca.devolverLibro(isbn));
    }

    static void buscarLibroPorISBN() {
        System.out.print("ISBN: ");
        String isbn = scan.nextLine();
        Libro libro = biblioteca.buscarPorISBN(isbn);
        if (libro != null) System.out.println(libro);
        else System.out.println("Libro no encontrado.");
    }

    static void buscarLibroPorTitulo() {
        System.out.print("Título: ");
        String titulo = scan.nextLine();
        biblioteca.mostrarLibrosPorTitulo(titulo);
    }

    static void buscarLibroPorAutor() {
        System.out.print("Autor: ");
        String autor = scan.nextLine();
        biblioteca.mostrarLibrosPorAutor(autor);
    }

    static void editarLibro() {
        System.out.print("Ingrese ISBN del libro a editar: ");
        String isbn = scan.nextLine();
        Libro libro = biblioteca.buscarPorISBN(isbn);
        if (libro == null) {
            System.out.println("Libro no encontrado.");
            return;
        }
        System.out.println("Libro encontrado: " + libro);

        System.out.print("Nuevo título (enter para dejar igual): ");
        String titulo = scan.nextLine();
        if (!titulo.isEmpty()) libro.titulo = titulo;

        System.out.print("Nuevo autor (enter para dejar igual): ");
        String autor = scan.nextLine();
        if (!autor.isEmpty()) libro.autor = autor;

        System.out.print("Nuevo género (enter para dejar igual): ");
        String genero = scan.nextLine();
        if (!genero.isEmpty()) libro.genero = genero;

        System.out.print("Nueva fecha publicación yyyy-mm-dd (enter para dejar igual): ");
        String fecha = scan.nextLine();
        if (!fecha.isEmpty()) {
            try {
                libro.fechaPublicacion = LocalDate.parse(fecha);
            } catch (Exception e) {
                System.out.println("Fecha inválida, no se modificó.");
            }
        }

        System.out.println("Libro editado correctamente.");
    }

    static void eliminarLibro() {
        System.out.print("ISBN del libro a eliminar: ");
        String isbn = scan.nextLine();
        System.out.println(biblioteca.eliminarLibro(isbn));
    }


    static void reservarLibro() {
        System.out.print("ISBN del libro a reservar: ");
        String isbn = scan.nextLine();
        System.out.print("ID del lector: ");
        String idLector = scan.nextLine();
        System.out.println(biblioteca.registrarReserva(isbn, idLector));
    }
}
