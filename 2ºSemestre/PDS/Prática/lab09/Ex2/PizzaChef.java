package lab09.Ex2;

public class PizzaChef implements Kitchen {

    Kitchen nextKitchen;

    @Override
    public void setNextKitchen(Kitchen nextKitchen) {
        this.nextKitchen = nextKitchen;
    }

    @Override
    public void cook(Plate p) {
        if (p.getName().toLowerCase().contains("pizza")) {
            System.out.println("PizzaChef: Starting to cook " + p.getName() + ". Out in 7 minutes.");
        } else {
            System.out.println("PizzaChef: I can't cook that.");
            if (nextKitchen != null) {
                nextKitchen.cook(p);
            }
        }
    }
    
}
