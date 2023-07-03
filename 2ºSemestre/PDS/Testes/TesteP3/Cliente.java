public class Cliente extends Utilizador implements Observer {

    public Cliente(String nome, String localidade, int idade) {
        super(nome, localidade, idade);
    }

    public String toString() {
        return super.toString();
    }

    @Override
    public void update(String notification) {
        System.out.println("O cliente " + super.getNome() + " recebeu uma notificação: " + notification);
    }
    
}