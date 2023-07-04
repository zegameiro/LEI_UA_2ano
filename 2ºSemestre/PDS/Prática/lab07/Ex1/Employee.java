package lab07.Ex1;

import java.util.Date;

public class Employee implements Tarefas {

    private String name;
    private int id;
    private double salary;
    private Date inicio = null;
    private Date fim = null;

    public Employee(String name, int id, double salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public void setFim(Date fim) {
        this.fim = fim;
    }

    @Override
    public String toString() {
        return "Employee{" + "name=" + name + ", id=" + id + ", salary=" + salary + '}';
    }

    @Override
    public void start(Date data) {
        if (work(0)){
            System.out.println(this.name + "with id " + this.id + ", is working");
        }else{
            this.inicio = data;
            System.out.println(this.name + "with id " + this.id + ", is working since: " + data.toString());
        }
    }

    @Override
    public boolean terminate(Date data) {
        if (work(0)){
            this.fim = data;
            System.out.print(this.name + ", stoped working:" + data.toString() + "!\n");
            this.inicio = null;
            return true;
        }
        System.out.print(this.name + " insn't working");
        return false;
    }

    @Override
    public boolean work(int num) {
        if (inicio != null) {
            if (num == 1){
                System.out.println( this.name + ", is working!");
            }
            return true;
        }
        if (num == 1){
            System.out.println(this.name + ", doesn't work");
        }
        return false;
    }

    @Override
    public String getNome() {
        return name;
    }

    @Override
    public int getId() {
        return id;
    }

    // eliminar
    public Date useYellow() {
        return fim;
    }
    
}
