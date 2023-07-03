import java.util.*;
import java.io.*;
import java.lang.StringBuilder;

public class WSGenerator {
    public static void main(String args[]) {
        String iFileName = "";
        int gridSize = 0;
        String oFileName = "";
        
        if (args.length < 4) {
            System.err.println("Too few arguments.");
            System.exit(1);
        } else if (args.length > 6) {
            System.err.println("Too many arguments.");
            System.exit(1);
        }

        boolean iOption = false;
        boolean sOption = false;
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-i":
                    if (!args[i + 1].contains(".txt")) {
                        System.err.println("Input file must be a .txt file.");
                        System.exit(1);
                    }
                    iOption = true;
                    iFileName = args[++i];
                    break;
                case "-s":
                    if (!args[i + 1].matches("[0-9]+")) {
                        System.err.println("Invalid grid size. Provide an integer.");
                        System.exit(1);
                    }
                    sOption = true;
                    gridSize = Integer.parseInt(args[++i]);
                    break;
                case "-o":
                    if (!args[i + 1].contains(".txt")) {
                        System.err.println("Output file must be a .txt file.");
                        System.exit(1);
                    }
                    oFileName = args[++i];
                    break;
                default:
                    System.err.println("Unrecognized option:" + args[i]);
                    System.exit(1);
            }
        }

        if (!(iOption && sOption)) {
            System.err.println("-i and -s parameters are necessary.");
            System.exit(1);
        }

        Generator generator = new Generator();
        char[][] populatedPuzzle = null;
        if (generator.readFile(iFileName)) { // if file was read sucessfully
            generator.createPuzzle(gridSize);
            populatedPuzzle = generator.populatePuzzle(gridSize);
        } else {
            System.err.println("Error: file couldn't be read.");
            System.exit(1);
        }

        if (oFileName.isEmpty()) {
            // dar print na consola
            for (int i = 0; i < gridSize; i++) {
                for (int j = 0; j < gridSize; j++) {
                    System.out.print(populatedPuzzle[i][j]);
                }
                System.out.println();
            }

            ArrayList<String> words = generator.getWords();
            int count = 0;
            for (String word : words) {
                System.out.print(word + " ");
                count++;
                if (count % 8 == 0) // 8 palavras por linha
                    System.out.println();
            }
        } else {
            // escrever no ficheiro
            PrintStream fileStream = null;
            try {
                fileStream = new PrintStream(new File(oFileName));
            } catch (FileNotFoundException e) {
                System.out.println("Error: Output file not found.");
                System.exit(1);
            }

            System.setOut(fileStream);

            for (int i = 0; i < gridSize; i++) {
                for (int j = 0; j < gridSize; j++) {
                    System.out.print(populatedPuzzle[i][j]);
                }
                System.out.println();
            }

            ArrayList<String> words = generator.getWords();
            int count = 0;
            for (String word : words) {
                System.out.print(word + " ");
                count++;
                if (count % 8 == 0) // 8 palavras por linha
                    System.out.println();
            }
        }
    }
}

class Generator {
    private ArrayList<String> words = new ArrayList<String>();
    private char[][] puzzle;

    public boolean readFile(String filename) {
        boolean sucess = true;
        try {
            Scanner reader = new Scanner(new File(filename));

            while (reader.hasNextLine() && sucess) {
                String[] wordData = reader.nextLine().split(";|,| ");

                for (int i = 0; i < wordData.length; i++) {
                    if (!wordData[i].matches("[a-zA-Z]+")) {
                        sucess = false;
                    } else {
                        words.add(wordData[i]);
                    }
                }
            }
            reader.close();

        } catch (FileNotFoundException e) {
            System.err.println("Error: Input file.");
            sucess = false;
        }

        return sucess;
    }

