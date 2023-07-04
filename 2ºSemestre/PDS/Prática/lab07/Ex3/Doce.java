package lab07.Ex3;

public class Doce extends Item{

    public Doce(String name, int weight) {
        super(name, weight);
    }

    public String toString() {
        return "Doce '" + getName() + "' - Weight : " + getWeight();
    }

    @Override
    public void draw() {
        System.out.println(sb.toString() + toString());
    }   
}