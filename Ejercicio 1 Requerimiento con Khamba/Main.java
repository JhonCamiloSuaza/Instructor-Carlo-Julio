public class Main {
    public static void main(String[] args) {
        GestorUsuario gestor = new GestorUsuario();
        java.util.Scanner sc = new java.util.Scanner(System.in);

        int opcion;

        do {
            System.out.println("MENÚ ");
            System.out.println("1. Registrar usuario Ej Contaseña Fuerte Hola123@");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Editar perfil por ID");
            System.out.println("4. Cambiar contraseña por ID");
            System.out.println("5. Ver historial por ID");
            System.out.println("6. Ver todos los usuarios");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 -> gestor.registrarUsuario();
                case 2 -> gestor.iniciarSesion();
                case 3 -> gestor.editarPerfil();
                case 4 -> gestor.cambiarContrasena();
                case 5 -> gestor.verHistorial();
                case 6 -> gestor.mostrarUsuarios();
                case 0 -> System.out.println(" Saliendo del sistema...");
                default -> System.out.println(" Opción inválida.");
            }

        } while (opcion != 0);

        sc.close();
    }
}
