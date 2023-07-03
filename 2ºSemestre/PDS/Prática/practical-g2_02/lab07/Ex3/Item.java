package lab07.Ex3;

public abstract class Item implements Composite {
    
    private String name;
    private double weight;
    static StringBuffer sb = new StringBuffer();

    public Item(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public abstract void draw();

}