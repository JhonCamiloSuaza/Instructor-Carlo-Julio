import java.util.Scanner;

public class AreaTriangulo {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in); // Para leer datos del usuario

        System.out.print("Ingrese la base del triángulo: ");
        double base = teclado.nextDouble(); // Leer la base

        System.out.print("Ingrese la altura del triángulo: ");
        double altura = teclado.nextDouble(); // Leer la altura

        double area = (base * altura) / 2; // Calcular el área del triángulo
        // Fórmula: área = (base * altura) / 2
        System.out.println("El área del triángulo es: " + area); // Mostrar el resultado
    }
}
// Este programa calcula el área de un triángulo dado su base y altura ingresadas por el usuario.
// Se utiliza la clase Scanner para leer los valores desde la entrada estándar.
// La fórmula utilizada para calcular el área es: área = (base * altura) / 2.
// El área de un triángulo se calcula multiplicando la base por la altura y dividiendo el resultado entre 2.