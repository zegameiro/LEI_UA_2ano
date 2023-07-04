package lab05.Ex1;

public class Container {

    private Portion portion;

    public Container(Portion portion) {
        this.portion = portion;
    }

    public Portion getPortion() {
        return portion;
    }

    public void setPortion(Portion p) {
        portion = p;
    }

    public static Container create(Portion p) {
        State state = p.getState();
        switch(state) {
            case Solid:
                if(p.getTemperature() == Temperature.COLD) 
                    return new PlasticBag(p);
                else
                    return new Tupperware(p);
            case Liquid:
                if(p.getTemperature() == Temperature.COLD) 
                    return new PlasticBottle(p);
                else
                    return new TermicBottle(p);
            default:
                System.err.println("ERROR: Invalid state");
                System.exit(1);
        }

        return null;
    }


}