    public char[][] createPuzzle(int size) {
        puzzle = new char[size][size];
        ArrayList<String> insertedWords = new ArrayList<String>();
        int posx = 0, posy = 0;

        for (char[] row : puzzle)
            Arrays.fill(row, '-');

        for (String word : words) {
            char[] wordChars = word.toUpperCase().toCharArray(); // dar uppercase à palavra
            word = String.copyValueOf(wordChars);
            if (word.length() > size) {
                System.err.printf("Cannot have a word (%s) bigger than the grid size!\n", word);
                System.exit(1);
            }

            int loops = 0;
            int maxLoops = size * size * 8;

            while (!insertedWords.contains(word)) {
                if (loops == maxLoops) { // failsafe no caso de serem demasiadas palavras para uma grid pequena
                    System.err.println("Grid Size too small. Try a bigger number.");
                    System.exit(1);
                }

                int direction = randomInt(0, 8);
                if (insideAnother(word, words)) {
                    insertedWords.add(word); // essentially skip this word cuz it's inside another
                    continue;
                } else {
                    boolean fits = true; // este fits n tem nada a ver com a outra função do tropa

                    // + 0 1 2 3 4 x
                    // 0 - - - - -
                    // 1 - - - - -
                    // 2 - - - - -
                    // 3 - - - - -
                    // 4 - - - - -
                    // y

                    switch (direction) {
                        case 0: // up
                            posx = randomInt(0, size - 1);
                            posy = randomInt(word.length() - 1, size - 1);
                            for (int i = 0; i < word.length(); i++) {
                                if (puzzle[posx][posy - i] != '-' && puzzle[posx][posy - i] != word.charAt(i))
                                    fits = false; // doesn't fit in this direction
                            }

                            if (fits) {
                                for (int i = 0; i < word.length(); i++) {
                                    puzzle[posx][posy--] = word.charAt(i);
                                }
                                insertedWords.add(word);
                            }

                            break;

                        case 1: // down
                            posx = randomInt(0, size - 1);
                            posy = randomInt(0, size - word.length());
                            for (int i = 0; i < word.length(); i++)
                                if (puzzle[posx][posy + i] != '-' && puzzle[posx][posy + i] != word.charAt(i))
                                    fits = false; // doesn't fit in this direction

                            if (fits) {
                                for (int i = 0; i < word.length(); i++) {
                                    puzzle[posx][posy++] = word.charAt(i);
                                }

                                insertedWords.add(word);
                            }

                            break;

                        case 2: // right
                            posx = randomInt(0, size - word.length());
                            posy = randomInt(0, size - 1);
                            for (int i = 0; i < word.length(); i++)
                                if (puzzle[posx + i][posy] != '-' && puzzle[posx + 1][posy] != word.charAt(i))
                                    fits = false; // doesn't fit in this direction

                            if (fits) {
                                for (int i = 0; i < word.length(); i++) {
                                    puzzle[posx++][posy] = word.charAt(i);
                                }

                                insertedWords.add(word);
                            }

                            break;

                        case 3: // left
                            posx = randomInt(word.length() - 1, size - 1);
                            posy = randomInt(0, size - 1);
                            for (int i = 0; i < word.length(); i++)
                                if (puzzle[posx - i][posy] != '-' && puzzle[posx - i][posy] != word.charAt(i))
                                    fits = false; // doesn't fit in this direction

                            if (fits) {
                                for (int i = 0; i < word.length(); i++) {
                                    puzzle[posx--][posy] = word.charAt(i);
                                }

                                insertedWords.add(word);
                            }

                            break;

                        case 4: // upright
                            posx = randomInt(0, size - word.length());
                            posy = randomInt(word.length() - 1, size - 1);
                            for (int i = 0; i < word.length(); i++)
                                if (puzzle[posx + i][posy - i] != '-' && puzzle[posx + i][posy - i] != word.charAt(i))
                                    fits = false; // doesn't fit in this direction

                            if (fits) {
                                for (int i = 0; i < word.length(); i++) {
                                    puzzle[posx++][posy--] = word.charAt(i);
                                }

                                insertedWords.add(word);
                            }

                            break;

                        case 5: // upleft
                            posx = randomInt(word.length() - 1, size - 1);
                            posy = randomInt(word.length() - 1, size - 1);
                            for (int i = 0; i < word.length(); i++)
                                if (puzzle[posx - i][posy - i] != '-' && puzzle[posx - i][posy - i] != word.charAt(i))
                                    fits = false; // doesn't fit in this direction

                            if (fits) {
                                for (int i = 0; i < word.length(); i++) {
                                    puzzle[posx--][posy--] = word.charAt(i);
                                }

                                insertedWords.add(word);
                            }

                            break;

                        case 6: // downright
                            posx = randomInt(0, size - word.length());
                            posy = randomInt(0, size - word.length());
                            for (int i = 0; i < word.length(); i++)
                                if (puzzle[posx + i][posy + i] != '-' && puzzle[posx + i][posy + i] != word.charAt(i))
                                    fits = false; // doesn't fit in this direction

                            if (fits) {
                                for (int i = 0; i < word.length(); i++) {
                                    puzzle[posx++][posy++] = word.charAt(i);
                                }

                                insertedWords.add(word);
                            }

                            break;

                        case 7: // downleft
                            posx = randomInt(word.length() - 1, size - 1);
                            posy = randomInt(0, size - word.length());
                            for (int i = 0; i < word.length(); i++)
                                if (puzzle[posx - i][posy + i] != '-' && puzzle[posx - i][posy + i] != word.charAt(i))
                                    fits = false; // doesn't fit in this direction

                            if (fits) {
                                for (int i = 0; i < word.length(); i++) {
                                    puzzle[posx--][posy++] = word.charAt(i);
                                }

                                insertedWords.add(word);
                            }

                            break;
                    }
                }

                loops++;
            }
        }
        return puzzle;
    }

    public char[][] populatePuzzle(int size) {
        char[][] populatedPuzzle = this.puzzle;

        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (populatedPuzzle[i][j] == '-') {
                    int index = randomInt(0, 25);
                    populatedPuzzle[i][j] = letters.charAt(index);
                }
            }
        }

        return populatedPuzzle;
    }

    public ArrayList<String> getWords() {
        return this.words;
    }

    private boolean insideAnother(String word, ArrayList<String> wordList) {
        String reversed = new StringBuilder(word).reverse().toString();
        for (String s : wordList) {
            if (s.contains(word) || s.contains(reversed)) {
                return true;
            }
        }

        return false;
    }

    private int randomInt(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        return (int) (Math.random() * ((max - min) + 1)) + min;
    }
}