
import java.util.Scanner;

public class NotaAcadémica { 
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.print("Ingresa tu nota: ");
        int nota = entrada.nextInt();

        // Clasificamos la nota según el rango
        if (nota < 10) {
            System.out.println("Desaprobado");

        } else if (nota <= 13) {
            System.out.println("Regular");

        } else if (nota <= 17) {
            System.out.println("Bueno");

        } else if (nota <= 20) {
            System.out.println("Excelente");

        } else {
            System.out.println("Nota no válida");

            entrada.close();
        }
    }
}
