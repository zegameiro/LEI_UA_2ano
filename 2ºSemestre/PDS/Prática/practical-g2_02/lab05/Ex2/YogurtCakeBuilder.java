package lab05.Ex2;

public class YogurtCakeBuilder implements CakeBuilder {

    private Cake yogurtCake;

    @Override
    public void setCakeShape(Shape sh) {
        yogurtCake.setShape(sh);
    }

    @Override
    public void addCakeLayer() {
        yogurtCake.addCakeLayer();
    }

    @Override
    public void addCreamLayer() {
        Cream middleCream = new Cream("Vanilla");
        yogurtCake.setMidLayerCream(middleCream);
    }

    @Override
    public void addTopLayer() {
        Cream topCream = new Cream("Red_Barries");
        yogurtCake.setTopLayerCream(topCream);
    }

    @Override
    public void addTopping() {
        Topping topping = new Topping("Chocolate");
        yogurtCake.setTopping(topping);
    }

    @Override
    public void addMessage(String m) {
        yogurtCake.setMessage(m);
    }

    @Override
    public void createCake() {
        yogurtCake = new Cake("Yogurt");
    }

    @Override
    public Cake getCake() {
        return yogurtCake;
    }

}
