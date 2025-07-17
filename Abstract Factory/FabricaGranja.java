
public class FabricaGranja implements FabricaAnimales {
    public Animal crearAnimal() {
        return new Vaca();
    }
}
