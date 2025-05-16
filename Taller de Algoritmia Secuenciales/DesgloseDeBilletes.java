import java.util.Scanner;

public class DesgloseDeBilletes {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.print("Ingrese una cantidad de dinero: ");
        int cantidad = teclado.nextInt(); // Leer la cantidad total

        int billetes100 = cantidad / 100;
        cantidad = cantidad % 100;

        int billetes50 = cantidad / 50;
        cantidad = cantidad % 50;

        int billetes20 = cantidad / 20;
        cantidad = cantidad % 20;

        int billetes10 = cantidad / 10;
        cantidad = cantidad % 10;

        int billetes5 = cantidad / 5;
        cantidad = cantidad % 5;

        int billetes1 = cantidad; // Lo que queda son billetes de 1

        System.out.println("Billetes de 100: " + billetes100);
        System.out.println("Billetes de 50: " + billetes50);
        System.out.println("Billetes de 20: " + billetes20);
        System.out.println("Billetes de 10: " + billetes10);
        System.out.println("Billetes de 5: " + billetes5);
        System.out.println("Billetes de 1: " + billetes1);
    }
}
// Este programa desglosa una cantidad de dinero en billetes de diferentes denominaciones (100, 50, 20, 10, 5 y 1).
// Se utiliza la clase Scanner para leer la cantidad total desde la entrada estándar.
// Luego, se calcula cuántos billetes de cada denominación se necesitan y se muestra el resultado en la consola.
// La lógica se basa en la división entera y el operador módulo para obtener el número de billetes y la cantidad restante, respectivamente.
// El desglose se realiza de mayor a menor denominación, asegurando que se utilicen la menor cantidad de billetes posible.
