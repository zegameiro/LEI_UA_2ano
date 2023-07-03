package Implematacao_correta;

import java.util.ArrayList;

public class Torre implements TorreInterface{
    ArrayList<Veiculo> flyList = new ArrayList<Veiculo>();

    public void checkTraffic(Veiculo v) {
        System.out.println("===================== "+ v.toString() +" =================================");
        ArrayList<Veiculo> lst = new ArrayList<>();
        for (Veiculo veiculo : flyList){
            if (veiculo.getModelo() != v.getModelo() && veiculo.getRota() == v.getRota()) {
                lst.add(veiculo);
            }
        }

        if (lst.isEmpty()) {
            System.out.println("Por enquanto, tem o caminho livre.");
        } else {
            System.out.println("ATENÇÃO! Há "+ lst.size() +" veiculo(s) na sua rota:");
            for (Veiculo vei : lst)
                System.out.println("\t"+ vei.toString());
        }
        System.out.println("Tome atenção!");
    }

    public void addFly(Veiculo v) {
        this.flyList.add(v);
        this.checkTraffic(v);
    }
}
