package lab07.Ex2;

import java.io.*;
import java.util.*;

public class TextReader implements TextoInterface {

    private Scanner sc;

    public TextReader(String filename) {
        try {
            sc = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            System.err.println("ERROR! File Not Found!");
            System.exit(1);
        }
    }

    @Override
    public boolean hasNext() {
        return sc.hasNextLine();
    }

    @Override
    public String next() {
        if (hasNext()) {
            return sc.nextLine();
        }
        return null;
    }

    @Override
    public String nextFiltered() {
        return next();
    }
    
}
