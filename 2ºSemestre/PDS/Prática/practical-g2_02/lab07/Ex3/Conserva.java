package lab07.Ex3;

public class Conserva extends Item {

    public Conserva(String name, int weight) {
        super(name, weight);
    }

    @Override
    public String toString() {
        return "Conserva '" + getName() + "' - Weight : " + getWeight();
    }

    @Override
    public void draw() {
        System.out.println(sb.toString() + toString());
    }
}