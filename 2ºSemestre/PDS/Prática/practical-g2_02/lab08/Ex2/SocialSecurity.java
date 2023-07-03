package Ex2;

import java.util.*;

public class SocialSecurity {

    private Person per;
    private static ArrayList<Person> pers = new ArrayList<Person>();

    // public SocialSecurity(Person per) {
    //     this.per = per;
    //     this.pers = new ArrayList<Person>();
    // } 

    public static void registPerson(Person p) {
        if (!pers.contains(p))
            pers.add(p);
        else
            System.out.println("A pessoa já se econtra no devido array. Atenção!");

    } 

    public Person getPerson() {
        return per;
    }

    public ArrayList<Person> getSecurities() {
        return pers;
    }

}
