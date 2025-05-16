import java.util.Scanner;

public class MultiploDeCinco {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.print("Ingresá un número: ");
        int numero = entrada.nextInt();

        if (numero % 5 == 0) {
            System.out.println("Es múltiplo de 5.");

            entrada.close();
        }
    }
}
// El código verifica si un número ingresado por el usuario es múltiplo de 5. Si lo es, imprime un mensaje indicando que es múltiplo de 5.
// El código utiliza la clase Scanner para leer la entrada del usuario y el operador módulo (%) para verificar la divisibilidad.
// El operador módulo devuelve el residuo de la división entre el número ingresado y 5. Si el residuo es 0, significa que el número es múltiplo de 5.
// El código también cierra el objeto Scanner al final para liberar los recursos utilizados.