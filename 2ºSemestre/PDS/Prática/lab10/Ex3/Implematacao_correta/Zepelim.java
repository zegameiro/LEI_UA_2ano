package Implematacao_correta;

public class Zepelim implements Veiculo{
    protected String modelo;
    protected String rota;
    
    public Zepelim(String modelo, String rota) {
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
        return "Zepelim | modelo "+ this.modelo +" | rota "+ this.rota;
    }
}
