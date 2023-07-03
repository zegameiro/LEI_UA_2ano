package Ex1.b;

import java.util.*;

class Company { 
    
    public static User user; 
    private List<Employee> emps = new ArrayList<>(); 
     
    public void paySalaries(int month) { 
        for (Employee e : emps) {   
        BankAccount ba = e.getBankAccount(); 
        ba.deposit(e.getSalary());  
        } 
    } 
    public List<Employee> employees() { 
        return Collections.unmodifiableList(emps); 
    }
    public void admitEmployee(Person person, int salary) {
        Employee e = new Employee(person, salary); 
        emps.add(e); 
    } 

}
