package Ex1.a;


class Person { 

    private String name; 
    private BankAccount bankAccount; 
    
    public Person(String n) { 
        name = n; 
        bankAccount = new BankAccountImplProxy(new BankAccountImpl(n, 0)); 
    } 
    public String getName() { 
        return name; 
    } 
    public BankAccount getBankAccount() { 
        return bankAccount; 
    } 
   }
