import java.util.Scanner;

public class NumeroPositivo {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.print("Ingresá un número: ");
        int numero = entrada.nextInt();

        if (numero > 0) {
            System.out.println("El número es positivo.");

            entrada.close();
        }
    }
}

// El código verifica si un número ingresado por el usuario es positivo. Si lo es, imprime un mensaje indicando que el número es positivo.
// El código utiliza la clase Scanner para leer la entrada del usuario y una comparación simple para verificar si el número es mayor que 0.
// El código también cierra el objeto Scanner al final para liberar los recursos utilizados.