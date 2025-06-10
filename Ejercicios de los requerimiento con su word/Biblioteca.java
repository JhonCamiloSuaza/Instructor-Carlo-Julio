import java.time.LocalDate;
import java.util.ArrayList;

public class Biblioteca {
    ArrayList<Libro> libros = new ArrayList<>();
    ArrayList<Lector> lectores = new ArrayList<>();
    ArrayList<Prestamo> prestamos = new ArrayList<>();
    ArrayList<Reserva> reservas = new ArrayList<>();  // <-- Agregado para manejar reservas

    public String registrarLibro(String isbn, String titulo, String autor, String genero, String fecha) {
        for (Libro l : libros) {
            if (l.isbn.equals(isbn)) return "Error: ISBN ya registrado.";
        }
        libros.add(new Libro(isbn, titulo, autor, genero, fecha));
        return "Libro registrado correctamente.";
    }

    public Libro buscarPorTitulo(String titulo) {
        for (Libro libro : libros) {
            if (libro.titulo.equalsIgnoreCase(titulo)) {
                return libro;
            }
        }
        return null;
    }

    public Libro buscarPorISBN(String isbn) {
        for (Libro libro : libros) {
            if (libro.isbn.equalsIgnoreCase(isbn)) {
                return libro;
            }
        }
        return null;
    }

    public void mostrarLibrosPorAutor(String autor) {
        for (Libro libro : libros) {
            if (libro.autor.equalsIgnoreCase(autor)) {
                System.out.println(libro);
            }
        }
    }

    public String registrarLector(String id, String nombre, String apellido, String correo) {
        for (Lector l : lectores) {
            if (l.id.equals(id)) return "Error: Lector ya registrado.";
        }
        lectores.add(new Lector(id, nombre, apellido, correo));
        return "Lector registrado correctamente.";
    }

    public String registrarPrestamo(String isbn, String idLector) {
        Libro libro = buscarPorISBN(isbn);
        if (libro == null || libro.prestado) return "Error: Libro no disponible.";

        for (Lector lector : lectores) {
            if (lector.id.equals(idLector)) {
                Prestamo p = new Prestamo(lector, libro);
                prestamos.add(p);
                libro.prestado = true;
                lector.historial.add(p);
                return "Préstamo registrado correctamente.";
            }
        }
        return "Error: Lector no encontrado.";
    }

    public void listarPrestamos() {
        for (Prestamo p : prestamos) {
            System.out.println(p);
        }
    }

    public String devolverLibro(String isbn) {
        for (Prestamo p : prestamos) {
            if (p.libro.isbn.equalsIgnoreCase(isbn) && !p.devuelto) {
                p.marcarDevuelto();
                return "Libro devuelto correctamente.";
            }
        }
        return "Error: Libro no encontrado o ya fue devuelto.";
    }

    public void mostrarLibrosPorTitulo(String filtro) {
        for (Libro libro : libros) {
            if (libro.titulo.toLowerCase().contains(filtro.toLowerCase())) {
                System.out.println(libro);
            }
        }
    }

    public void mostrarLectorPorId(String id) {
        for (Lector lector : lectores) {
            if (lector.id.equals(id)) {
                System.out.println(lector);
            }
        }
    }

    public void listarTodosLosLibros() {
        for (Libro libro : libros) {
            System.out.println(libro);
        }
    }

