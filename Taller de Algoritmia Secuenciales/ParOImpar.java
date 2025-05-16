import java.util.Scanner;

public class ParOImpar {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.print("Ingrese un número entero: ");
        int numero = teclado.nextInt(); // Leer número entero

        if (numero % 2 == 0) {
            System.out.println("El número es par.");
        } else {
            System.out.println("El número es impar.");
        }
    }
}
// Este programa determina si un número entero ingresado por el usuario es par o impar.
// Se utiliza la clase Scanner para leer el número desde la entrada estándar.
// La condición para determinar si un número es par o impar se basa en el resto de la división entre 2.
// Si el resto es 0, el número es par; de lo contrario, es impar.

/*
 Un número es par si al dividirlo entre 2 el resto es 0:
numero % 2 == 0 → par
numero % 2 != 0 → impar
 */