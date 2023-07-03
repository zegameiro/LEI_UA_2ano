package lab07.Ex1;

import java.util.Date;

public interface Tarefas {
    
    void start(Date data);
    boolean terminate(Date data);
    boolean work(int num);
    String getNome();
    int getId();

}
