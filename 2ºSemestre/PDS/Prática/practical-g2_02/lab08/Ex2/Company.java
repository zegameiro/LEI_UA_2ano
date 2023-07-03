package Ex2;

import java.util.*;

class Company { 

    SocialSecurity sc = new SocialSecurity();
    Insurance in = new Insurance();
    CartaoFunc cf = new CartaoFunc("Dize", "12/12/2012", "23/05/2012");
    Parking pk = new Parking();

    public static User user; 
    private List<Employee> emps = new ArrayList<>(); 
    
    public void admitEmployee(Person person, int salary) {
        Employee e = new Employee(person, salary); 
        emps.add(e); 
    }

    public void facade(Person p) {
        SocialSecurity.registPerson(p);
        Insurance.registPerson(p);
        Parking.allow(p);
    }

    public List<Employee> employees() {
        return emps;
    }

    public void paySalaries(int i) {
        for (Employee e : emps) {
            e.getBankAccount().deposit(e.getSalary());
        }
    }

}
