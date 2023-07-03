import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.LinkedHashMap;


public class WSSolver{
    public static void main(String[] args){
        // Command: "java WSSolver sdl_01.txt" (aka temos que usar args)

        String fileName = parseInput(args);
        if (fileName == null){
            System.exit(1);
        }

        // Scanner myReader = getScanner(args[0]); 
        // ArrayList<Object> parsed_input = loadInput(myReader);
        
        // char[][] sopa = (char[][]) parsed_input.get(0);
        // ArrayList<String> palavras = (ArrayList<String>) parsed_input.get(1);

        /* System.out.println("Sopa: ");
        for (int i = 0; i < sopa.length; i++){
            for (int j = 0; j < sopa.length; j++){
                System.out.print(sopa[i][j]);
            }
            System.out.println();
        }
        System.out.println("\nPalavras: ");
        for (String palavra : palavras){
            System.out.println(palavra);
        }
        System.out.println("\nSolução: "); */
        // solver(sopa, palavras);
        
    }

    // validação dos argumentos certa
    static String parseInput(String[] args) {
        if (args.length != 1){
            System.out.println("[ERROR] Invalid program usage! Please use the following format: java WSSolver <input_file.txt>");
            return null;
        }
        if (!args[0].endsWith(".txt")) {
            System.out.println("[ERROR] Invalid file format! Please use a .txt file.");
            return null;
        }

        return args[0];
    }


    static void solver(char[][] sopa, ArrayList<String> palavras) { 

        LinkedHashMap<String, String[]> found_words = new LinkedHashMap<String, String[]>();
                            // falta definir tamanho do String ou definir ArrayList

        // inicializou bem
        char[][] print_sopa = new char[sopa.length][sopa.length];

        // Inicializou a sopa para imprimir toda com '.' bem
        for (int i = 0; i < print_sopa.length; i++) {
            for (int j = 0; j < print_sopa.length; j++)
                print_sopa[i][j] = '.';
        }

        for (String palavra : palavras) {
            char first_letter = palavra.charAt(0);
            for (int col= 0; col < sopa.length; col++){
                int index = -1;
                String line = new String(sopa[col]); 

                while (true) {
                    index = line.indexOf(first_letter, ++index);

                    if (index == -1)
                        break;
                                                    // array bidimensional
                                                             // coluna
                                                                   //linha
                    int[][] neighbours = get_neighbours(sopa, col, index);
                    for (int[] neighbour : neighbours) {
                        int next_letter;
                        int x = neighbour[0]; 
                        int y = neighbour[1];
                        int x_direction = x - col; 
                        int y_direction = y - index;


                        int[][] coords= new int[palavra.length()][2]; // all the coordinates of the word chars
                        coords[0]= new int[]{col, index};

                        for (next_letter = 1; next_letter < palavra.length(); next_letter++){
                            if (out_of_bounds(x, y, sopa.length)){
                                break;
                            }
                            if (sopa[x][y] == palavra.charAt(next_letter)){
                                coords[next_letter] = new int[]{x, y};

                                x += x_direction;
                                y += y_direction;
                            } else {
                                break;
                            }
                        }
                        
                        if (next_letter == palavra.length()) {
                            String direction= get_direction(x_direction, y_direction);
                            String coords_s= coords[0][0]+1 + "," + (coords[0][1]+1);
                            String[] direction_coords= new String[]{coords_s, direction};
                            
                            found_words.put(palavra, direction_coords);
                            print_sopa = mark_word(print_sopa, palavra, coords);
                        }
                    }
                }
            }
        }
        System.out.println("");
        output(print_sopa, found_words);
        
    }

    static void output(char[][] print_sopa, LinkedHashMap<String, String[]> found_words){
        for (String word : found_words.keySet()){
            System.out.printf("%-15s %-6d %-8s %-10s\n", word.toLowerCase(), word.length(), found_words.get(word)[0], found_words.get(word)[1]);
        }
        System.out.println();
        for (int i = 0; i < print_sopa.length; i++){
            for (int j = 0; j < print_sopa.length; j++)
                System.out.print(print_sopa[i][j]);

            System.out.println();
        }
    }
    // Verificado e funcional
    static boolean out_of_bounds(int x, int y, int length) {
        return x < 0 || x >= length || y < 0 || y >= length;
    }

    static char[][] mark_word(char[][] sopa, String palavra, int[][] coords) {
        int i = 0;
        while (i < palavra.length()) {
            sopa[coords[i][0]][coords[i][1]] = palavra.charAt(i);
            i++;
        }

        return sopa;
    }

    /** 
     * Returns a list of the coordinates of all neighbours 
     * <p>
     * Each of them is represented by a list with x and y as the first and second element, respectively
    */
    // Verificado e funcional mas um bocado confuso faltam comentários para compreender o propósito da função
    static int[][] get_neighbours(char[][] sopa, int x, int y) {
        int[][] neighbours= new int[8][2];
        
        int index= 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0) // skip the current position
                    continue;
                if (out_of_bounds(x + i, y + j, sopa.length))
                    continue;

