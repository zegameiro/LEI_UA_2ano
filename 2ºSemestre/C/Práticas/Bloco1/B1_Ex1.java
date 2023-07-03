import java.util.Scanner;
import java.util.ArrayList;

public class B1_Ex1 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Insert an expressions(It should have the format <number> <operator> <number>)");

        String expression = sc.nextLine();

        String[] numbers_operator = expression.split(" ");
        ArrayList<String> numOp = new ArrayList<String>();

        for(int i = 0 ; i < numbers_operator.length ; i++)
            numOp.add(numbers_operator[i]);

        if(numOp.size() != 3) {
            System.out.println("ERROR: invalid expression inserted the format should be <number> <operator> <number>");
            numOp.clear();

            do {
                System.out.println("Insert a valid expression:");
                expression = sc.nextLine();
                numbers_operator = expression.split(" ");

                for(int i = 0 ; i < numbers_operator.length ; i++)
                    numOp.add(numbers_operator[i]);

            } while (numbers_operator.length != 3);
        }

        double n1 = Double.parseDouble(numOp.get(0));
        String operator = numOp.get(1);
        double n2 = Double.parseDouble(numOp.get(2));

        double result = 0;

        switch(operator) {
            case "+":
                result += n1 + n2;
                break;
            case "-":
                result += n1 - n2;
                break;
            case "*":
                result += n1 * n2;
                break;
            case "/":
                result += n1 / n2;
                break;
            default:
                System.out.println("Invalid operator");
                break;
        }

        System.out.println("Result = " + result);
        sc.close();

    }
}