package lab05.Ex2;

public class Cream {

    private String cream;

    public Cream(String cream) {
        this.cream = cream;
    }

    public String getCream() {
        return cream;
    }

    public void setCream(String c) {
        cream = c;
    }

    public String toString() {
        return getCream();
    }
}
