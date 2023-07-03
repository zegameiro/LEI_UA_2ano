package lab09.Ex2;

public class DesertChef implements Kitchen {

    Kitchen nextKitchen;

    @Override
    public void setNextKitchen(Kitchen nextKitchen) {
        this.nextKitchen = nextKitchen;
    }

    @Override
    public void cook(Plate p) {
        if (p.getName().toLowerCase().equals("dessert")) {
            System.out.println("DesertChef: Starting to cook " + p.getName() + ". Out in 17 minutes.");
        } else {
            System.out.println("DesertChef: I can't cook that.");
            System.out.println("We're sorry but that request can't be satisfied by our service!");
            if (nextKitchen != null) {
                nextKitchen.cook(p);
            }
        }
    }
    
}
