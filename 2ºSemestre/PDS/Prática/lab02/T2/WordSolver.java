import java.util.*;

public class WordSolver {
    private Grid grid;
    private ArrayList<Word> words = new ArrayList<Word>();
    private int grid_size;

    public WordSolver(ArrayList<String> file) {
        this.words = getWordList(file);
        this.grid = findGrid(file);
    }

    // GERAR AS ESTRUTURAS DE DADOS PARA RESOLVER O PROBLEMA
    // Obter a lista de palvras a pesquisar
    private ArrayList<Word> getWordList(ArrayList<String> list) {
        ArrayList<String> word_lines = new ArrayList<String>();
        ArrayList<Word> words = new ArrayList<Word>();

        // Separar as palavras por ',' ';' ' '
        for (int i = 0; i < list.size(); i++) {
            word_lines.addAll(Arrays.asList(list.get(i).split("[,; ]")));
        }

        // Obter as palavras a pesquisar do ficheiro
        for (int i = 0; i < word_lines.size(); i++) {
            if (isWord(word_lines.get(i))) {
                Word w = new Word(word_lines.get(i));
                words.add(w);
            }
        }

        // Remover palavras com menos de 3 caracteres
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i).getValue();
            if (word.length() < 3) {
                words.remove(i);
            }
        }

        return words;
    }

    // Obter a grelha
    private Grid findGrid(ArrayList<String> ws) {
        // Obter o número de linhas que contem palavras
        int word_lines = 0;
        for (int i = ws.size() - 1; i > 0; i--) {
            if (isWord(ws.get(i))) {
                word_lines++;
            }
        }
        // Número de linhas da grelha
        this.grid_size = ws.size() - word_lines;

        // Obter a grelha
        ArrayList<String> grid = new ArrayList<String>();
        for (int i = 0; i < grid_size; i++) {
            grid.add(ws.get(i));
        }

        return new Grid(grid);
    }

    // Verificar se uma string segue a formatação válida para uma palavra de sopa de
    // letras
    private Boolean isWord(String str) {
        for (char c : str.toCharArray()) {
            if (Character.isLowerCase(c)) {
                return true;
            }
        }
        return false;
    }

    // Encontrar as palavras na grelha
    public void findWord(Word w) {
        try{
            String word = w.getValue().toUpperCase();
            for (int i = 0; i < grid_size; i++) {
                for (int j = 0; j < grid_size; j++) {
                    // Se a palavra já tiver sido encontrada parar
                    if(w.getDirection()!=null){
                        return;
                    }
                    // Procurar pela primeira letra da palavra
                    Character c = grid.getCharAt(i, j);
                    if (c == word.charAt(0)) {
                        // Procurar em todas as direções e transformar em string para posterior pesquisa
                        String right = stringify(grid.getRIGHT(i));
                        String down = stringify(grid.getDOWN(j));
                        String down_right = stringify(grid.getDOWN_RIGHT(i, j));
                        String up_right = stringify(grid.getUP_RIGHT(i, j));
                        String left = stringify(grid.getLEFT(i));
                        String up = stringify(grid.getUP(j));
                        String down_left = stringify(grid.getDOWN_LEFT(i, j));
                        String up_left = stringify(grid.getUP_LEFT(i, j));
                        
                        List<String> directions = Arrays.asList(right, down, down_right, up_right, left, up, down_left, up_left);
                        
                        // Verificar se a palavra existe em alguma direção.
                        for (String direction : directions){
                            if (direction.contains(word)){
                                // Guardar a posição
                                w.setX(j+1);
                                w.setY(i+1);

                                // Guardar a direção
                                if(direction.equals(right)){
                                    w.setDirection(Directions.RIGHT);
                                }
                                else if(direction.equals(down)){
                                    w.setDirection(Directions.DOWN);
                                }
                                else if(direction.equals(down_right)){
                                    w.setDirection(Directions.DOWN_RIGHT);
                                }
                                else if(direction.equals(up_right)){
                                    w.setDirection(Directions.UP_RIGHT);
                                }
                                else if(direction.equals(left)){
                                    w.setDirection(Directions.LEFT);
                                }
                                else if(direction.equals(up)){
                                    w.setDirection(Directions.UP);
                                }
                                else if(direction.equals(down_left)){
                                    w.setDirection(Directions.DOWN_LEFT);
                                }
                                else if(direction.equals(up_left)){
                                    w.setDirection(Directions.UP_LEFT);
                                }
                                else{
                                    throw new Exception("ERRO: Palavra não está na grelha!");
                                }

                            }
                        }
                    }
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    // Resolver o problema
    public void solve() {
        for (int i = 0 ; i < this.words.size(); i++) {
            // Garantir que se duas palavras têm o mesmo começo só é procurada a palavra maior
            Word word;
            Word word_0 = words.get(i);

            // Se i+1 > words.size significa que estamos no ultimo elemento do array, logo avançamos
            if(i+1 < words.size()){
                Word word_1 = words.get(i+1);
                // Se as palavras têm partes em comum retiramos do array a palavra menor e procuramos a maior
                if(word_0.getValue().contains(word_1.getValue()) || word_1.getValue().contains(word_0.getValue())){
                    if(word_0.getSize()>word_1.getSize()){
                        words.remove(word_1);
                        word = word_0;
                    }
                    else{
                        words.remove(word_0);
                        word = word_1;
                    }
                }
                else{
                    word = word_0;
                }
            }
            else{
                word = word_0;
            }

            findWord(word);
        }
    }

    // Getters
    public Grid getGrid() {
        return this.grid;
    }

    public ArrayList<Word> getWords() {
        return this.words;
    }

    public int getGridSize() {
        return this.grid_size;
    }

    // Utility function
    private String stringify(List<Character> list){
        StringBuilder sb = new StringBuilder();
        for (Character ch: list) {
            sb.append(ch);
        }
 
        String string = sb.toString();
        return string;
    }
    
    public String getSolution(){
        String s = "";
        Grid solved = new Grid(this.grid_size);
        for (Word word : words) {
            solved.writeWord(word);
        }
        s += solved.toString() + "\n\n";
        
        s += String.format("%-20s %-5s (%-2s,%-2s) %s","WORD", "SIZE" ,"X","Y", "DIRECTION") + "\n";
        s+=  "-----------------------------------------------------\n\n";
        for (Word word : words) {
            s += word.toString() + "\n";
        }
        return s;
    }

    // Overrides
    @Override
    public String toString() {
        String s = "GRID:\n\n";
        s += grid.toString();

        s += "\n" + String.format("%-20s %-5s","WORD", "SIZE") + "\n";
        s += "--------------------------\n";

        for (int i = 0; i < words.size(); i++) {
            s += String.format("%-20s %-5s",words.get(i).getValue(), words.get(i).getSize()) + "\n";
        }

        return s;
    }

}
