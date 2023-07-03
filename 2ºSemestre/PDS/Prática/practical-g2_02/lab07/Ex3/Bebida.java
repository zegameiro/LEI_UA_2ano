package lab07.Ex3;

public class Bebida extends Item {

    public Bebida(String name, int weight) {
        super(name, weight);
    }
    
    @Override
    public String toString() {
        return "Bebida '" + getName() + "' - Weight : " + getWeight();
    }

    @Override
    public void draw() {
        System.out.println(sb.toString() + toString());
    }
    
}