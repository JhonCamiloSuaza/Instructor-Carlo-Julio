
import java.util.Scanner;

public class MayorOMenorDeEdad { 
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        // le Pedimos al usuario que ingrese su edad
        System.out.print("Ingresa tu edad: ");
        int edad = entrada.nextInt();

        // Comprobar si es mayor o menor de edad
        if (edad >= 18) {
            System.out.println("Eres mayor de edad");
        } 
        
        else  {
            System.out.println("Eres menor de edad");

            entrada.close();
        }
    }
}
