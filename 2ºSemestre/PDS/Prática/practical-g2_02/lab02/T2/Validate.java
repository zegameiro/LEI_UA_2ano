import java.util.*;

public class Validate {
// Valida o ficheiro e cria as estruturas de dados necessárias para o programa
    private ArrayList<Word> words;
    private Grid grid;
    private ArrayList<String> file;

    // Constructors
    public Validate(ArrayList<String> file, Grid grid, ArrayList<Word> words) {
        this.file = file;
        this.words = words;
        this.grid = grid;
    }

    public Validate(ArrayList<String> file) {
        this.file = file;
    }

    public Validate(Grid grid, ArrayList<Word> words) {
        this.words = words;
        this.grid = grid;
    }
    
    public Validate() {
    }

    // Getters
    public Grid getGrid() {
        return this.grid;
    }

    public ArrayList<Word> getWords() {
        return this.words;
    }


    public void validateFile() {
        try{
            ArrayList<String> file = this.file;
            ArrayList<String> ws = new ArrayList<String>();
            
            // Obter todas as palavras do ficheiro
            for(int i = 0; i < file.size(); i++){
                ws.addAll(Arrays.asList(file.get(i).split("[,; ]")));
            }
            
            // Verificar se o ficheiro contem apenas caracteres válidos
            for (int i = 0; i < ws.size(); i++) {
                for (int j = 0; j < ws.get(i).length(); j++) {
                    if (!Character.isLetter(ws.get(i).charAt(j))) {
                        throw new Exception("ERRO: Ficheiro com formato inválido");
                    }
                }
            }

            // Verificar se o ficheiro tem linhas vazias
            for (int i = 0; i < file.size(); i++) {
                if (file.get(i).isEmpty()) {
                    throw new Exception("ERRO: Grelha contem linhas vazias");
                    
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Exiting...");
            System.exit(1);
        }
    }

    public void validateGrid(){
        try {
            Grid grid = this.grid;

            int grid_size = grid.getGridSize();

            // Verificar se a grelha é quadrada
            // Verificamos se o tamanho da primeira linha é igual ao tamanho da grelha
            for(int i = 0; i < grid_size; i++){
                if (grid.getRIGHT(i).size() != grid_size) {
                    throw new Exception("ERRO: Grelha não é quadrada");
                }
            }

            // Verificar se a grelha não excede o tamanho máximo
            if (grid_size > 40){
                throw new Exception("ERRO: Grelha excede o tamanho máximo");
            }

            // Verificar se a grelha contem apenas caracteres maiúsculos
            for(int i = 0; i < grid_size; i++){
                ArrayList<Character> row = grid.getRIGHT(i);
                for (Character c : row){
                    if(!Character.isUpperCase(c)){
                        throw new Exception("ERRO: Grelha contem caracteres minúsculos");
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exiting...");
            System.exit(1);
        }
    }
    
    public void validateWords(){
        try {
            ArrayList<Word> words = this.words;

            for(Word w : words){
                String word = w.getValue();
                if(word.length() < 3){
                    throw new Exception("ERRO: Palavras com tamanho inferior a 3");
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exiting...");
            System.exit(1);
        }



    }

    public boolean validatenow(int nLines, int nColumns, ArrayList<String> wordList) {
        return false;
    }

}
