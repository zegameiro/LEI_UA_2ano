import java.io.*;
import java.util.*;

public class WSSolver {
    // processamento de ficheiro
    public static void main(String[] args) {
        // Ler o ficheiro
        ArrayList<String> file = new ArrayList<String>();

        try {
            Scanner sc = new Scanner(new FileInputStream(args[0]));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                file.add(line);
            }
        } catch (Exception e) {
            System.out.println("Erro ao ler ficheiro");
            System.exit(1);
        }
        
        // Validar o ficheiro
        Validate v_file = new Validate(file);
        v_file.validateFile();
        
        WordSolver ws = new WordSolver(file);

        // Validar a grelha e as palavras
        Validate v_ws = new Validate(ws.getGrid(), ws.getWords());
        v_ws.validateGrid();
        v_ws.validateWords();

        // System.out.println(ws.toString());

        ws.solve();
        System.out.println(ws.getSolution());
        //System.out.println(ws.getSolution());
       
    }
    
}

