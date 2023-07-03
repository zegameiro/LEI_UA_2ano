import java.util.ArrayList;

public class BasicCamperService implements ICamperService {

    private ArrayList<ICamper> campers;
    private ArrayList<Utilizador> users;

    public BasicCamperService() {
        this.campers = new ArrayList<ICamper>();
        this.users = new ArrayList<Utilizador>();
    }

    public ArrayList<ICamper> getCampers() {
        return campers;
    }

    public ArrayList<Utilizador> getUsers() {
        return users;
    }

    @Override
    public void registaUtilizador(Utilizador u) {
        this.users.add(u);
    }

    @Override
    public ICamper registaCamper(int lugares, String descricao) {
        ICamper camper = new BasicICamper(lugares, descricao);

        this.campers.add(camper);

        return camper;
    }

    @Override
    public String registaAluguer(Cliente u, ICamper c) {
        int index = this.campers.indexOf(c);
        this.campers.get(index).setEstado(Estado.INDISPONIVEL);

        String message = "Utilizador " + u.toString() + " alugou " + c.getDescricao();

        return message;
    }

    @Override
    public String terminaAluguer(Cliente u, ICamper c) {
        int index = this.campers.indexOf(c);
        this.campers.get(index).setEstado(Estado.DISPONIVEL);

        String message = "Utilizador " + u.toString() + " devolveu " + c.getDescricao();

        return message;
    }

    public ArrayList<ICamper> getCampersDisponiveis() {
        ArrayList<ICamper> availableCampers = new ArrayList<ICamper>();

        for(ICamper camp : this.campers) {
            if (camp.getEstado() == Estado.DISPONIVEL)
                availableCampers.add(camp);
            else
                continue;
        }

        return availableCampers;
    }
    
}