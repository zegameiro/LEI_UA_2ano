package lab09.Ex3;

public class Controller<E> {

    private Command<E> command;
    
    public void setComand(Command<E> command) {
        this.command = command;
    }

    public void pressButton(E element) {
        command.execute(element);
    }

    public void undo() {
        if (command != null)
            command.undo();
        else 
            System.err.println("ERROR:No command to undo");
    }
}
