package lab07.Ex1;

import java.util.Date;

public class Ex1 {
    public static void main(String[] args) {

        Employee employee1 = new Employee("JOSÉ GAMEIRO", 234, 800);
        TeamMember employee2 = new TeamMember(new Employee("DIANA MIRANDA", 22, 880));
        Manager employee3 = new Manager(new Employee("JOÃO NUNO", 2, 11000));
        TeamLeader employee4 = new TeamLeader(new Employee("DIOGO FALCÃO", 1, 56000));

        employee1.work(1);
        employee1.start(new Date()); 
        employee1.start(new Date()); 
        employee1.work(1);
        employee1.terminate(new Date()); 
        employee2.colaborate();
        employee4.plan();

        EmployeeInterface employees[] = {employee2, employee3 ,employee4};
        for (EmployeeInterface e: employees) {
            System.out.println("\nEmployee" + e);
            e.start(new Date());
            e.work(1);

            // For last role added, do additional operation
            if (e instanceof TeamMember) {
                TeamMember ee = (TeamMember) e;
                ee.colaborate();
            }
            if (e instanceof TeamLeader) {
                TeamLeader ee = (TeamLeader) e;
                ee.plan();
            }
            if (e instanceof Manager) {
                Manager ee = (Manager) e;
                ee.manage();
            }
            e.terminate(new Date());
        }

    }
}