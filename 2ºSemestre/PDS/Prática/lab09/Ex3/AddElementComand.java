package lab09.Ex3;

import java.util.Collection;

public class AddElementComand<E> implements Command<E> {

    private Collection<E> c;
    private boolean isAddElementComand = false;
    private E element;

    public AddElementComand(Collection<E> c) {        
        this.c = c;
    }

    public void setIsAddElementComand(boolean isAddElementComand) {
        this.isAddElementComand = isAddElementComand;
    }

    public void setElement(E el) {
        this.element = el;
    }

    @Override
    public void execute(E el) {
        if(c.contains(el)) {
            System.out.println("Element already exists");
            setElement(el);
            setIsAddElementComand(false);
        } else {
            c.add(el);
            setIsAddElementComand(true);
            setElement(el);
            System.out.println("Element added");
        }
    }

    @Override
    public void undo() {
        if(isAddElementComand) {
            c.remove(element);
            System.out.println("UNDO OPERATION: Element removed");
        } else {
            System.out.println("UNDO OPERATION: Element not removed");
        }

    }

}
