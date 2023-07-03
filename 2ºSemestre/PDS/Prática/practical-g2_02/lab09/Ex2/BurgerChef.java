package lab09.Ex2;

public class BurgerChef implements Kitchen {

    private Kitchen nextKitchen;

    @Override
    public void setNextKitchen(Kitchen nextKitchen) {
        this.nextKitchen = nextKitchen;
    }

    @Override
    public void cook(Plate p) {
        if (p.getName().toLowerCase().contains("burger")) {
            System.out.println("BurgerChef: Starting to cook " + p.getName() + ". Out in 19 minutes.");
        } else {
            System.out.println("BurgerChef: I can't cook that.");
            if (nextKitchen != null) {
                nextKitchen.cook(p);
            }
        }
    }
    
}
