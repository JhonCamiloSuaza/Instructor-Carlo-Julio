
import java.util.Scanner;

public class NumeroMayor { 
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.print("Número 1: ");
        int numero1 = entrada.nextInt();

        System.out.print("Número 2: ");
        int numero2 = entrada.nextInt();


        if (numero1 > numero2) {
            System.out.println("El número 1 es mayor");
        } 
        
        else if (numero2 > numero1) {
            System.out.println("El número 2 es mayor");
        } 
        else {
            
            System.out.println("Los dos números son iguales");

            entrada.close();
        }
    }
}
