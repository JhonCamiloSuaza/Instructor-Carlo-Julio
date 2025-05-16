import java.util.Scanner;

public class CelsiusAFahrenheit {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in); // Crear el lector del teclado

    System.out.print("Ingrese la temperatura en grados Celsius: ");
        double gradosCelsius = teclado.nextDouble(); // Leer temperatura en Celsius

        double gradosFahrenheit = gradosCelsius * 9 / 5 + 32; // Aplicar fórmula
    System.out.println("La temperatura en Fahrenheit es: " + gradosFahrenheit); // Mostrar resultado
    }
}
// Este programa convierte una temperatura de grados Celsius a grados Fahrenheit.
// Se utiliza la clase Scanner para leer la temperatura desde la entrada estándar.
// La fórmula utilizada para la conversión es: grados Fahrenheit = (grados Celsius * 9/5) + 32.
// La conversión se realiza multiplicando la temperatura en Celsius por 9/5 y luego sumando 32 para obtener el resultado en Fahrenheit.
