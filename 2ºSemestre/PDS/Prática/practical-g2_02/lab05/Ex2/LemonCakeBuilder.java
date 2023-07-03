package lab05.Ex2;

public class LemonCakeBuilder implements CakeBuilder{
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
        Cream middleCream = new Cream("Orange_Juice");
        spongeCake.setMidLayerCream(middleCream);
    }

    @Override
    public void addTopLayer() {
        Cream topCream = new Cream("Lemon_Cream");
        spongeCake.setTopLayerCream(topCream);
    }

    @Override
    public void addTopping() {
        Topping topping = new Topping("Lime");
        spongeCake.setTopping(topping);
    }

    @Override
    public void addMessage(String m) {
        spongeCake.setMessage(m);
    }

    @Override
    public void createCake() {
        spongeCake = new Cake("Lemon");
    }

    @Override
    public Cake getCake() {
        return spongeCake;
    }
}
