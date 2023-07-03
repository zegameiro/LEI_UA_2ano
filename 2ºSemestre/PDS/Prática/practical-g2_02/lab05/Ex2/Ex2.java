package lab05.Ex2;

public class Ex2 {

    public static void main(String[] args) {
        CakeMaster cakeMaster = new CakeMaster();

        CakeBuilder chocolate = new ChocolateCakeBuilder();
        cakeMaster.setCakeBuilder(chocolate);
        cakeMaster.createCake("Congradulations");               // 1 cake layer
        Cake cake = cakeMaster.getCake();
        System.out.println("Your cake is ready: " + cake);

        CakeBuilder sponge = new SpongeCakeBuilder();
        cakeMaster.setCakeBuilder(sponge);
        cakeMaster.createCake(Shape.Square, 2,"Well done");     // squared, 2 layers
        cake = cakeMaster.getCake();
        System.out.println("Your cake is ready: " + cake);

        CakeBuilder yogurt = new YogurtCakeBuilder(); 
        cakeMaster.setCakeBuilder(yogurt); 
        cakeMaster.createCake(3, "The best");
        cake = cakeMaster.getCake(); 
        System.out.println("Your cake is ready: " + cake);               // 3 cake layers

        // you should add here other example(s) of CakeBuilder
        CakeBuilder lemon = new LemonCakeBuilder();
        cakeMaster.setCakeBuilder(lemon);
        cakeMaster.createCake(Shape.Rectangle, 4, "Perfection"); // 4 cake layer, rectangle
        cake = cakeMaster.getCake();
        System.out.println("Your cake is ready: " + cake);
    }
    
}
