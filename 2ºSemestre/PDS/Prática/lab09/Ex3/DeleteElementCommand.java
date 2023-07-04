package lab09.Ex3;

import java.util.Collection;

public class DeleteElementCommand<E> implements Command<E> {

    private Collection<E> c;
    private boolean isDeleteElementCommand = false;
    private E element;

    public DeleteElementCommand(Collection<E> c) {        
        this.c = c;
    }

    public void setIsDeleteElementCommand(boolean isDeleteElementCommand) {
        this.isDeleteElementCommand = isDeleteElementCommand;
    }

    public void setElement(E el) {
        this.element = el;
    }

    @Override
    public void execute(E element) {
        if(!c.contains(element)) { 
            System.out.println("Element does not exist");
            setElement(element);
            setIsDeleteElementCommand(false);

        } else {
            c.remove(element);
            setIsDeleteElementCommand(true);
            setElement(element);
            System.out.println("Element deleted");            
        }
    }

    @Override
    public void undo() {
        if(isDeleteElementCommand) {
            c.add(element);
            System.out.println("UNDO OPERATION: Element added");
        } else {
            System.out.println("UNDO OPERATION: Element not added");
        }
    }

    
    
}