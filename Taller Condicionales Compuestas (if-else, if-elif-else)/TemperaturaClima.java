
import java.util.Scanner;

public class TemperaturaClima {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.print("Ingresa la temperatura: ");
        int temperatura = entrada.nextInt();

        // Evaluamos la temperatura para saber el clima
        if (temperatura < 15) {
            System.out.println("Hace frío");

        }
         else if (temperatura <= 30)
          {
            System.out.println("Clima normal");
        
        } else {
            System.out.println("Hace calor");

            entrada.close();
        }
    }
}
