import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class B1_Ex4 {
    public static void main(String[] args) {
        Map<String,Integer> numbers = new HashMap<String,Integer>();
        ArrayList<String> numInputed = new ArrayList<String>();
        String[] elements = null;

        try {
            File f = new File("./bloco1/numbers.txt");
            Scanner reader = new Scanner(f);

            while(reader.hasNextLine()) {
                String line = reader.nextLine();
                elements = line.split("[ -]+");

                for(int i = 0; i < elements.length - 1; i++) {
                    int number = Integer.parseInt(elements[i]);
                    numbers.put(elements[i + 1], number);
                }
            }
            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File not found");
            e.printStackTrace();
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("Insert a number for extension:");
        String numExt = sc.nextLine();

        if(numExt.length() < 3) {
            System.out.println("ERROR: invalid number inserted");
            numExt = null;

            do {
                System.out.println("Insert a valid number by extension:");
                numExt = sc.nextLine();
            } while (numExt.length() < 3);
        }

        sc.close();

        String[] extension = numExt.split(" ");

        for(int i = 0; i < extension.length; i++) {
            if(numbers.containsKey(extension[i]))
                numInputed.add(extension[i]);
            else {
                System.out.println("ERROR: invalid number inserted");
                numInputed.clear();
                System.exit(1);
            }
        }
        System.out.println("A list of numbers: ");

        for(String num : numInputed) {
            System.out.print(numbers.get(num) + " ");
        }
        System.out.println();
    }
}
