package lab07.Ex2;

import java.util.*;

public class CapitalizationFilter extends TextDecorator {

    public CapitalizationFilter(TextoInterface texto) {
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
        String text = sc.next();
        String output = "";
        if (hasNext()) {
            if (text.length() > 2) {
                output += text.substring(0, 1).toUpperCase() +
                        text.substring(1, text.length() - 1).toLowerCase() +
                        text.substring(text.length() - 1).toUpperCase() + " ";
            } else {
                output += text.toUpperCase() + " ";
            }
            return output;
        }
        return null;
    }

}
