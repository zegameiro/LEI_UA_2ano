package T1;

public class JGaloMethods implements JGaloInterface {
    private int round = 0;
    private char[] board = new char[9];

    // Store the type of win
    private int winPos;

    public JGaloMethods() {
        for (int i = 0; i < 9; i++)
            board[i] = '\0';
    }

    @Override
    public char getActualPlayer() {
        return round % 2 == 0 ? 'X' : 'O';
    }

    @Override
    public boolean setJogada(int lin, int col) {
        board[3 * (lin - 1) + col - 1] = getActualPlayer();
        round++;
        return board[3 * (lin - 1) + col - 1] != '\0';
    }

    @Override
    public boolean isFinished() {
        if (round == 9) {
            winPos = 9;
            return true;
        }
        for (int i = 0; i < 9; i++) {
            winPos = i;
            // Horizontal
            if (i % 3 == 0 && board[i] != '\0' && board[i] == board[i + 1] && board[i] == board[i + 2]) {
                return true;
            }
            // Vertical
            if (i < 3 && board[i] != '\0' && board[i] == board[i + 3] && board[i] == board[i + 6]) {
                return true;
            }
            // Diagonal (left to right)
            if (i == 0 && board[i] != '\0' && board[i] == board[i + 4] && board[i] == board[i + 8]) {
                return true;
            }
            // Diagonal (right to left)
            if (i == 2 && board[i] != '\0' && board[i] == board[i + 2] && board[i] == board[i + 4]) {
                return true;
            }
        }
        return false;
    }

    @Override
    public char checkResult() {
        return round == 9 ? ' ' : board[winPos];
    }

}
