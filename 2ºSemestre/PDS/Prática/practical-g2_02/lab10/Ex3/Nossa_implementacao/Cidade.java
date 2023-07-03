package Nossa_implementacao;

public class Cidade extends Local {

    public Cidade(Mediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void sendMessage(String message) {
        this.mediator.sendMessage("Mensagem enviada:" + message, this);        
    }

    @Override
    public void receiveMessage(String message) {
        System.out.println(message);
    }
    
}