import java.util.ArrayList;
import java.util.Scanner;

public class GestorUsuario {
    public ArrayList<Usuario> usuarios = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public void registrarUsuario() {
        System.out.print("Correo: ");
        String correo = sc.nextLine();

        if (!correo.contains("@") || !correo.contains(".")) {
            System.out.println("Correo inválido.");
            return;
        }

        for (Usuario u : usuarios) {
            if (u.correo.equals(correo)) {
                System.out.println(" Correo ya registrado.");
                return;
            }
        }

        System.out.print("Contraseña: ");
        String pass = sc.nextLine();
        if (pass.length() < 6 || !pass.matches(".*[^a-zA-Z0-9].*")) {
            System.out.println(" Contraseña débil.");
            return;
        }

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        Usuario nuevo = new Usuario(nombre, correo, pass);
        usuarios.add(nuevo);
        System.out.println(" Usuario registrado con ID: " + nuevo.id);
    }

    public void iniciarSesion() {
        System.out.print("Correo: ");
        String correo = sc.nextLine();
        System.out.print("Contraseña: ");
        String pass = sc.nextLine();

        for (Usuario u : usuarios) {
            if (u.correo.equals(correo)) {
                if (u.contrasena.equals(pass)) {
                    System.out.println(" Inicio exitoso. Bienvenido " + u.nombre + " (ID: " + u.id + ")");
                } else {
                    System.out.println(" Contraseña incorrecta.");
                }
                return;
            }
        }

        System.out.println(" Usuario no encontrado.");
    }

    public void editarPerfil() {
        System.out.print("Ingrese ID del usuario: ");
        int id = Integer.parseInt(sc.nextLine());

        Usuario u = buscarPorId(id);
        if (u == null) {
            System.out.println(" Usuario no encontrado.");
            return;
        }

        System.out.print("Nuevo nombre: ");
        String nuevoNombre = sc.nextLine();
        System.out.print("Nuevo correo: ");
        String nuevoCorreo = sc.nextLine();

        if (!nuevoNombre.equals(u.nombre)) {
            u.nombre = nuevoNombre;
            u.agregarHistorial("Cambió nombre.");
        }

        if (!nuevoCorreo.equals(u.correo)) {
            u.correo = nuevoCorreo;
            u.agregarHistorial("Cambió correo.");
        }

        System.out.println(" Perfil actualizado.");
    }

    public void cambiarContrasena() {
        System.out.print("ID del usuario: ");
        int id = Integer.parseInt(sc.nextLine());

        Usuario u = buscarPorId(id);
        if (u == null) {
            System.out.println(" Usuario no encontrado.");
            return;
        }

        System.out.print("Contraseña actual: ");
        String actual = sc.nextLine();

        if (!u.contrasena.equals(actual)) {
            System.out.println(" Contraseña incorrecta.");
            return;
        }

        System.out.print("Nueva contraseña: ");
        String nueva = sc.nextLine();
        if (nueva.length() < 6 || !nueva.matches(".*[^a-zA-Z0-9].*")) {
            System.out.println(" Contraseña débil.");
            return;
        }

        u.contrasena = nueva;
        u.agregarHistorial("Cambió contraseña.");
        System.out.println(" Contraseña actualizada.");
    }

    public void verHistorial() {
        System.out.print("ID del usuario: ");
        int id = Integer.parseInt(sc.nextLine());

        Usuario u = buscarPorId(id);
        if (u == null) {
            System.out.println(" Usuario no encontrado.");
            return;
        }

        if (u.historial.isEmpty()) {
            System.out.println(" No hay historial.");
        } else {
            System.out.println(" Historial del usuario " + u.nombre + ":");
            for (String h : u.historial) {
                System.out.println("- " + h);
            }
        }
    }

    public void mostrarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println(" No hay usuarios.");
        } else {
            System.out.println(" Lista de usuarios:");
            for (Usuario u : usuarios) {
                System.out.println("ID: " + u.id + " | " + u.nombre + " | " + u.correo);
            }
        }
    }

    public Usuario buscarPorId(int id) {
        for (Usuario u : usuarios) {
            if (u.id == id) return u;
        }
        return null;
    }
}
