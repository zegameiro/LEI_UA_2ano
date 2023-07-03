package lab05.Ex3;

public class Person {

    // Atributs
    private String name;
    private int age;

    // Constructor
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // ---------- Getters ----------
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
    // -----------------------------

    // ---------- Setters ----------

    public void setName(String n) {
        name = n;
    }
    
    public void setAge(int a) {
        age = a;
    }
    // ----------------------------

    public String toString() {
        return getName() + "( Age " + getAge() + ")";
    }
}
