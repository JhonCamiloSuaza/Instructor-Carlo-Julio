
import java.util.Scanner;

public class DivisionSegura { 
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.print("Número numerador: ");
        double numerador = entrada.nextDouble();

        System.out.print("Número denominador: ");
        double denominador = entrada.nextDouble();

        // Verificamos que el denominador no sea cero para evitar error

        if (denominador != 0) {
            System.out.println("Resultado: " + (numerador / denominador));
        } else 
    {
            System.out.println("No se puede dividir entre cero");

            entrada.close();
        }
    }
}
