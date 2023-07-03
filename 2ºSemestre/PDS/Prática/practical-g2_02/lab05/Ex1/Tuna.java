package lab05.Ex1;

public class Tuna extends PortionFactory {

    public Tuna() {
        super();
        setState(State.Solid);
        setTemperature(Temperature.COLD);
    }

    public String toString() {
        return "Tuna: Temperature " + getTemperature() +
               ", State " + getState();
    }
    
}
