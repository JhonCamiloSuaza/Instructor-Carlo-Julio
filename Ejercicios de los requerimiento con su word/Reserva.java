import java.time.LocalDate;

public class Reserva {
    public Libro libro;
    public Lector lector;
    public LocalDate fechaReserva;

    public Reserva(Libro libro, Lector lector) {
        this.libro = libro;
        this.lector = lector;
        this.fechaReserva = LocalDate.now();
    }

    @Override
    public String toString() {
        return "Libro: " + libro.titulo + ", Reservado por: " + lector.nombre + ", Fecha: " + fechaReserva;
    }
}
