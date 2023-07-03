import java.util.HashMap;
import java.util.ArrayList;

public class Camper implements CamperInterface {

    private ArrayList<Proprietario> proprietarios;
    private HashMap<Proprietario, ArrayList<ICamper>> proCampers;
    private ArrayList<Pedido> pedidos;

    protected Camper() {
        this.proprietarios = new ArrayList<Proprietario>();
        this.proCampers = new HashMap<Proprietario, ArrayList<ICamper>>();
        this.pedidos = new ArrayList<Pedido>();
    }

    public HashMap<Proprietario, ArrayList<ICamper>> getProCampers() {
        return proCampers;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public ArrayList<ICamper> getCampersDisponiveis() {

        ArrayList<ICamper> disponiveis = new ArrayList<ICamper>();

        for(ArrayList<ICamper> ca : proCampers.values()) {
            for(ICamper c : ca) {
                if(c.getEstado() == Estado.DISPONIVEL)
                    disponiveis.add(c);
            }
        }

        return disponiveis;
    }

    @Override
    public ICamper registaCamper(Proprietario p, int lugares, String descricao) {

        ICamper camp = new BasicICamper(lugares, descricao);
        this.proprietarios.add(p);

        if(proCampers.containsKey(p)) {
            this.proCampers.get(p).add(camp);
        } else {
            ArrayList<ICamper> values = new ArrayList<ICamper>();
            values.add(camp);
            this.proCampers.put(p, values);
        }

        return camp;
    }

    @Override
    public String registaPedido(Cliente u, ICamper c) {
        c.setEstado(Estado.INDISPONIVEL);

        Pedido p = new Pedido(u, c);
        u.update("Pedido registado com sucesso!");
        Proprietario pro = proprietarios.get(0);
        pro.update(" cliente " + u.getNome() + " fez um pedido no camper " + c.getDescricao());
        this.pedidos.add(p);

        return p.toString() + " Pendente";
    }

    @Override
    public String cancelaPedido(Cliente u, ICamper c) {
        Pedido p = new Pedido(u, c);

        p.getCamp().setEstado(Estado.DISPONIVEL);

        Proprietario pro = proprietarios.get(0);
        pro.update(" cliente " + u.getNome() + " cancelou o seu pedido  no camper "+ c.getDescricao());

        return p.toString() + " Cancelado";
            
    }

    @Override
    public String aceitaPedido(Cliente u, ICamper c) {
        Pedido p = new Pedido(u, c);

        p.getCamp().setEstado(Estado.RESERVADO);
        u.update( u.getNome() + " o seu pedido no camper " + c.getDescricao() + " foi aceite");

        return p.toString() + " Aceite";
    }

    @Override
    public String rejeitaPedido(Cliente u, ICamper c) {
        Pedido p = new Pedido(u, c);

        p.getCamp().setEstado(Estado.DISPONIVEL);
        this.pedidos.remove(p);
        u.update( u.getNome() + " o seu pedido no camper " + c.getDescricao() + " foi rejeitado");

        return p.toString() + " Rejeitado";
    }

    @Override
    public String registaDevolucao(Cliente u, ICamper c) {
        Pedido p = new Pedido(u, c);

        p.getCamp().setEstado(Estado.DISPONIVEL);
        this.pedidos.remove(p);

        Proprietario pro = proprietarios.get(0);
        pro.update(" O cliente " + u.getNome() + " devolveu o camper " + c.getDescricao());

        return p.toString() + " Devolvido";

    }
}
