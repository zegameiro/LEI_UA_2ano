package T2.Ex02;

public class Aviao {
    // 0 = vazio
    private int[][] executiva;
    private int[][] turistica;

    // construtor
    public Aviao(int[][] executiva, int[][] turistica) {
        this.executiva = executiva;
        this.turistica = turistica;
    }

    // getters e setters
    // retorna a capacidade total da classe executiva
    public int[][] getExecutiva() {
        return executiva;
    }

    // retorna a capacidade total da classe executiva
    public int[][] getTuristica() {
        return turistica;
    }

    // retorna a capacidade total da classe executiva
    public void setExecutiva(int[][] executiva) {
        this.executiva = executiva;
    }

    // retorna a capacidade total da classe turistica
    public void setTuristica(int[][] turistica) {
        this.turistica = turistica;
    }

    // retorna a capacidade total da classe turistica
    public int getCapacidadeTuristica() {
        return turistica.length * turistica[0].length;
    }

    // retorna a capacidade total da classe executiva
    public int getCapacidadeExecutiva() {
        if (executiva.length == 0) {
            return 0;
        }
        return executiva.length * executiva[0].length;
    }

    // retorna a quantidade de lugares ocupados na classe turistica
    public int getLugaresOcupadosTuristica() {
        int ocupados = 0;

        // percorre a matriz
        for (int i = 0; i < turistica.length; i++) {
            for (int j = 0; j < turistica[i].length; j++) {
                // se o lugar estiver ocupado
                if (turistica[i][j] != 0) {
                    ocupados++;
                }
            }
        }

        return ocupados;
    }

    // retorna a quantidade de lugares ocupados na classe executiva
    public int getLugaresOcupadosExecutiva() {
        int ocupados = 0;

        // percorre a matriz
        for (int i = 0; i < executiva.length; i++) {
            for (int j = 0; j < executiva[i].length; j++) {
                // se o lugar estiver ocupado
                if (executiva[i][j] != 0) {
                    ocupados++;
                }
            }
        }

        return ocupados;
    }

    public String toString() {
        return "Aviao: " + getCapacidadeTuristica() + " turistica, " + getCapacidadeExecutiva() + " executiva";
    }
}
