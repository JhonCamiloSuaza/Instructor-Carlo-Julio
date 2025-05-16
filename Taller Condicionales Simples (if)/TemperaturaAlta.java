import java.util.Scanner;

public class TemperaturaAlta {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.print("Ingresá la temperatura en °C: ");
        float temp = entrada.nextFloat();

        if (temp > 30) {
            System.out.println("Hace calor (más de 30°C).");

            entrada.close();
        }
    }
}
// El código verifica si la temperatura ingresada por el usuario es mayor a 30 grados Celsius. Si lo es, imprime un mensaje indicando que hace calor.
// El código utiliza la clase Scanner para leer la entrada del usuario.
// El código también cierra el objeto Scanner al final para liberar los recursos utilizados.
