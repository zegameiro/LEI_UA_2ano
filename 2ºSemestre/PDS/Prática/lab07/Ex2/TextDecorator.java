package lab07.Ex2;

public abstract class TextDecorator implements TextoInterface{
    
    protected TextoInterface texto;
    
    public TextDecorator(TextoInterface texto) {
        this.texto = texto;
    }
    
    public boolean hasNext() {
        return texto.hasNext();
    }
    
    public String next() {
        return texto.next();
    }
    
    public String nextFiltered() {
        return texto.nextFiltered();
    }

}
