package Ex2;

public class Person {

    private String name;
    private static double salary;

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, double salary) {
        this.name = name;
        Person.salary = salary;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

}
