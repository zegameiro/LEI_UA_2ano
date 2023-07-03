package lab11.Ex1;

import java.util.ArrayList;

public class Context {

    ArrayList <Telemovel> telemovel;
    Strategy strategy;

    public Context(ArrayList<Telemovel> telemovel, Strategy strategy) {
        this.telemovel = telemovel;
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public ArrayList<Telemovel> getTelemovel() {
        return telemovel;
    }

    public void setTelemovel(ArrayList<Telemovel> telemovel) {
        this.telemovel = telemovel;
    }

    public void sort() {
        strategy.sort(telemovel);
    }
    
}
