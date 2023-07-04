package T2.Ex02;

public class Voo {
    private final String codigo;
    private final Aviao aviao;
    private final int capacidadeTuristica;
    private final int capacidadeExecutiva;
    private int reservas;

    public Voo(String codigo, Aviao aviao) {
        this.codigo = codigo;
        this.aviao = aviao;
        capacidadeTuristica = aviao.getCapacidadeTuristica();
        capacidadeExecutiva = aviao.getCapacidadeExecutiva();
        reservas = 0;
    }

    public int getNumReservas() {
        return reservas;
    }

    public String getCodigo() {
        return codigo;
    }

    public Aviao getAviao() {
        return aviao;
    }

    public int getCapacidadeTuristica() {
        return capacidadeTuristica;
    }

    public int getCapacidadeExecutiva() {
        return capacidadeExecutiva;
    }

    public boolean reservar(Classe classe, int numReservas) {
        // reserva lugares para um cliente apos a verificacao de disponibilidade
        int[][] reservas;

        switch (classe) {
            case EXECUTIVA:
                if (aviao.getCapacidadeExecutiva() - aviao.getLugaresOcupadosExecutiva() < numReservas) {
                    return false;
                }
                reservas = aviao.getExecutiva();
                break;
            case TURISTICA:
                if (aviao.getCapacidadeTuristica() - aviao.getLugaresOcupadosTuristica() < numReservas) {
                    return false;
                }
                reservas = aviao.getTuristica();
                break;
            default:
                return false;
        }

        int numBancosFila = reservas[0].length;
        int numFilas = reservas.length;

        int reservados = 0;
        int filaVazia = -1;

        for (int fila = 0; fila < numFilas; fila++) {
            boolean vazia = true;
            for (int banco = 0; banco < numBancosFila; banco++) {
                if (reservas[fila][banco] != 0) {
                    vazia = false;
                    break;
                }
            }

            if (vazia) {
                filaVazia = fila;
                break;
            }
        }

        boolean reservado = false;

        if (filaVazia != -1) {
            for (int fila = filaVazia; fila < numFilas && !reservado; fila++) {
                for (int banco = 0; banco < numBancosFila && !reservado; banco++) {
                    if (reservas[fila][banco] == 0) {
                        reservas[fila][banco] = this.reservas+1;
                        reservados++;
                    }
                    if (reservados == numReservas) {
                        reservado = true;
                    }
                }
            }
            if (reservados < numReservas) {
                for (int fila = 0; fila < filaVazia && !reservado; fila++) {
                    for (int banco = 0; banco < numBancosFila && !reservado; banco++) {
                        if (reservas[fila][banco] == 0) {
                            reservas[fila][banco] = this.reservas+1;
                            reservados++;
                        }
                        
                        if (reservados == numReservas) {
                            reservado = true;
                        }
                    }
                }
            }
        } else {
            for (int fila = 0; fila < numFilas && !reservado; fila++) {
                for (int banco = 0; banco < numBancosFila && !reservado; banco++) {
                    if (reservas[fila][banco] == 0) {
                        reservas[fila][banco] = this.reservas+1;
                        reservados++;
                    }
                    
                    if (reservados == numReservas) {
                        reservado = true;
                    }
                }
            }
        }

        if (reservado) {
            this.reservas += 1;
            if (classe == Classe.EXECUTIVA) {
                aviao.setExecutiva(reservas);
            } else {
                aviao.setTuristica(reservas);
            }
            return true;
        } else {
            return false;
        }
    }

    public void mapaReservas() {
        // Apresenta o mapa de reservas do avião consoante o input do ficheiro de texto
        // A primeira linha apresenta o número de cada fila
        // A primeira coluna apresenta a letra de cada banco
        int[][] turistica = aviao.getTuristica();
        int[][] executiva = aviao.getExecutiva();

        int numFilasE = executiva.length;
        int numFilasT = turistica.length;
        int numFilas = numFilasE + numFilasT;
        int numBancosFilaMaximo = 0;
        if (numFilasE == 0) {
            numBancosFilaMaximo = turistica[0].length;
        } else {
            numBancosFilaMaximo = Math.max(turistica[0].length, executiva[0].length);
        }

        for (int i = 1; i <= numFilas; i++) {
            System.out.print("\t" + i);
        }
        System.out.println();

        for (int banco = 0; banco < numBancosFilaMaximo; banco++) {
            System.out.print((char)(banco+65));
            for (int fila = 0; fila < numFilas; fila++) {
                if (fila < executiva.length) {
                    if (banco < executiva[0].length) {
                        System.out.print("\t" + executiva[fila][banco]);
                    } else {
                        System.out.print("\t");
                    }
                } else {
                    if (numFilasE > 0) {
                        System.out.print("\t" + turistica[fila-numFilasE][banco]);
                    } else {
                        System.out.print("\t" + turistica[fila][banco]);
                    }
                    
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean cancelarReserva(int numReserva) {
        // cancela a reserva com o número numReserva
        // retorna true se a reserva foi cancelada com sucesso
        // retorna false se a reserva não existe
        // se o utlizador utilizar a opção "M" do menu, consegue ver a diferenca no mapa de reservas
        if (numReserva > this.reservas) {
            return false;
        }

        int[][] turistica = this.aviao.getTuristica();
        int[][] executiva = this.aviao.getExecutiva();

        int numFilasE = executiva.length;
        int numFilasT = turistica.length;
        int numFilas = numFilasE + numFilasT;
        int numBancosFilaMaximo = 0;
        if (executiva.length == 0) {
            numBancosFilaMaximo = turistica[0].length;
        } else {
            numBancosFilaMaximo = Math.max(turistica[0].length, executiva[0].length);
        }

        for (int fila = 0; fila < numFilas; fila++) {
            for (int banco = 0; banco < numBancosFilaMaximo; banco++) {
                if (fila < numFilasE) {
                    if (banco < executiva[0].length) {
                        if (executiva[fila][banco] == numReserva) {
                            executiva[fila][banco] = 0;
                        }
                    }
                } else {
                    if (numFilasE == 0) {
                        if (turistica[fila][banco] == numReserva) {
                            turistica[fila][banco] = 0;
                        }
                    } else {
                        if (turistica[fila - numFilasE][banco] == numReserva) {
                            turistica[fila - numFilasE][banco] = 0;
                        }
                    }
                }
            }
        }

        this.aviao.setExecutiva(executiva);
        this.aviao.setTuristica(turistica);
        return true;
    }

    @Override
    public String toString() {
        return "Voo " + codigo + " com " + capacidadeTuristica + " lugares turisticos e " + capacidadeExecutiva + " lugares executivos";
    }
}
