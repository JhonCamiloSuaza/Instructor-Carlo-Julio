import java.time.LocalDate;

public class Prestamo {
    public Libro libro;
    public Lector lector;
    public LocalDate fechaPrestamo;
    public LocalDate fechaDevolucion;
    public boolean devuelto;

    public Prestamo(Lector lector, Libro libro) {
        this.libro = libro;
        this.lector = lector;
        this.fechaPrestamo = LocalDate.now();
        this.fechaDevolucion = fechaPrestamo.plusDays(14); 
        this.devuelto = false;
    }

    public void marcarDevuelto() {
        this.devuelto = true;
        this.libro.prestado = false;
    }

    @Override
    public String toString() {
        return "Libro: " + libro.titulo +
               ", Lector: " + lector.nombre +
               ", Prestado: " + fechaPrestamo +
               ", Devolución: " + fechaDevolucion +
               (devuelto ? ", Devuelto" : ", No devuelto");
    }
}
