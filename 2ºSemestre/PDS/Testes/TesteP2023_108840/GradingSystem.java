import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Iterator;

// GradingSystem class
public class GradingSystem {
    List<StudentAdm> studentsAdm = new ArrayList<>();
    List<Professor> professors = new ArrayList<>();
    List<Director> directors = new ArrayList<>();

    // Add a student to the system
    // The director of student's course is added as Monitor
    public StudentAdm addStudent(Student student) {
        StudentAdm studentAdm = new StudentAdm(student);
        studentsAdm.add(studentAdm);
        return studentAdm;
    }   

    // Add a director to the system
    // The director is added as monitor of the students in the same course
    public void addDirector(Course course) {
        Director dir;

        for(StudentAdm std : studentsAdm) {
            if(std.getStudent().getCourse() == course) {
                dir = new Director(std, course);
                directors.add(dir);
            } else {
                continue;
            }
        }
    } 

    public void addProfessor(Professor professor) {
        professors.add(professor);
    }


    public List<Director> getDirectors() {
        return directors;
    }

    public List<Professor> getProfessors() {
        return professors;
    }

    public int getTotalStudents(Course c) {
        int totalStudents = 0;

        for(StudentAdm st : studentsAdm) {
            if(st.getStudent().getCourse() == c) {
                totalStudents += 1;
            }
        }

        return totalStudents;
    }

    public StudentAdm getElem(int i){
        return (StudentAdm) this.studentsAdm.get(i);
    }

    public GradingSystem.StudentIterator iterator(Course c) {
        return new StudentIterator(c);
    }

    private class StudentIterator implements Iterator<StudentAdm> {

        private int index;
        private Course c;

        public StudentIterator(Course c) {
            index = 0;
            this.c = c;
        }

        @Override
        public boolean hasNext() {
            if(index < getTotalStudents(c))
                return true;
            else
                return false;
        }

        @Override
        public StudentAdm next() {
            if(hasNext())
                return GradingSystem.this.getElem(index++);

            throw new NoSuchElementException(String.format("There are only %d elements!", getTotalStudents(this.c)));
        }

    }

}