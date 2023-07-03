package lab09.Ex2;

public class SushiChef implements Kitchen {

    private Kitchen nextKitchen;

    @Override
    public void setNextKitchen(Kitchen nextKitchen) {
        this.nextKitchen = nextKitchen;
    }

    @Override
    public void cook(Plate p) {
        if (p.getName().toLowerCase().contains("sushi")) {
            System.out.println("SushiChef: Starting to cook " + p.getName() + ". Out in 14 minutes.");
        } else {
            System.out.println("SushiChef: I can't cook that.");
            if (nextKitchen != null) {
                nextKitchen.cook(p);
            }
        }
    }
    
}
