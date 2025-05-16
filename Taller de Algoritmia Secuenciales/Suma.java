import java.util.Scanner;

public class Suma {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in); // Crear el lector del teclado

  System.out.print("Ingrese el primer número: ");
        int primerNumero = teclado.nextInt(); // Leer el primer número

  System.out.print("Ingrese el segundo número: ");
        int segundoNumero = teclado.nextInt(); // Leer el segundo número

        int resultado = primerNumero + segundoNumero; // Calcular la suma
  System.out.println("La suma es: " + resultado); // Mostrar el resultado
    }
}
// Este programa suma dos números enteros ingresados por el usuario.
// Se utiliza la clase Scanner para leer los números desde la entrada estándar.
// El resultado se imprime en la consola utilizando System.out.println().