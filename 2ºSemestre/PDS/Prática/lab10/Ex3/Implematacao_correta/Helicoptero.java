package Implematacao_correta;

public class Helicoptero implements Veiculo {
    protected String modelo;
    protected String rota;
    
    public Helicoptero(String modelo, String rota) {
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
        return "Helicoptero | modelo "+ this.modelo +" | rota "+ this.rota;
    }
}
