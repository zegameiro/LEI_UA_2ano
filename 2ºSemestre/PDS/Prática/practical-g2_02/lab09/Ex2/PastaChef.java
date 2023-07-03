package lab09.Ex2;

public class PastaChef implements Kitchen {

    private Kitchen nextKitchen;

    @Override
    public void setNextKitchen(Kitchen nextKitchen) {
        this.nextKitchen = nextKitchen;
    }

    @Override
    public void cook(Plate p) {
        if (p.getName().toLowerCase().contains("pasta")) {
            System.out.println("PastaChef: Starting to cook " + p.getName() + ". Out in 10 minutes.");
        } else {
            System.out.println("PastaChef: I can't cook that.");
            if (nextKitchen != null) {
                nextKitchen.cook(p);
            }
        }
    }
    
}
