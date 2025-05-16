import java.util.Scanner;

public class Hipotenusa {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.print("Ingrese el primer cateto: ");
        double cateto1 = teclado.nextDouble();

        System.out.print("Ingrese el segundo cateto: ");
        double cateto2 = teclado.nextDouble();

        double hipotenusa = Math.sqrt(cateto1 * cateto1 + cateto2 * cateto2);

        System.out.println("La hipotenusa es: " + hipotenusa);
    }
}

// Este programa calcula la hipotenusa de un triángulo rectángulo dados los dos catetos ingresados por el usuario.
// Se utiliza la clase Scanner para leer los catetos desde la entrada estándar.
// La fórmula utilizada para calcular la hipotenusa es: hipotenusa = √(cateto1² + cateto2²).
// La hipotenusa se calcula utilizando la función Math.sqrt() para obtener la raíz cuadrada de la suma de los cuadrados de los catetos.
// El resultado se imprime en la consola utilizando System.out.println().

/*
 
Ejemplo para guiarme

Cateto 1 = 3
Cateto 2 = 4

Ahora, vamos a usar la fórmula de Pitágoras
Elevamos al cuadrado cada cateto

3 elevado a la 2 = 9
4 elevado a la 2 = 16

Sumamos los resultados 
9 + 16 = 25

Sacamos la raíz cuadrada de la suma
Raiz cuadrda de 25 = 5

Entonces, la hipotenusa es 5.
 */