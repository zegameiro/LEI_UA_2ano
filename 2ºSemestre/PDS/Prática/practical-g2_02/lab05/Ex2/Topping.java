package lab05.Ex2;

public class Topping {

    private String top;

    public Topping(String top) {
        this.top = top;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String t) {
        top = t;
    }

    @Override
    public String toString() {
        return getTop();
    }
    
}
