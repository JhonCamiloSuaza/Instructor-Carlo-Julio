import java.util.Scanner;

public class NumeroPar {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.print("Ingresá un número: ");
        int numero = entrada.nextInt();

        if (numero % 2 == 0) {
            System.out.println("Es un número par.");

            entrada.close();
        }
    }
}
// El código verifica si un número ingresado por el usuario es par. Si lo es, imprime un mensaje indicando que es un número par.
// El código utiliza la clase Scanner para leer la entrada del usuario y el operador módulo (%) para verificar si el número es par.
// El operador módulo devuelve el residuo de la división entre el número ingresado y 2. Si el residuo es 0, significa que el número es par.
// El código también cierra el objeto Scanner al final para liberar los recursos utilizados.