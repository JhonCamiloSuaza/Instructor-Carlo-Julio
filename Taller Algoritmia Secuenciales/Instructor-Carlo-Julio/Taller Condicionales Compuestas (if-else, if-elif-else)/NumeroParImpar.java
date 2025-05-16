
import java.util.Scanner;

public class NumeroParImpar {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.print("Escribe un número: ");
        int numero = entrada.nextInt();

        // Verificamos si el número es par o impar
        if (numero % 2 == 0) {
            System.out.println("Es par");
        } 
        
        else {
            System.out.println("Es impar");

            entrada.close();
        }
    }
}
