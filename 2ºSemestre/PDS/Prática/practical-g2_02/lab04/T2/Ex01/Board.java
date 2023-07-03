package T2.Ex01;

public class Board implements JGaloInterface{
    // Variáveis de instância
    char player1;
    char player2;
    char[][] gameBoard;
    char actualPlayer;
    int plays;

    // Construtor
    public Board(char player) {
        // Inicializar variáveis de instância
        this.player1 = 'X';
        this.player2 = 'O';
        this.gameBoard = new char[3][3];
        if (player == ' ') {
            this.actualPlayer = player1;
        } else {
            this.actualPlayer = player;
        }
        this.plays = 0;
    }

    @Override
    // Devolve o jogador que tem a vez
    public char getActualPlayer() {
        return this.actualPlayer;
    }

    @Override
    public boolean setJogada(int lin, int col) {
        int linha = --lin;
        int coluna = --col;
        boolean validPosition;

        // Verificar se a posição é válida
        if (linha < 0 || linha > 2 || coluna < 0 || coluna > 2 || this.gameBoard[lin][col] == player1 || this.gameBoard[linha][coluna] == player2) {
            validPosition = false;
        } else {
            validPosition = true;
        }

        // Se a posição for válida, colocar a jogada no tabuleiro
        if (validPosition) {
            // Colocar a jogada no tabuleiro
            this.gameBoard[lin][col] = getActualPlayer();
            this.plays++;
            // Trocar o jogador
            if (this.actualPlayer == player1) {
                this.actualPlayer = player2;
            } else {
                this.actualPlayer = player1;
            }
            return true;
        // Se a posição não for válida, devolver false
        } else {
            return false;
        }
    }

    @Override
    // Verificar se o jogo terminou
    public boolean isFinished() {
        // Se o jogo terminou, devolver true
        if (checkResult() == player1 || checkResult() == player2 || this.plays == 9) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    // Devolver o vencedor  
    public char checkResult() {
        // Verificar se o jogador 1 ganhou
        if (findWords(this.gameBoard, "XXX")) {
            return player1;
        // Verificar se o jogador 2 ganhou
        } else if (findWords(this.gameBoard, "OOO")) {
            return player2;
        }

        return ' ';
    }

    // Função usada na sopa de letras do guião 1
    public boolean checkMatch(char[][] soup, String word, int row, int col) {
        // Check horizontal right
        if (col + word.length() <= soup[row].length) {
            boolean found = true;
            for (int i = 0; i < word.length(); i++) {
                if (soup[row][col + i] != word.charAt(i)) {
                    found = false;
                    break;
                }
            }
            if (found) {
                return true;
            }
        }

        // Check horizontal left
        if (col >= word.length() - 1) {
            boolean found = true;
            for (int i = 0; i < word.length(); i++) {
                if (soup[row][col - i] != word.charAt(i)) {
                    found = false;
                    break;
                }
            }
            if (found) {
                return true;
            }
        }

        // Check vertical down
        if (row + word.length() <= soup.length) {
            boolean found = true;
            for (int i = 0; i < word.length(); i++) {
                if (soup[row + i][col] != word.charAt(i)) {
                    found = false;
                    break;
                }
            }
            if (found) {
                return true;
            }
        }

        // Check vertical up
        if (row >= word.length() - 1) {
            boolean found = true;
            for (int i = 0; i < word.length(); i++) {
                if (soup[row - i][col] != word.charAt(i)) {
                    found = false;
                    break;
                }
            }
            if (found) {
                return true;
            }
        }

        // Check diagonal down-right
        if (col + word.length() <= soup[row].length && row + word.length() <= soup.length) {
            boolean found = true;
            for (int i = 0; i < word.length(); i++) {
                if (soup[row + i][col + i] != word.charAt(i)) {
                    found = false;
                    break;
                }
            }
            if (found) {
                return true;
            }
        }

        // Check diagonal down-left
        if (col >= word.length() - 1 && row + word.length() <= soup.length) {
            boolean found = true;
            for (int i = 0; i < word.length(); i++) {
                if (soup[row + i][col - i] != word.charAt(i)) {
                    found = false;
                    break;
                }
            }
            if (found) {
                return true;
            }
        }

        // Check diagonal up-right
        if (col + word.length() <= soup[row].length && row >= word.length() - 1) {
            boolean found = true;
            for (int i = 0; i < word.length(); i++) {
                if (soup[row - i][col + i] != word.charAt(i)) {
                    found = false;
                    break;
                }
            }
            if (found) {
                return true;
            }
        }

        // Check diagonal up-left
        if (col >= word.length() - 1 && row >= word.length() - 1) {
            boolean found = true;
            for (int i = 0; i < word.length(); i++) {
                if (soup[row - i][col - i] != word.charAt(i)) {
                    found = false;
                    break;
                }
            }
            if (found) {
                return true;
            }
        }

        return false;
    }

    // Função usada na sopa de letras do guião 1
    public boolean findWords(char[][] soup, String word) {
        // Verificar se a palavra existe na sopa de letras
        for (int row = 0; row < soup.length; row++) {
            for (int col = 0; col < soup.length; col++) {
                Boolean found = checkMatch(soup, word, row, col);
                // Se a palavra for encontrada, devolver true
                if (found) {
                    return true;
                }
            }
        }  
        return false;
    }
}
