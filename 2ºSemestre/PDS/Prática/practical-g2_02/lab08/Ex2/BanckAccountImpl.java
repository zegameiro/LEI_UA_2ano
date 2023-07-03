package Ex2;

public class BanckAccountImpl implements BankAccount {

    private String bank; 
    private double balance; 

    public BanckAccountImpl(String bank, double initialDeposit) { 
        this.bank = bank; 
        balance = initialDeposit; 
    } 

    public String getBank() { 
        return bank; 
    } 

    @Override 
    public void deposit(double amount) { 
        balance += amount;    
    } 

    @Override 
    public boolean withdraw(double amount) { 
        if (amount > balance ) 
            return false; 
        balance -= amount; 
            return true; 
    } 

    @Override 
    public double balance() { 
        return balance; 
    } 
    
}
