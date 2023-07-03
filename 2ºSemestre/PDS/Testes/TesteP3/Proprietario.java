public class Proprietario extends Utilizador implements Observer {

    public Proprietario(String nome, String localidade, int idade) {
        super(nome, localidade, idade);
    }
    
    public String toString() {
        return super.toString();
    }

    @Override
    public void update(String notification) {
        System.out.println("O proprietário " + super.getNome() + " recebeu uma notificação: " + notification);
    }
}
