package lab05.Ex1;

public class Pork extends PortionFactory {

    public Pork() {
        super();
        setState(State.Solid);
        setTemperature(Temperature.WARM);
    }

    public String toString() {
        return "Pork: Temperature " + getTemperature() +
               ", State " + getState();
    }
    
}
