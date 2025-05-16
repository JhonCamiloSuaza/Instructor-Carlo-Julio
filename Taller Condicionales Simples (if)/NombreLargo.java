import java.util.Scanner;

public class NombreLargo {
    public static void main(String[] args) {       
        Scanner entrada = new Scanner(System.in);

        System.out.print("Ingresá tu nombre: ");
        String nombre = entrada.nextLine();

        if (nombre.length() > 8) {
            System.out.println("Tu nombre es largo.");

            entrada.close();
        }
    }
}
// El código verifica si el nombre ingresado por el usuario tiene más de 8 caracteres. Si lo tiene, imprime un mensaje indicando que el nombre es largo.
// El código utiliza la clase Scanner para leer la entrada del usuario y el método length() de la clase String para obtener la longitud del nombre.
// length() es un método que se usa para saber cuántos caracteres (letras, números, espacios, etc.) tiene una cadena de texto
// El código también cierra el objeto Scanner al final para liberar los recursos utilizados.
