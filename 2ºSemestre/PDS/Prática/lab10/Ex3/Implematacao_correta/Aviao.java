package Implematacao_correta;

public class Aviao implements Veiculo{
    protected String modelo;
    protected String rota;
    
    public Aviao(String modelo, String rota) {
        this.modelo = modelo;
        this.rota = rota;
    }

    public String getModelo() {
        return this.modelo;
    }

    public String getRota() {
        return this.rota;
    }

    public String toString() {
        return "Avião | modelo "+ this.modelo +" | rota "+ this.rota;
    }
}