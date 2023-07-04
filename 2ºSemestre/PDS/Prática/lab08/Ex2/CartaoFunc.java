package Ex2;

public class CartaoFunc {
    private int numero;
    private String nome;
    private String dataInicio;
    private String dataValidade;
    private static int id = 0;

    public CartaoFunc(String nome, String dataInicio, String dataValidade) {
        this.numero = id;
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataValidade = dataValidade;
        id++;
    }

    public int getNumero() {
        return numero;
    }

    public String getNome() {
        return nome;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public String getDataValidade() {
        return dataValidade;
    }
}

