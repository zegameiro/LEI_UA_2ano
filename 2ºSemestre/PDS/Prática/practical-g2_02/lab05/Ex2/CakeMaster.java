package lab05.Ex2;

public class CakeMaster {

    private Cake cake;
    private CakeBuilder cakeBuilder;

    public void setCakeBuilder(CakeBuilder cb) {
        this.cakeBuilder = cb;
    }

    public Cake getCake() {
        return cake;
    }

    public void createCake(Shape shape, int layers, String message) {

        cakeBuilder.createCake();
        
        if(layers > 1)  {
            for(int j = 1; j < layers; j++)
                cakeBuilder.addCakeLayer();
            cakeBuilder.addCreamLayer();
        }

        cakeBuilder.addMessage(message);

        if(shape != Shape.Circle)
            cakeBuilder.setCakeShape(shape);
        else
            cakeBuilder.setCakeShape(shape);
        
        cakeBuilder.addTopLayer();
        cakeBuilder.addTopping();

        cake = cakeBuilder.getCake();     
    }

    public void createCake(int layers, String message) {

        cakeBuilder.createCake();

        cakeBuilder.addMessage(message);

        if(layers > 1)  {
            for(int j = 1; j < layers; j++)
                cakeBuilder.addCakeLayer();
            cakeBuilder.addCreamLayer();
        }

        cakeBuilder.addMessage(message);
        
        cakeBuilder.addTopLayer();
        cakeBuilder.addTopping();

        cake = cakeBuilder.getCake();
    }

    public void createCake(String message) {
        cakeBuilder.createCake();
        cakeBuilder.addMessage(message);
        cakeBuilder.addTopLayer();
        cakeBuilder.addTopping();

        cake = cakeBuilder.getCake();
    }

}
