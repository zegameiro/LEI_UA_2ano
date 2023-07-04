package lab07.Ex2;

import java.util.Scanner;

public class TeamFilter extends TextDecorator {

    public TeamFilter(TextoInterface texto) {
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
            String word = sc.next();
            if(word.equals("Alimentos")) {
                return word;
            }
        }
        return null;
    }
    
}
