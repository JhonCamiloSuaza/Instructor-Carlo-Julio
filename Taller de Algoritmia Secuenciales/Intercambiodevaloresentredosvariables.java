import java.util.Scanner;

public class Intercambiodevaloresentredosvariables {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.print("Ingrese el primer valor (A): ");
        int a = teclado.nextInt();

        System.out.print("Ingrese el segundo valor (B): ");
        int b = teclado.nextInt();

        // Intercambio sin usar una variable extra
        a = a + b;
        b = a - b;
        a = a - b;

        System.out.println("Después del intercambio:");
        System.out.println("Valor de A: " + a);
        System.out.println("Valor de B: " + b);
    }
}
// Este programa intercambia los valores de dos variables enteras (A y B) sin usar una variable extra.
// Se utiliza la clase Scanner para leer los valores desde la entrada estándar.
// El intercambio se realiza mediante operaciones aritméticas, sumando y restando los valores de A y B.
// Después del intercambio, se muestran los nuevos valores de A y B en la consola.


/*

Ejemplo para Guiarme

A = 5

B = 3



a = a + b → 5 + 3 = 8

b = a - b → 8 - 3 = 5 → ahora b = 5

a = a - b → 8 - 5 = 3 → ahora a = 3


 */
