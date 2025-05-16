import java.util.Scanner;

public class SalarioAlto {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.print("Ingresá tu salario mensual: ");
        double salario = entrada.nextDouble();

        if (salario > 3000) {
            System.out.println("Tienes un salario alto.");

            entrada.close();
        }
    }
}

// El código verifica si el salario ingresado por el usuario es mayor a 3000. Si lo es, imprime un mensaje indicando que tiene un salario alto.
// El código utiliza la clase Scanner para leer la entrada del usuario.
// El código también cierra el objeto Scanner al final para liberar los recursos utilizados.