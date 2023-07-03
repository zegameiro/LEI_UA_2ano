package lab07.Ex3;

import java.util.ArrayList;

public class Caixa extends Item {

    private ArrayList<Item> items;
    private double totalWeight;

    public Caixa(String name, int weight) {
        super(name, weight);
        this.items = new ArrayList<Item>();
    }

    public double getTotalWeight() {
        for(Item i : items) {
            if(i instanceof Caixa)
                totalWeight += ((Caixa) i).getTotalWeight();
            else
                totalWeight += i.getWeight();
        }
        return totalWeight;
    }

    public void add(Item item) {
        items.add(item);
    }    

    @Override
    public String toString() {
        return "* Caixa '" + getName() + "' [ Weight: " + getWeight() + "; Total: " + getTotalWeight() + " ]";
    }

    @Override
    public void draw() {
        System.out.println(sb.toString() + toString());
        sb.append("  ");

        for(Item i : items)
            i.draw();

        sb.delete(sb.length() - 2, sb.length()); // Remove the last two characters from the string buffer

    }

}
