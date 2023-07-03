package lab05.Ex1;

public class FruitJuice extends PortionFactory {

    private String fruit;

    public FruitJuice(String fruit) {
        super();
        this.fruit = fruit;
        setState(State.Liquid);
        setTemperature(Temperature.COLD);
    }

    public String getFruit() {
        return fruit;
    }

    public void setFruit(String fr) {
        fruit = fr;
    }

    public String toString() {
        return "FruitJuice: " + getFruit() + 
               ", Temperature " + getTemperature() +
                ", State " + getState();
    }
    
}
