import java.util.Scanner;

public class MayorDeEdad {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.print("Ingresá tu edad: ");
        int edad = entrada.nextInt();

        if (edad >= 18) {
            System.out.println("Eres mayor de edad.");

            entrada.close();
        }
    }
}

// El código verifica si la edad ingresada por el usuario es mayor o igual a 18. Si lo es, imprime un mensaje indicando que es mayor de edad.
// El código utiliza la clase Scanner para leer la entrada del usuario.
// El código también cierra el objeto Scanner al final para liberar los recursos utilizados.