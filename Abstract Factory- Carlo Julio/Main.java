import java.util.Scanner; 

public class Main {
    public static void main(String[] args) {
        Scanner Teclado = new Scanner(System.in); 
        FabricaAnimales fabrica;

        System.out.println("Escribe el tipo de animal que deseas (selva o granja):");
        String tipo = Teclado.nextLine().toLowerCase(); 
        if (tipo.equals("selva")) {
            fabrica = new FabricaSelva();
        } else if (tipo.equals("granja")) {
            fabrica = new FabricaGranja(); 
        } else {
            System.out.println("Opción no válida.");
            return; 
        }

        Animal animal = fabrica.crearAnimal();
        animal.sonido();

    }
}