    public void listarTodosLosLectores() {
        for (Lector lector : lectores) {
            System.out.println(lector);
        }
    }

   
    public String eliminarLibroPorTitulo(String titulo) {
        for (int i = 0; i < libros.size(); i++) {
            Libro libro = libros.get(i);
            if (libro.titulo.equalsIgnoreCase(titulo)) {
                if (libro.prestado) return "Error: No se puede eliminar un libro prestado.";
                libros.remove(i);
                return "Libro eliminado.";
            }
        }
        return "Error: Libro no encontrado.";
    }

    
    public String editarLibroPorTitulo(String titulo, java.util.Scanner scan) {
        Libro libro = buscarPorTitulo(titulo);
        if (libro == null) return "Libro no encontrado.";

        System.out.print("Nuevo autor: ");
        libro.autor = scan.nextLine();
        System.out.print("Nuevo genero: ");
        libro.genero = scan.nextLine();
        System.out.print("Nueva fecha publicacion (yyyy-mm-dd): ");
        libro.fechaPublicacion = LocalDate.parse(scan.nextLine());
        return "Libro editado correctamente.";
    }

    
    public String eliminarLectorPorId(String id) {
        for (int i = 0; i < lectores.size(); i++) {
            Lector lector = lectores.get(i);
            if (lector.id.equals(id)) {
                lectores.remove(i);
                return "Lector eliminado.";
            }
        }
        return "Lector no encontrado.";
    }

    public String editarLectorPorId(String id, java.util.Scanner scan) {
        for (Lector lector : lectores) {
            if (lector.id.equals(id)) {
                System.out.print("Nuevo nombre: ");
                lector.nombre = scan.nextLine();
                System.out.print("Nuevo apellido: ");
                lector.apellido = scan.nextLine();
                System.out.print("Nuevo correo: ");
                lector.correo = scan.nextLine();
                return "Lector editado correctamente.";
            }
        }
        return "Lector no encontrado.";
    }

    public void mostrarHistorialPrestamosPorLector(String id) {
        for (Lector lector : lectores) {
            if (lector.id.equals(id)) {
                for (Prestamo p : lector.historial) {
                    System.out.println(p);
                }
            }
        }
    }

    public void mostrarPrestamosVencidos() {
        LocalDate hoy = LocalDate.now();
        for (Prestamo p : prestamos) {
            if (!p.devuelto && p.fechaDevolucion.isBefore(hoy)) {
                System.out.println("VENCIDO: " + p);
            }
        }
    }

    
    public String registrarReserva(String isbn, String idLector) {
        Libro libro = buscarPorISBN(isbn);
        if (libro == null) return "Error: Libro no encontrado.";

        Lector lector = null;
        for (Lector l : lectores) {
            if (l.id.equals(idLector)) {
                lector = l;
                break;
            }
        }
        if (lector == null) return "Error: Lector no encontrado.";

       
        if (!libro.prestado) {
            return "Error: El libro está disponible, no es necesario reservar.";
        }

        
        for (Reserva r : reservas) {
            if (r.libro.isbn.equals(isbn) && r.lector.id.equals(idLector)) {
                return "Error: Reserva ya existe para este lector y libro.";
            }
        }

        Reserva reserva = new Reserva(libro, lector);
        reservas.add(reserva);
        return "Reserva registrada correctamente.";
    }

    public void listarReservas() {
        if (reservas.isEmpty()) {
            System.out.println("No hay reservas registradas.");
            return;
        }
        for (Reserva r : reservas) {
            System.out.println(r);
        }
    }

    public void verLibrosDisponibles() {
        for (Libro libro : libros) {
            if (!libro.prestado) System.out.println(libro);
        }
    }

    public void verLibrosPrestados() {
        for (Libro libro : libros) {
            if (libro.prestado) System.out.println(libro);
        }
    }

    public void verLectores() {
        listarTodosLosLectores();
    }

    public void mostrarHistorialPrestamos() {
        for (Prestamo p : prestamos) {
            System.out.println(p);
        }
    }

    public String editarLibro(String isbn, String titulo, String autor, String genero, String fecha) {
        Libro libro = buscarPorISBN(isbn);
        if (libro == null) return "Error: Libro no encontrado.";
        libro.titulo = titulo;
        libro.autor = autor;
        libro.genero = genero;
        libro.fechaPublicacion = LocalDate.parse(fecha);
        return "Libro editado correctamente.";
    }

    public String eliminarLibro(String isbn) {
        Libro libro = buscarPorISBN(isbn);
        if (libro == null) return "Error: Libro no encontrado.";
        if (libro.prestado) return "Error: El libro está prestado y no puede ser eliminado.";
        libros.remove(libro);
        return "Libro eliminado correctamente.";
    }
}
