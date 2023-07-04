package Ex2;

public class BankAccountImplProxy implements BankAccount {

    private BanckAccountImpl executor;

    public BankAccountImplProxy(BanckAccountImpl executor){
        this.executor = executor;
    }    

    @Override
    public void deposit(double amount) {
        executor.deposit(amount);
    }

    private boolean IsUser(){
        return (Company.user == User.OWNER);
    }

    @Override
    public boolean withdraw(double amount) {
        if (IsUser()){
            return executor.withdraw(amount);
        }
        System.out.println("Aviso de segurança!");
        return false;
    }

    @Override
    public double balance() {
        if (IsUser()) {
            return executor.balance();
        }
        System.out.println("Aviso de segurança!");
        return Double.NaN;
    }
    
}
