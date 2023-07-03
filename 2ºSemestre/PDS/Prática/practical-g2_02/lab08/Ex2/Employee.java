package Ex2;

public class Employee {

    // não vai ter acesso às 2 operações

    private double salary;
    private Person person;
    private BankAccount account;

    public Employee(Person person, double salary) {
        this.salary = salary;
        this.person = person;
        account = new BankAccountImplProxy(new BanckAccountImpl("banco", 0));
    }

    public double getSalary() {
		return salary;
	}

    public Person getPerson() {
        return person;
    }

    public BankAccount getBankAccount() {
        return account;
    }

    public void facade() {
        
    }
    
 
}