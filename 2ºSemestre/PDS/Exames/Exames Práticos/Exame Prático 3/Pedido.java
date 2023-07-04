public class Pedido {
    
    private Cliente client;
    private ICamper camp;

    public Pedido(Cliente client, ICamper camp) {
        this.client = client;
        this.camp = camp;
    }

    public Cliente getClient() {
        return client;
    }

    public ICamper getCamp() {
        return camp;
    }

    @Override
    public String toString() {
        return "Pedido de " + client.getNome() + " para " + camp.getDescricao() + ".:";
    }

}
