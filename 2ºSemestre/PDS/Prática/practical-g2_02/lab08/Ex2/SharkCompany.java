package Ex2;

import java.util.*;

public class SharkCompany {
    public static void main(String[] args) {
        System.out.println("\nCriar uma empresa...");
        Company shark = new Company();
        Company.user = User.COMPANY;
        System.out.println("\nAdicionar empregados....");
        shark.admitEmployee(new Person("Maria Silva"), 1000);
        shark.admitEmployee(new Person("Manuel Pereira"), 900);
        shark.admitEmployee(new Person("Aurora Machado"), 1200);
        shark.admitEmployee(new Person("Augusto Lima"), 1100);
        List<Employee> sharkEmps = shark.employees();

        System.out.println("--------------Company--------------");
        for (Employee e : sharkEmps){
            // "talking to strangers", but this is not a normal case
            System.out.println(e.getBankAccount().balance());
        }
        shark.paySalaries(1);
        for (Employee e : sharkEmps) {
            e.getBankAccount().withdraw(500);
            System.out.println(e.getBankAccount().balance());
        }

        System.out.println("-------------User------------------");
        Company.user = User.OWNER;
        for (Employee e : sharkEmps){
            // "talking to strangers", but this is not a normal case
            System.out.println(e.getBankAccount().balance());
        }
        shark.paySalaries(1);
        for (Employee e : sharkEmps) {
            e.getBankAccount().withdraw(500);
            System.out.println(e.getBankAccount().balance());
        }
    }
}
