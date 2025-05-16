import java.util.Scanner;

public class PromedioTresNumeros {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.print("Ingrese el primer número: ");
        double numero1 = teclado.nextDouble();

        System.out.print("Ingrese el segundo número  ");
        double numero2 = teclado.nextDouble();

        System.out.print("Ingrese el tercer número  ");
        double numero3 = teclado.nextDouble();

        double promedio = (numero1 + numero2 + numero3) / 3;

        // Mostrar el promedio con 2 decimales
        System.out.printf("El promedio es: %,.2f\n", promedio);
    }
}
// Este programa calcula el promedio de tres números ingresados por el usuario.
// Se utiliza la clase Scanner para leer los números desde la entrada estándar.
// La fórmula utilizada para calcular el promedio es: promedio = (numero1 + numero2 + numero3) / 3.
// El formato %,.2f indica que se desea mostrar el número con dos decimales y con separador de miles si es necesario.
// El promedio se calcula sumando los tres números y dividiendo el resultado entre 3.