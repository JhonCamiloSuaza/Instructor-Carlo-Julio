
import java.util.Scanner;

public class HoraDelDia {  
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.print("Ingresa la hora (0 a 23): ");
        int hora = entrada.nextInt();

        // Mostramos el momento del día según la hora
        if (hora >= 6 && hora <= 12) {
            System.out.println("Es de mañana");
        
        } else if (hora <= 18) {
            System.out.println("Es de tarde");

        } else if (hora <= 23) {
            System.out.println("Es de noche");

        } else if (hora >= 0 && hora < 6) {
            System.out.println("Es de madrugada");
            
        } else
        
        {
            System.out.println("Hora no válida");

            entrada.close();
        }
    }
}
