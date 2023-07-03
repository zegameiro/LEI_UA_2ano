import java.util.ArrayList;

public class StudentAdm {

    private Student student;
    private ArrayList<Monitor> monitors = new ArrayList<Monitor>();

    public void attach(Monitor m) {
        monitors.add(m);
    }

    public StudentAdm(Student studnt) {
        this.student = studnt;
    }

    public void addScore(String className, double score) {
        student.addScore(className, (Double) score);
        notifyMonitor();
    }

    public Student getStudent() {
        return student;
    }

    private void notifyMonitor() {
        for(Monitor m : monitors) {
            m.update();
        }
    }

    public String toString() {
        return student.toString();
    }
    
}








