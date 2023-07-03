package lab05.Ex1;

public class PlasticBottle extends Container {

    public PlasticBottle(Portion portion) {
        super(portion);
    }

    public String toString() {
        return "PlasticBottle with portion = " + getPortion().toString();
    }
    
}

