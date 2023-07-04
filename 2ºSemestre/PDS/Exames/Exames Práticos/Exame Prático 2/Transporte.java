public class Transporte implements Servico {
    
    private String name;
    private String description;
    private double price;

    public Transporte(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
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
        return price;
    }

    @Override
    public TipoServico type() {
        return TipoServico.TRANSPORTE;
    }

    @Override
    public String toString() {
        return  "Transporte [" +
                "name=" + name() +
                ", description=" + description() +
                ", price=" + price() + "]";
    }
}
