public class Professor extends Monitor {

    private String className;
    private String name;
    private StudentAdm adm;

    public Professor(StudentAdm adm, String name, String className) {
        this.name = name;
        this.className = className;
        this.adm = adm;
        adm.attach(this);
    }

    public String getName() {
        return name;
    }

    public String getClassName() {
        return className;
    }

    @Override
    public void update() {
        if(adm.getStudent().getScore(getClassName()) != null)
            System.out.println("Professor " + getName() + " of " + getClassName() + " class evaluated student " + adm.getStudent().toString() + " with score: " + adm.getStudent().getScore(getClassName()));
    }

}
