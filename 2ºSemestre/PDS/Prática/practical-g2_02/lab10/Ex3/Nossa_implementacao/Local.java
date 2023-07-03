package Nossa_implementacao;

public abstract class Local {

    protected Mediator mediator;
    protected String name;

    public Local(Mediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public abstract void sendMessage(String message);

    public abstract void receiveMessage(String message);

    public Mediator getMediator() {
        return mediator;
    }

    @Override
    public String toString() {
        return "Mensagem recebida de: " + name + " !";
    }

}
