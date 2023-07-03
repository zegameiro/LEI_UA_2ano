package lab09.Ex2;

import java.util.ArrayList;

public class KitchenChain_main {

    private Kitchen firstKitchen;

    public KitchenChain_main() {
        //inicializar a cadeia de cozinhas
        this.firstKitchen = new SushiChef();
        Kitchen secondKitchen = new PastaChef();
        Kitchen thirdKitchen = new BurgerChef();
        Kitchen fourthKitchen = new PizzaChef();
        Kitchen fifthKitchen = new DesertChef();

        //defenir a ordem da cadeia
        firstKitchen.setNextKitchen(secondKitchen);
        secondKitchen.setNextKitchen(thirdKitchen);
        thirdKitchen.setNextKitchen(fourthKitchen);
        fourthKitchen.setNextKitchen(fifthKitchen);
    }

    public static void main(String[] args) {

        
        KitchenChain_main chain = new KitchenChain_main();

        ArrayList <String> orders = new ArrayList<>();

        orders.add("Can I please get a veggie burger?");
        orders.add("Can I please get a Pasta Carbonara?");
        orders.add("Can I please get a PLAIN pizza, no toppings!?");
        orders.add("Can I please get a sushi nigiri and sashimi?");
        orders.add("Can I please get a salad with tuna?");
        orders.add("Can I please get a strawberry ice cream and waffles dessert?");
        
        for (String order : orders) {
            System.out.println("Customer: " + order);
            chain.firstKitchen.cook(new Plate(order));
            System.out.println();
        }

    }
    
}
