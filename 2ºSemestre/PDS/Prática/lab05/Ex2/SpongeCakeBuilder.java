package lab05.Ex2;

public class SpongeCakeBuilder implements CakeBuilder {

    private Cake spongeCake;

    @Override
    public void setCakeShape(Shape sh) {
        spongeCake.setShape(sh);
    }

    @Override
    public void addCakeLayer() {
        spongeCake.addCakeLayer();
    }

    @Override
    public void addCreamLayer() {
        Cream middleCream = new Cream("Red_Barries");
        spongeCake.setMidLayerCream(middleCream);
    }

    @Override
    public void addTopLayer() {
        Cream topCream = new Cream("Whipped_Cream");
        spongeCake.setTopLayerCream(topCream);
    }

    @Override
    public void addTopping() {
        Topping topping = new Topping("Fruit");
        spongeCake.setTopping(topping);
    }

    @Override
    public void addMessage(String m) {
        spongeCake.setMessage(m);
    }

    @Override
    public void createCake() {
        spongeCake = new Cake("Sponge");
    }

    @Override
    public Cake getCake() {
        return spongeCake;
    }
}
