package Ex2;

public class Parking {

    private static double salaryThreshold = 5000;

    public static boolean allow(Person person) {
        if (person.getSalary() > salaryThreshold) {
            return true;
        } else {
            return false;
        }
    }

    
}
