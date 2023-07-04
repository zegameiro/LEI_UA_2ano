package lab09.Ex1;

import java.util.Iterator;
import java.util.ListIterator;

public class Ex1 {
    public static void main(String[] args) {


        System.out.println("----------------------------------- VectorGeneric -----------------------------------");

        // Test of addElem()
        VectorGeneric<String> v1 = new VectorGeneric<>(10);
        v1.addElem("a");
        v1.addElem("b");
        v1.addElem("c");
        System.out.println("v1: " + v1.totalElem() + " elementos"); // esperado: 3 elementos

        // Test off removeElem() 
        VectorGeneric<Integer> v2 = new VectorGeneric<>(10);
        v2.addElem(1);
        v2.addElem(2);
        v2.addElem(3);
        v2.removeElem(2);
        System.out.println("v2: " + v2.totalElem() + " elementos"); // esperado: 2 elementos


        System.out.println("-------------------------------------- Iterator --------------------------------------");
        VectorGeneric<Double> v3 = new VectorGeneric<>(10);
        v3.addElem(1.0);
        v3.addElem(2.0);
        v3.addElem(3.0);
        Iterator<Double> it1 = v3.iterator();
        while(it1.hasNext()) {
            System.out.println(it1.next());
        }

        System.out.println("---------------------------------- VectorIterator ----------------------------------");
        VectorGeneric<Integer> v4 = new VectorGeneric<>(10);
        v4.addElem(1);
        v4.addElem(2);
        v4.addElem(3);
        ListIterator<Integer> it2 = v4.listIterator(1);
        while(it2.hasNext()) {
            System.out.println(it2.next());
        }

        System.out.println("----------------------------------- Test Multiple Iterators -----------------------------------");
        VectorGeneric<String> v5 = new VectorGeneric<>(10);
        v5.addElem("a");
        v5.addElem("b");
        v5.addElem("c");
        Iterator<String> it3 = v5.iterator();
        ListIterator<String> it4 = v5.listIterator();
        System.out.println(it3.next()); 
        System.out.println(it4.next());
        System.out.println(it4.next()); 
        System.out.println(it3.next()); 
    }
}
