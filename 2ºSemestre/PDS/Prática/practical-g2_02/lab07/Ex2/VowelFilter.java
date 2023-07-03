package lab07.Ex2;

import java.util.Scanner;

public class VowelFilter extends TextDecorator{

    public VowelFilter(TextoInterface texto) {
        super(texto);
    }

    Scanner sc;

    @Override 
    public boolean hasNext() {
        if(sc!=null && sc.hasNext())
            return true;
        return texto.hasNext();
    }

    @Override
    public String next() {
        if(sc==null || sc!=null && !sc.hasNext())
            sc = new Scanner(texto.next());
        if (this.hasNext()) {
            return sc.next().replaceAll("[aeiouAEIOU]", "");
        }
        return null;
    }
    
}
