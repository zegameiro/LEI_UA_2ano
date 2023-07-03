package lab10.Ex2;

public class NullProgrammer extends Employee {

    public NullProgrammer() { 
        this.name = "Not Available";
    } 

    @Override
    public String getName() {
        return name;
    }
    
}
