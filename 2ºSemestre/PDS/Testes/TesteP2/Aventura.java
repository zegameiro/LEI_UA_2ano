public class Aventura implements Servico {

    private String name;
    private String description;
    private double price;

    public Aventura(String name, String description, double price) {
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
        return TipoServico.AVENTURA;
    }

    @Override
    public String toString() {
        return  "Aventura [" +
                "name=" + name() +
                ", description=" + description() +
                ", price=" + price() + "]";
    }
}