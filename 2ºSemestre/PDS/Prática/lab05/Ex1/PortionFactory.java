package lab05.Ex1;

public class PortionFactory implements Portion{

    private String food;
    private Temperature temperature;
    private State state;

    @Override
    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature t) {
        temperature = t;
    }

    @Override
    public State getState() {
        return state;
    }

    public void setState(State s) {
        state = s;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String f) {
        food = f;
    }

    public static Portion create(String food, Temperature temp) {
        switch(food) {
            case "Beverage":
                if(temp == Temperature.COLD) 
                    return new FruitJuice("Orange");
                else if(temp == Temperature.WARM)
                    return new Milk();
                else
                    System.err.println("ERROR: Invalid temperature");
                    System.exit(1);

            case "Meat":
                if(temp == Temperature.COLD)
                    return new Tuna();
                else if(temp == Temperature.WARM)
                    return new Pork();
                else
                    System.err.println("ERROR: Invalid temperature");
                    System.exit(1);

            default:
                System.err.println("ERROR: Invalid food type");
                System.exit(1);
        }

        return null;
    }



    
}
