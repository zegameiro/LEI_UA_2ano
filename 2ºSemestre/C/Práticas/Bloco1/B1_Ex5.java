import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class B1_Ex5 {
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

        int total = 0;

        for(String num : numInputed) {
            int value = numbers.get(num);
            
            if(value == 10 || value == 100 || value == 1000 || value == 1000000) {
                total = total * value;
                System.out.println(total + " * " + value);
            } else {
                total += value;
                System.out.println(total + " + " + value);
            }
        }

        System.out.println(numExt + " -> " + total);



    }
    
}
