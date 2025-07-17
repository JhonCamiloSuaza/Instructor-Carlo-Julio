import java.util.ArrayList;

public class Usuario {
    public int id;
    public String nombre;
    public String correo;
    public String contrasena;
    public ArrayList<String> historial;

    private static int contador = 1;

    public Usuario(String nombre, String correo, String contrasena) {
        this.id = contador;
        contador++;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.historial = new ArrayList<>();
    }

    public void agregarHistorial(String mensaje) {
        historial.add(mensaje);
    }
}
