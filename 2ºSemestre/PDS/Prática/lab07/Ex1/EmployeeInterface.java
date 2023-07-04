package lab07.Ex1;

import java.util.Date;

public abstract class EmployeeInterface implements Tarefas {

    protected Tarefas tarefas;

    public EmployeeInterface(Tarefas tarefas) {
        this.tarefas = tarefas;
    }

    @Override
    public void start(Date data) {
        tarefas.start(data);
    }

    @Override
    public boolean terminate(Date data) {
        if (tarefas.terminate(data)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean work(int num) {
        if (tarefas.work(num)) {
            return true;
        }
        return false;
    }

    @Override
    public String getNome() {
        return tarefas.getNome();
    }

    public int getId() {
        return tarefas.getId();
    }
    
}
