package lab07.Ex1;

import java.util.Date;

public class TeamLeader extends EmployeeInterface{

    public TeamLeader(Tarefas tarefas) {
        super(tarefas);
    }

    public void start(Date data) {
        super.start(data);
    }

    public boolean terminate(Date data) {
        return super.terminate(data);
    }

    public boolean work(int num) {
        if (tarefas.work(num)) {
            System.out.print("**TeamMember**");
            return true;
        }
        return false;
    }

    public void plan() {
        System.out.print(super.getNome()+ "with id " + super.getId() + " is working as TeamLeader\n");
    }
    
}
