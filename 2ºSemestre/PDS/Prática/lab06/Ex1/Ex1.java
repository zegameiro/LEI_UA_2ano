package lab06.Ex1;

public class Ex1 {

    public static void main(String[] args) {

        Database db = new Database();
        Employee e1 = new Employee("Diogo", 126789, 1000);
        Employee e2 = new Employee("José", 67834, 1000);
        Employee e3 = new Employee("Maria", 1234, 1000);

        System.out.println(db.toString());

        db.addEmployee(e1);
        db.addEmployee(e2);
        db.addEmployee(e3);

        System.out.println(db.toString());

        db.deleteEmployee(1234);

        System.out.println(db.toString());

        Employee[] employees = db.getAllEmployees();

        for (Employee e : employees) {
            System.out.println(e);
        }

        Database dbi = new Database();
        
        System.out.println(dbi.toString());

        Registos r = new Registos();

        RegistosAdaptor ra = new RegistosAdaptor(r);

        System.out.println(ra.toString());

        ra.addEmployee(e1);

        System.out.println(ra.toString());

        ra.addEmployee(e2);

        System.out.println(ra.toString());

        ra.addEmployee(e3);

        System.out.println(ra.toString());

        ra.deleteEmployee(1);

        System.out.println(ra.toString());

        ra.getAllEmployees();

    }
    
}