
import java.util.Scanner;

public class DescuentoPorCompra { 
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.print("Total de la compra: ");
        double total = entrada.nextDouble();

        // Si supera 100,  se le aplica 10% de descuento
        if (total > 100) {
            total = total * 0.9; // 10% de descuento es esta linea
        }

        System.out.println("Total a pagar: " + total);

        entrada.close();
    }
}
