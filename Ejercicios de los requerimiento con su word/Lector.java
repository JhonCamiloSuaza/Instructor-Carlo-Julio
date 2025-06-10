import java.util.ArrayList;

public class Lector {
    public String id;             
    public String nombre;
    public String apellido;
    public String correo;
    public ArrayList<Prestamo> historial;

    public Lector(String id, String nombre, String apellido, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.historial = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "ID: " + id +
               ", Nombre: " + nombre + " " + apellido +
               ", Correo: " + correo;
    }
}
