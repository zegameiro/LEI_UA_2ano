package lab07.Ex2;

import java.text.Normalizer;
import java.util.Scanner;

public class NormalizationFilter extends TextDecorator{

    public NormalizationFilter(TextoInterface texto) {
        super(texto);
    }

    private Scanner sc;

    @Override 
    public boolean hasNext() {
        if(sc!=null && sc.hasNext())
            return true;
        return texto.hasNext();
    }

    @Override public String next() {
        if(sc==null || sc!=null && !sc.hasNext())
            sc = new Scanner(texto.next());
        if (this.hasNext()) {
            String[] words = sc.next().replaceAll("[,./]", "").split("\\s+");
            String output = String.join(" ", words);
            return Normalizer.normalize(output, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        }
        return null;
    }
    
}
