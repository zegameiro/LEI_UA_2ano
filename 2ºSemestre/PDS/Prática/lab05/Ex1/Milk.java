package lab05.Ex1;

public class Milk extends PortionFactory {
    
    public Milk() {
        super();
        setState(State.Liquid);
        setTemperature(Temperature.WARM);
    }

    public String toString() {
        return "Milk: Temperature " + getTemperature() +
               ", State " + getState();
    }
    
}
