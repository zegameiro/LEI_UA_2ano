package Nossa_implementacao;

public class Ex3 {

    public static void main(String[] args) {

        Mediator mediator = new ChatMediator();

        Local casteloBranco = new Cidade(mediator, "Castelo Branco");
        Local leiria = new Cidade(mediator, "Leiria");

        mediator.addLocal(casteloBranco);
        mediator.addLocal(leiria);

        casteloBranco.sendMessage("Daqui fala CB, preciso de um autocarro agora!");
        leiria.sendMessage("Daqui fala Leiria, vou já enviar um autocarro para aí!");
        
    }
    
}
