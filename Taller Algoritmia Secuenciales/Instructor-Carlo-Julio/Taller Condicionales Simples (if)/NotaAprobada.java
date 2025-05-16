import java.util.Scanner;

public class NotaAprobada {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.print("Ingresá tu nota: ");
        float nota = entrada.nextFloat();

        if (nota >= 10) {
            System.out.println("Aprobaste");

            entrada.close();
        }
    }
}
// El código verifica si la nota ingresada por el usuario es mayor o igual a 10. Si lo es, imprime un mensaje indicando que aprobó.
// El código utiliza la clase Scanner para leer la entrada del usuario.
// El código también cierra el objeto Scanner al final para liberar los recursos utilizados.