                neighbours[index] = new int[]{x + i, y + j};
                index++;
            }
        }

        neighbours = remove_unused_slots(neighbours, index);

        return neighbours;
    }
    // Verificado e confuso
    static int[][] remove_unused_slots(int[][] neighbours, int total_neighbours) {
        if (total_neighbours < 8) {
            int[][] final_neighbours = new int[total_neighbours][2];

            for (int i = 0; i < total_neighbours; i++)
                final_neighbours[i] = neighbours[i];
    
            neighbours = final_neighbours;
        }

        return neighbours;
    }

    static String get_direction(int x_direction, int y_direction) {
        String direction= "";

        if (x_direction == -1){
            direction+= "Up";
        }else if(x_direction == 1){
            direction+= "Down";
        }

        if (y_direction == -1){
            direction+= "Left";
        }else if(y_direction == 1){
            direction+= "Right";
        }

        return direction;
    }
    // Verificado e funcional
    static Scanner getScanner(String fileName) {
        File myObj = null;
        Scanner myReader = null;
        Scanner newReader = new Scanner(System.in);

        while (true) {
            try {
                myObj = new File(fileName);
                myReader = new Scanner(myObj);

                newReader.close();
                break;
            } catch (FileNotFoundException e) {
                System.out.println("[ERROR] File not found!");
                System.out.println(e);
                System.out.println("Input the correct file: ");
                fileName = newReader.nextLine();
            }
        }

        return myReader;

    }
    
    static ArrayList<Object> loadInput(Scanner input) {
        char[][] sopa = null;
        ArrayList<String> palavras = new ArrayList<String>();
        int soup_size = 3; // minimal size of the soup would be 3x3

        int i = 0;
        while (input.hasNextLine()) {
            String line = input.nextLine();

            if (line.length() == 0)  // empty lines
                System.out.println("[ERROR] The file must not contain any empty lines");;

            if (i < soup_size) { // soup part
                if (! isAllUpper(line)) {
                    System.out.println("[ERROR] Invalid line of soup! Must be all uppercase:" + line);
                    System.exit(1);
                }


                if (i == 0) { // first line (used to get the size of the soup)
                    soup_size= getSize(line);
                    if (soup_size == -1){
                        System.out.println("[ERROR] Invalid size of sopa! It's too big!");
                        System.exit(1);
                    }
                    sopa = new char[soup_size][soup_size];
                }

                if (getSize(line, soup_size) == -1) {
                    System.out.println("[ERROR] The size of this line of soup is invalid:" + line + "\n[ERROR] The expected size was: " + Integer.toString(soup_size));
                    System.exit(1);
                }

                
                for (int j = 0; j < soup_size; j++)
                    sopa[i][j] = line.charAt(j);

            } else { // palavras
                String[] word_list = line.split("[, ;]+");

                if (!checkWords(word_list)) {
                    // checkWords prints the error message
                    System.exit(1);
                }

                for (String word : word_list)
                    palavras.add(word.toUpperCase());
            }

            i++;
        }

        palavras= remove_duplicates(palavras);


        ArrayList<Object> parsed_input = new ArrayList<Object>();

        parsed_input.add(sopa);
        parsed_input.add(palavras);
        return parsed_input;
    }

    public static int getSize(String line){
        int len = line.length();
        if (len > 40) return -1;
        return len;
    }
    public static int getSize(String line, int correct_size){
        int len = line.length();
        if (len != correct_size) {return -1;}
        return len;
    }

    public static boolean isAllUpper(String word){
        return word.matches("[A-Z]+");
    }
    
    public static void errorMsg(){ // currently not used
        System.out.println("[ERROR] The text file must follow the following rules:");
        System.out.println("\t SOUP:");
        System.out.println("\t - Must be a square matrix");
        System.out.println("\t - Should have a size between 3 and 40");
        System.out.println("\t - Only uppercase letters");
        System.out.println("\t - No empty lines");
        System.out.println("\t WORDS:");
        System.out.println("\t - Must be separated by commas, spaces, semicolons or tabs");
        System.out.println("\t - Must be at least 3 characters long");
        System.out.println("\t - Must contain only alphabetical characters");
        System.out.println("\t - Cannot be all uppercase");
        System.exit(1);
    }

    public static boolean checkSopaLetters(char[][] sopa){
        for (int i = 0; i < sopa.length; i++){
            for (int j = 0; j < sopa.length; j++){
                String letra = Character.toString(sopa[i][j]);
                if (!isAllUpper(letra)){ // if not upper case and alphabetical
                    return false;
                }
            }
        }
        return true;
    }
    // Verificado funciona e está apresentável
    public static boolean checkWords(String[] word_list) {
        for (String word : word_list){
            if (word.length() < 3){
                System.out.println("[ERROR] Invalid word! Must have at least 3 chars: " + word);
                return false;
            }
            if (!word.matches("[a-zA-Z]+")) {
                System.out.println("[Error] Invalid word! Must contain only alpha characters: " + word);
                return false;
            }
            if (isAllUpper(word)){
                System.out.println("[Error] Invalid word! Cannot be all uppercase: " + word);
                return false;
            }
        }

        return true;
    }

    // Verificado e podia ser mais simples
    public static ArrayList<String> remove_duplicates(ArrayList<String> palavras) {
        ArrayList<String> palavras_sem_duplicados = new ArrayList<String>();
        boolean is_duplicated;

        for (String palavra : palavras) {
            is_duplicated = false;

            for (String palavra_2: palavras_sem_duplicados) {
                if (palavra_2.contains(palavra)) {
                    is_duplicated = true;
                    break;
                }
                if (palavra.contains(palavra_2)) {
                    palavras_sem_duplicados.remove(palavra_2);
                    break;
                }
            }
            if (!is_duplicated) 
                palavras_sem_duplicados.add(palavra);
        }
        return palavras_sem_duplicados;
    }

}
