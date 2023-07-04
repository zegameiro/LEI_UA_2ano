package lab05.Ex1;

public class PlasticBag extends Container {

    public PlasticBag(Portion portion) {
        super(portion);
    }

    public String toString() {
        return "PlasticBag with portion = " + getPortion().toString();
    }
    
}
