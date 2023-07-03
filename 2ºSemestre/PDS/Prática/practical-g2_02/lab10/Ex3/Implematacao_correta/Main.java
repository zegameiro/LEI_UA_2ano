package Implematacao_correta;

public class Main {
    public static void main(String[] args) {
        Torre t = new Torre();

        Aviao a1 = new Aviao("Boing777", "X");
        Helicoptero h1 = new Helicoptero("S4315", "Y");
        Zepelim z1 = new Zepelim("FSDG323", "Z");
        Aviao a2 = new Aviao("BVDC334", "X");
        Helicoptero h2 = new Helicoptero("SGDS543", "B");

        Veiculo lst[] = {a1,a2,h1,h2,z1};

        for (Veiculo v : lst) t.addFly(v);

        Helicoptero h3 = new Helicoptero("OUYT324", "X");
        Aviao a3 = new Aviao("HSHST33", "B");
        Zepelim z2 = new Zepelim("FSDG323", "Z");

        Veiculo lst1[] = {h3,a3,z2};

        for (Veiculo v : lst1) t.addFly(v);
    }
}
