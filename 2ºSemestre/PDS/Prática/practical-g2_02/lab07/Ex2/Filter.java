package lab07.Ex2;

public abstract class Filter implements TextoInterface {
    
    protected TextoInterface texto;
    
    public Filter(TextoInterface texto) {
        this.texto = texto;
    }
    
    public boolean hasNext() {
        return texto.hasNext();
    }
    
    public abstract String next();

}