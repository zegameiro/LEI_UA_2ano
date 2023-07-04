package lab09.Ex3;

public interface Command<E> {

    public void execute(E element);

    public void undo(); 
}