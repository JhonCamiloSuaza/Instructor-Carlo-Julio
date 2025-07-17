public class FabricaSelva implements FabricaAnimales {
    public Animal crearAnimal() {
        return new Tigre();
    }
}
