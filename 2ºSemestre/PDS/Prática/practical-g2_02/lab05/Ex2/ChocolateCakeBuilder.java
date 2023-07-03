package lab05.Ex2;

public class ChocolateCakeBuilder implements CakeBuilder {

    private Cake chocolateCake;

    @Override
    public void setCakeShape(Shape sh) {
        chocolateCake.setShape(sh);
    }

    @Override
    public void addCakeLayer() {
        chocolateCake.addCakeLayer();
    }

    @Override
    public void addCreamLayer() {
        Cream middleCream = new Cream(null);
        chocolateCake.setMidLayerCream(middleCream);
    }

    @Override
    public void addTopLayer() {
        Cream topCream = new Cream("Whipped_Cream");
        chocolateCake.setTopLayerCream(topCream);
    }

    @Override
    public void addTopping() {
        Topping topping = new Topping("Fruit");
        chocolateCake.setTopping(topping);
    }

    @Override
    public void addMessage(String m) {
        chocolateCake.setMessage(m);
    }

    @Override
    public void createCake() {
        chocolateCake = new Cake("Soft Chocolate");
    }

    @Override
    public Cake getCake() {
        return chocolateCake;
    }

}
