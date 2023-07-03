package lab05.Ex2;

public class Cake {

    private Shape shape = Shape.Circle;
    private String cakeLayer;
    private int numCakeLayers = 1;
    private Cream midLayerCream;
    private Cream topLayerCream;
    private Topping topping;
    private String message;
    private String cakeType;

    public Cake(String cakeType) {
        this.cakeType = cakeType;
    }

    // -------------------- Getters --------------------
    public String getCakeType() {
        return cakeType;
    }

    public String getCakeLayer() {
        return cakeLayer;
    }

    public Shape getCakeShape() {
        return shape;
    }

    public int getNumCakeLayers() {
        return numCakeLayers;
    }

    public Cream getMidLayerCream() {
        return midLayerCream;
    }

    public Cream getTopLayerCream() {
        return topLayerCream;
    }
    
    public Topping getTopping() {
        return topping;
    }

    public String getMessage() {
        return message;
    }
    // -------------------------------------------------

    // -------------------- Setters --------------------
    public void setCakeLayer(String cl) {
        cakeLayer = cl;
    }
    
    public void addCakeLayer() {
        this.numCakeLayers += 1;
    }

    public void setCakeShape(Shape s) {
        shape = s;
    }

    public void setNumCakeLayers(int ncl) {
        numCakeLayers = ncl;
    }

    public void setShape(Shape shc) {
        shape = shc;
    }

    public void setMessage(String msg) {
        message = msg;
    }
    
    public void setTopping(Topping t) {
        topping = t;
    }

    public void setTopLayerCream(Cream tlc) {
        topLayerCream = tlc;
    }

    public void setMidLayerCream(Cream mlc) {
        midLayerCream = mlc;
    }
    // -------------------------------------------------

    public String toString() {

        StringBuilder stb = new StringBuilder();
        stb.append(getCakeType() + " " + " cake with " + getCakeShape() + " shape, " + getNumCakeLayers() + " layers");    
        if ( midLayerCream != null )
            stb.append(" with " + getMidLayerCream());
        stb.append(", topped with " + getTopLayerCream());
        if ( topping != null )
            stb.append(" and " + getTopping());

        if( message != null)
            stb.append(". Message says: " + getMessage()); 
        return stb.toString();
    }
}
