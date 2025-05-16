import java.util.Scanner;

public class SalarioConHorasExtras {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

// Pedimos al usuario las horas trabajadas y la tarifa por hora
      System.out.print("¿Cuántas horas trabajaste?: ");
        int horasTrabajadas = teclado.nextInt();  // Número de horas trabajadas

      System.out.print("¿Cuál es tu tarifa por hora?: ");
        double tarifaPorHora = teclado.nextDouble();  // Tarifa por cada hora trabajada


        double salarioTotal;

// Si trabajaste más de 40 horas, calculamos el salario normal y las horas extras
        if (horasTrabajadas > 40) {
// El salario por las primeras 40 horas (salario normal)
            double salarioNormal = 40 * tarifaPorHora;

// Las horas extras (trabajadas más de 40) se pagan al 150% de la tarifa
            double horasExtras = (horasTrabajadas - 40) * tarifaPorHora * 1.5;

// El salario total es la suma de salario normal y horas extras
            salarioTotal = salarioNormal + horasExtras;
        } else {
            
// Si no trabajaste más de 40 horas, el salario total es simplemente las horas trabajadas * tarifa
            salarioTotal = horasTrabajadas * tarifaPorHora;
        }

// Mostrar el salario con separador de miles y 2 decimales
          System.out.printf("Tu salario total es: %, .2f\n", salarioTotal);
    }
}

// Este programa calcula el salario total de un trabajador considerando horas extras.
// Si el trabajador trabaja más de 40 horas, las horas extras se pagan al 150% de la tarifa normal.
// Se utiliza la clase Scanner para leer los datos desde la entrada estándar.
// El salario total se calcula sumando el salario normal y el salario por horas extras si corresponde.
// La fórmula utilizada es:
// salario total = (horas trabajadas * tarifa por hora) + (horas extras * tarifa por hora * 1.5)
// Si el trabajador no supera las 40 horas, el salario total es simplemente horas trabajadas * tarifa por hora.








/*
Ejemplo para guiarme


 Voy a imaginar que trabaja 45 horas a una tarifa de $10 por hora.

Cálculos:

Horas trabajadas: 45 horas.
Tarifa por hora: $10.

Paso 1: Calcular salario normal (por las primeras 40 horas):
Salario normal = 40×10=400
40×10=400 dólares.

Paso 2: Calcular horas extras (por las 5 horas extras):
Horas extras = 5×10×1.5=75
5×10×1.5=75 dólares.

Paso 3: Sumar ambos salarios:
Salario total = 400+75=475
400+75=475 dólares.

Entonces, el salario total sería $475.
 */