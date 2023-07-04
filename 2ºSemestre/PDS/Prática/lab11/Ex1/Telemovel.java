package lab11.Ex1;

public class Telemovel {

    private String processador;
    private double preço;
    private String memoria;
    private String camara;
    private String marca;


    public Telemovel(String processador, double preço, String memoria, String camara, String marca) {
        this.processador = processador;
        this.preço = preço;
        this.memoria = memoria;
        this.camara = camara;
        this.marca = marca;
    }

    public String getProcessador() {
        return processador;
    }

    public double getPreço() {
        return preço;
    }

    public String getMemoria() {
        return memoria;
    }

    public String getCamara() {
        return camara;
    }

    public String getMarca() {
        return marca;
    }

    public void setProcessador(String processador) {
        this.processador = processador;
    }

    @Override
    public String toString() {
        return "Telemovel:" + "processador=" + processador + ", pre\u00e7o=" + preço + ", memoria=" + memoria + ", camara=" + camara + ", marca=" + marca + '\n';
    }
    
}
