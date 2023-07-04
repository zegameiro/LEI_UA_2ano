public class Passeio implements Servico {

    private String name;
    private String description;
    private double price;

    public Passeio(String name, String description, double price) {
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
        return TipoServico.PASSEIO;
    }

    @Override
    public String toString() {
        return  "Passeio [" +
                "name=" + name() +
                ", description=" + description() +
                ", price=" + price() + "]";
    }
}
