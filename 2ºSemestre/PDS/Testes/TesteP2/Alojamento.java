public class Alojamento implements Servico {

    private String name;
    private String descritpion;
    private double price;
    private int max_ocupation;

    public Alojamento(String name, String description, double price, int max_ocupation) {
        this.name = name;
        this.descritpion = description;
        this.price = price;
        this.max_ocupation = max_ocupation;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String description() {
        return descritpion;
    }

    @Override
    public double price() {
        return price;
    }

    public int getMaxOcupation() {
        return max_ocupation;
    }

    @Override
    public TipoServico type() {
        return TipoServico.ALOJAMENTO;
    }

    @Override
    public String toString() {
        return  "Alojamento [" +
                "name=" + name() + 
                ", description=" + description() + 
                ", price=" + price() + 
                ", ocupacao maxima=" + getMaxOcupation() + "]";
    }
    
}
