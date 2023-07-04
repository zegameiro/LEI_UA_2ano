package Nossa_implementacao;

import java.util.ArrayList;

public class ChatMediator implements Mediator {

    private ArrayList<Local> locais;

    public ChatMediator() {
        this.locais = new ArrayList<>();
    }

    @Override
    public void addLocal(Local local) {
        this.locais.add(local);
    }

    // envia a mensagem para Cidade
    @Override
    public void sendMessage(String message, Local local) {
        for (Local l : this.locais) {
            if (l == local) {
                l.receiveMessage(message);
                System.out.println(l);
            }
        }
    }
    
}
