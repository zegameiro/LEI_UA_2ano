import java.util.*;

public class Grid {
    private ArrayList<ArrayList <Character>> grid = new ArrayList<ArrayList <Character>> (); 
    private int grid_size;

    // Constructors
    public Grid (ArrayList<String> grid){
        
        // Obter o tamanho da grelha
        this.grid_size = grid.size();
        
        // Preencher a grelha a partir da arraylist
        for(int i = 0; i < grid.size(); i++){ 
            this.grid.add(new ArrayList<Character>());
            for(int j = 0; j < grid.get(i).length(); j++){
                this.grid.get(i).add(grid.get(i).charAt(j));
            }
        }
    }

    public Grid (int grid_size){
        this.grid_size = grid_size;
        for(int i = 0; i < grid_size; i++){
            this.grid.add(new ArrayList<Character>());
            for(int j = 0; j < grid_size; j++){
                this.grid.get(i).add('.');
            }
        }
    }

    // Getters
    public ArrayList<ArrayList <Character>> getGrid(){
        return this.grid;
    }
    
    public int getGridSize(){
        return this.grid_size;
    }

    // Methods
    // Coloca um char na posição (i,j)
    public void setCharAt(int i, int j, char c){
        this.grid.get(i).set(j, c);
    }

    // Retorna o char na posição (i,j)
    public Character getCharAt(int i, int j){
        return this.grid.get(i).get(j);
    }

    // Retorna a linha i na forma [char1, char2, char3 , ..., char_j]
    public ArrayList<Character> getRIGHT(int i){
        return this.grid.get(i);
    }

    // Retorna a coluna j na forma [cha1, char2, ..., char_i]
    public ArrayList <Character> getDOWN(int j){
        ArrayList <Character> col = new ArrayList<>();
        for(ArrayList<Character> grid_i : this.grid){
            col.add(grid_i.get(j));
        }
        return col;
    }

    // Retorna a diagonal (\) a começar na posição (i,j)
    public ArrayList <Character> getDOWN_RIGHT(int i, int j){
        ArrayList<Character> diagonal = new ArrayList<>();
        while(i>= 0 && i < this.grid_size && j >= 0 && j < this.grid_size){
            diagonal.add(this.grid.get(i).get(j));
            i++;
            j++;
        }
        return diagonal;
    }

    // Retorna a diagonal (/) a começar na posição (i,j)
    public ArrayList <Character> getUP_RIGHT(int i, int j){
        ArrayList<Character> diagonal = new ArrayList<>();
        while(i>= 0 && i < this.grid_size && j >= 0 && j < this.grid_size){
            diagonal.add(this.grid.get(i).get(j));
            i--;
            j++;
        }
        return diagonal;
    }

    // Retorna a linha i na forma [char_j, char_j_-_1, ..., char0]
    public ArrayList<Character> getUP(int i){
        ArrayList<Character> row = new ArrayList<Character>(getDOWN(i));
        Collections.reverse(row);
        return row;
    }

    // Retorna a coluna j na forma [char_i, char_i_-_1, ..., char0]
    public ArrayList <Character> getLEFT(int j){
        ArrayList <Character> col = new ArrayList<Character>(getRIGHT(j));
        Collections.reverse(col);
        return col;
    }

    // Retorna a diagonal (\) a começar na posição (i,j)
    public ArrayList <Character> getDOWN_LEFT(int i, int j){
        ArrayList<Character> diagonal = new ArrayList<>();
        while(i>= 0 && i < this.grid_size && j >= 0 && j < this.grid_size){
            diagonal.add(this.grid.get(i).get(j));
            i++;
            j--;
        }
        return diagonal;
    }

    // Retorna a diagonal (/) a começar na posição (i,j)
    public ArrayList <Character> getUP_LEFT(int i, int j){
        ArrayList<Character> diagonal = new ArrayList<>();
        while(i>= 0 && i < this.grid_size && j >= 0 && j < this.grid_size){
            diagonal.add(this.grid.get(i).get(j));
            i--;
            j--;
        }
        return diagonal;
    }

    // Coloca uma palavra na grelha
    public int writeWord(Word w){
        int x = w.getX()-1;
        int y = w.getY()-1;
        String word = w.getValue().toUpperCase();

        // Colocar a primeira letra na grelha
        if(grid.get(y).get(x) == '.' || grid.get(y).get(x) == word.charAt(0)){
            grid.get(y).set(x, word.charAt(0));
        }
        else{
            return 1;
        }

        // Percorrer a palavra e colocar as letras na grelha
        for(int i = 1; i< w.getSize(); i++){
            // Verificar se a posição é válida
            if(x<0 || x>=grid_size || y<0 || y>=grid_size){
                return 1;
            }
            switch(w.getDirection()){
                case UP: {
                    y = y-1;
                    break;
                }
                case DOWN: {
                    y = y+1;
                    break;
                }
                case RIGHT: {
                    x = x+1;
                    break;
                }
                case LEFT: {
                    x = x-1;
                    break;
                }
                case UP_RIGHT: {
                    x = x+1;
                    y = y-1;
                    break;
                }
                case DOWN_RIGHT: {
                    x = x+1;
                    y = y+1;
                    break;
                }
                case UP_LEFT: {
                    x = x-1;
                    y = y-1;
                    break;
                }
                case DOWN_LEFT: {
                    x = x-1;
                    y = y+1;
                    break;
                }
                default: {
                    break;
                }
            }
            try{
                if(x < 0 || x >= grid_size || y < 0 || y >= grid_size)
                    throw new Exception("Posição inválida");
                else if(grid.get(y).get(x) != '.' && grid.get(y).get(x) != word.charAt(i))
                    throw new Exception("Posição ocupada");
                else{
                    grid.get(y).set(x, word.charAt(i));
                }
            }catch (Exception e) {
                return 1;
            }
        }
        return 0;
    }

    // Retorna a grelha na forma de uma arraylist de strings
    public ArrayList<String> getGridStringy(){
        ArrayList<String> grid_stringy = new ArrayList<>();
        // Acedemos a cada linha da grelha
        for(ArrayList<Character> c : this.grid){
            String str = "";
            // E convertemos cada linha para uma string
            for(Character ch : c){
                str += ch;
            }
            // Adicionamos a string à arraylist a retornar
            grid_stringy.add(str);
        }
        
        return grid_stringy;
    }

    @Override
    public String toString() {
        String str = "";
        for(int i = 0; i < grid.size(); i++){
            for(int j = 0; j < grid.get(i).size(); j++){
                str += grid.get(i).get(j) + " ";
            }
            str += "\n";
        }

        return str;
    }
    
}

