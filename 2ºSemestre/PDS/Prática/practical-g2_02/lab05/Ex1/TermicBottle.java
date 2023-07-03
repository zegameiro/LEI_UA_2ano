package lab05.Ex1;

public class TermicBottle extends Container {

    public TermicBottle(Portion portion) {
        super(portion);
    }

    public String toString() {
        return "TermicBottle with portion = " + getPortion().toString();
    }
    
}
