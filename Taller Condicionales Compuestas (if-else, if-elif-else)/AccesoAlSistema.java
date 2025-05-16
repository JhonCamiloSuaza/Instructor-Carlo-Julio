
import java.util.Scanner;

public class AccesoAlSistema { 
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        // Usuario y contraseña predefinidos
        String usuarioCorrecto = "camilo suaza";
        String claveCorrecta = "3145555";

        // Pedimos al usuario sus datos
        System.out.print("Usuario: ");
        String usuario = entrada.nextLine();

        System.out.print("Contraseña: ");
        String clave = entrada.nextLine();

        // Verificamos si coinciden con los valores correctos
        if (usuario.equals(usuarioCorrecto) && clave.equals(claveCorrecta)) {
            System.out.println("Acceso concedido");
        } else {
            System.out.println("Acceso denegado");

            entrada.close();
        }
    }
}
