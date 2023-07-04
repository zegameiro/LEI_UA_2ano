package lab09.Ex3;

import java.util.ArrayList;
import java.util.Collection;

public class Ex3 {

    public static void main(String[] args) {
        
        Controller<Integer> controller = new Controller<Integer>();
        Collection<Integer> collection = new ArrayList<Integer>();

        Command<Integer> add = new AddElementComand<Integer>(collection);
        Command<Integer> delete = new DeleteElementCommand<Integer>(collection);

        controller.setComand(add);
        for (int i = 0; i <= 3; i++) {
            controller.pressButton(i);
            System.out.println(collection);
        }

        controller.setComand(delete);
        controller.pressButton(1);
        System.out.println(collection);
        controller.pressButton(2);
        System.out.println(collection);

        controller.undo();
        System.out.println(collection);

    
    }
    
}
