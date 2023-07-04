import java.util.*;
import java.io.*;

public class WSGenerator {
    public static void main(String[] args) {
        try {
            // Verificar argumentos
            if (args.length < 4) {
                System.out.println("Usage: java WSGenerator -i <word_file> -s <grid_size> [-o <output_file>]");
                throw new Exception("ERRO: Número de argumentos inválido");
            }
            String file = "";
            String output = "";
            int size = 0;

            // Obter argumentos pela flag
            for(int i = 0; i < args.length; i++){
                if(args[i].equals("-i")){
                    file = args[i+1]; 
                }
                else if(args[i].equals("-s")){
                    size = Integer.parseInt(args[i+1]);
                }
                else if(args[i].equals("-o")){
                    output = args[i+1];
                }
            }
            if(file.equals("") || size == 0){
                System.out.println("Usage: java WSGenerator -i <word_file> -s <grid_size> [-o <output_file>]");
                throw new Exception("ERRO: Argumentos inválidos");
            }

            // Ler o ficheiro
            ArrayList<String> words = new ArrayList<String>();
            Scanner sc = new Scanner(new FileInputStream(file));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                words.add(line);
            }

            // Gerar a sopa de letras
            WSGenerator wsg = new WSGenerator();
            wsg.generate(size, words);
            
            // Verificar o metodo de output
            if(args.length == 4){
                // Imprimir a sopa de letras no terminal
                wsg.print();
            }
            else if(args.length == 6 && args[4].equals("-o")){
                // Escrever a sopa de letras num ficheiro
                System.out.println("Writing to file: " + output);
                Writer fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output), "utf-8"));
                fw.write(wsg.toWrite());
                fw.close();
            }
            else{
                System.out.println("Usage: java WSGenerator -i <word_file> -s <grid_size> [-o <output_file>]");
                throw new Exception("ERRO: Argumentos inválidos -o");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exiting...");
            System.exit(1);
        }
    }


    // CLASS

    private Grid grid;
    public ArrayList<Word> words = new ArrayList<Word>();

    // Gera a grelha com letras aleatórias
    public void generateGrid(Grid g, int grid_size){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for(int i = 0; i < grid_size; i++){
            for(int j = 0; j < grid_size; j++){
                // Verificar se a posição já tem uma letra
                if(g.getCharAt(i, j) != '.' ){
                    continue;
                }
                else{
                    // Para cada posição da grelha, obtemos um índice aleatório
                    int index = (int)(alphabet.length()*Math.random());
                    // Usamos o indice aleatório para obter uma letra aleatória e colocamos na grelha
                    g.setCharAt(i, j, alphabet.charAt(index));
                }
            }
        }
    }

    public boolean validPosition(String s, int x, int y, int grid_size, Directions direction, Grid g){
        int word_size = s.length();
        // Verificar se a palavra cabe na grelha
        if(x < 0 || y < 0 || x >= grid_size || y >= grid_size)
            return false;
        else if(g.getCharAt(x, y) != '.' && g.getCharAt(x, y) != s.charAt(0))
            return false;
        
        switch(direction){
            case UP:
                if(y - word_size < 0)
                    return false;
                break;
            case DOWN:
                if(y + word_size >= grid_size)
                    return false;
                break;
            case LEFT:
                if(x - word_size < 0)
                    return false;
                break;
            case RIGHT:
                if(x + word_size >= grid_size)
                    return false;
                break;
            case UP_LEFT:
                if(x - word_size < 0 || y - word_size < 0)
                    return false;
                break;
            case UP_RIGHT:
                if(x + word_size >= grid_size || y - word_size < 0)
                    return false;
                break;
            case DOWN_LEFT:
                if(x - word_size < 0 || y + word_size >= grid_size)
                    return false;
                break;
            case DOWN_RIGHT:
                if(x + word_size >= grid_size || y + word_size >= grid_size)
                    return false;
                break;
        }   
        return true;     
    };

    

    public void setWords(ArrayList<String> words, int grid_size){
        // Organizar as palavras por ordem crescente de tamanho
        this.grid = new Grid(grid_size);
        words.sort(Comparator.comparingInt(String::length));
        Collections.reverse(words);
        String[] directions = {"UP", "DOWN", "LEFT", "RIGHT", "UP_LEFT", "UP_RIGHT", "DOWN_LEFT", "DOWN_RIGHT"};

        for(String s : words){
            Word word = new Word(s);
            // Verificar se a palavra é demasiado grande para a grelha
            if(s.length() > grid_size){
                System.out.println("ERRO: Palavra demasiado grande");
                continue;
            }
            do{
                // Verificar se a posição é válida
                Directions direction;
                int x;
                int y;

                // Verificar se a posição é válida
                int count = 0;
                do{
                    // Obter uma direção aleatória
                    Collections.shuffle(Arrays.asList(directions));
                    direction = Directions.getDirection(directions[count]);
                    // Obter uma posição aleatória
                    x = (int)(Math.random()*grid_size);
                    y = (int)(Math.random()*grid_size);
                    if(count != directions.length-1)
                        count++;
                    else
                        count = 0;
                }
                while(!validPosition(s, x, y, grid_size, direction, this.grid));
                
                
                // Adicionar campos à palavra
                word.setDirection(direction);
                word.setX(x+1);
                word.setY(y+1);

            }while(this.grid.writeWord(word) == 1);
            // Adicionar a palavra à lista de palavras
            this.words.add(word);   
        }
        
    }

    // Formata as palavras para serem escritas no ficheiro/ ecrã
    public String formatWords(ArrayList<Word> words){
        String s = "";
        // Array com pontuação válida
        String[] pontuation = {",", " ", ";", "\n"};
        for(Word w : words){
            String word = w.getValue();
            // Para cada palavra, adicionamos uma pontuação aleatória
            s += word + pontuation[(int)(Math.random()*pontuation.length)];
        }
        return s;
    }

    public void generate(int grid_size, ArrayList<String> words){
        // Primeiro colocamos as palavras na grelha
        this.setWords(words, grid_size);
        // Depois preenchemos a grelha com letras aleatórias
        this.generateGrid(this.grid, grid_size);
        
    }

    public void print(){
        ArrayList <String> g = this.grid.getGridStringy();
        for(String s : g){
            System.out.println(s);
        }
        System.out.println(formatWords(this.words));
    }

    public String toWrite(){
        String s = "";
        ArrayList <String> g = this.grid.getGridStringy();
        for(String str : g){
            s += str + "\n";
        }
        s += formatWords(this.words);
        return s;
    }
}
