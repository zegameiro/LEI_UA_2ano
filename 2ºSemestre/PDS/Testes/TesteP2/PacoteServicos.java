import java.util.ArrayList;
import java.util.HashSet;

public class PacoteServicos implements Servico {

    private String name;
    private String description;
    private ArrayList<Servico> pacotesServicos;
    private HashSet<TipoServico> tipoServicos;


    public PacoteServicos(String name, String description) {
        this.name = name;
        this.description = description;
        this.pacotesServicos = new ArrayList<Servico>();
        this.tipoServicos = new HashSet<TipoServico>();
    }


    @Override
    public String name() {
        return name;
    }


    @Override
    public String description() {
        return description;
    }


    @Override
    public double price() {
        double totalPrice = 0;

        for(Servico s: pacotesServicos)
            totalPrice += s.price();

        double disc = getDiscount();

        totalPrice = totalPrice - (totalPrice * disc);

        return totalPrice;
    }

    public double getDiscount() {
        double discount = 0.03;

        int count = this.tipoServicos.size();
        discount = count * discount;

        if (discount > 0.1)
            discount = 0.1;
        
        return discount;
    }

    public void add(Servico s) {
        pacotesServicos.add(s);
        tipoServicos.add(s.type());
    }

    @Override
    public String toString() {
        return "Pacote com " + pacotesServicos.size() + " AbstractServicos de " + tipoServicos.size() + " tipos. Preco (desconto = " + getDiscount() * 100 + "%): " + price();
    }


    @Override
    public TipoServico type() {
        return null;
    }

}
