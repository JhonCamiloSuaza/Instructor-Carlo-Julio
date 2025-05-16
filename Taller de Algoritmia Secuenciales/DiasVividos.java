import java.util.Scanner;

public class DiasVividos {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.print("Ingrese su edad en años: ");
        int edad = teclado.nextInt(); // Leer la edad

        int diasVividos = edad * 365; // Suponiendo 365 días por año (sin contar bisiestos)

        // Calcular la cantidad de días vividos
        // Fórmula: días vividos = edad * 365
        System.out.printf("Has vivido aproximadamente %,d días.\n", diasVividos);
    }
}
// Este programa calcula la cantidad de días vividos por una persona en función de su edad en años.
// Se asume que un año tiene 365 días, sin considerar los años bisiestos.
// El resultado se muestra en la consola utilizando System.out.println().
// La lógica es simple: multiplicar la edad por 365 para obtener el total de días vividos.
// Se utiliza la clase Scanner para leer la edad desde la entrada estándar.

/*
 No considera años bisiestos ni días exactos de nacimiento
 */
