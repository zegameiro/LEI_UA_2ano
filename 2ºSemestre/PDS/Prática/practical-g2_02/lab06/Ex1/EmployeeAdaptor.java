package lab06.Ex1;

public class EmployeeAdaptor{

    private String name;
    private long emp_num;
    private double salary;

    public EmployeeAdaptor(String name, long emp_num, double salary) {
        this.name = name;
        this.emp_num = emp_num;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public long getEmpNum() {
        return emp_num;
    }

    public double getSalary() {
        return salary;
    }
   
}
