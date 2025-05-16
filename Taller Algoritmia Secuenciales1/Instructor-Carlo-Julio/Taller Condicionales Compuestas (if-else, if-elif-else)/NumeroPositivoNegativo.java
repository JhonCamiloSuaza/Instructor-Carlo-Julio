
import java.util.Scanner;

public class NumeroPositivoNegativo {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.print("Escribe un número: ");
        int numero = entrada.nextInt();

        // Verificamos si el número es positivo o negativo
        if (numero >= 0)
         {
            System.out.println("El número es positivo");
        } else 
        
        {
            System.out.println("El número es negativo");
        

            entrada.close();
        }
    }
}


/* 

Si el número es mayor o igual a 0 (numero >= 0)
El programa dice: "El número es positivo"

Si NO es mayor o igual a 0 (es decir, es menor que 0)
 Entra al bloque else, y el programa dice: "El número es negativo"
 
*/ 