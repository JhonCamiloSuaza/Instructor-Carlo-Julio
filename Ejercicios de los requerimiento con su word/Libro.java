import java.time.LocalDate;

public class Libro {
    public String isbn;
    public String titulo;
    public String autor;
    public String genero;
    public LocalDate fechaPublicacion;
    public boolean prestado;

    public Libro(String isbn, String titulo, String autor, String genero, String fecha) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.fechaPublicacion = LocalDate.parse(fecha);
        this.prestado = false;
    }

    @Override
    public String toString() {
        return "ISBN: " + isbn + 
            ", Título: " + titulo + 
            ", Autor: " + autor + 
            ", Género: " + genero +
            ", Publicado: " + fechaPublicacion + 
            ", Prestado: " + (prestado ? "Sí" : "No");

            
    }
}
