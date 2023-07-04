package Ex1;
public class Galo implements JGaloInterface{
    
    private char[][] board; // The board of the game
    private char actualPlayer; // The actual player 
    private int moves;  // The number of moves made

    public Galo() {
        // Initialize the board 3x3
        board = new char[3][3]; // ai ai e as linked lists
        for(int i = 0 ; i < board.length; i++) {
            for(int j = 0; j < board.length ; j++)
                board[i][j] = 'N'; // N de nada
        }

        // Initialize the actual player
        actualPlayer = 'X';

        // Initialize the number of moves
        moves = 0;

    }

    @Override
    public char getActualPlayer() {
        return actualPlayer;
    }

    /*
    * Function to change the actual player when a move is made
    */
    private void changePlayer() {
        if(getActualPlayer() == 'X')
            actualPlayer = 'O';
        else 
            actualPlayer = 'X';
    }

    /*
     * Function to call when a move is made
     * It checks if the move is valid, marks on the board the move
     * and changes the player
     */
    @Override
    public boolean setJogada(int lin, int col) {
        if(board[lin-1][col-1] == 'N') {
            board[lin-1][col-1] = getActualPlayer();
            changePlayer();
            moves++;
            return true;
        } else 
            return false;
    }

    /*
     * This function checks if a row has all the elements equal
     */
    public boolean checkRows() {
        for(int i = 0 ; i < board.length ; i++){
            if (board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] != 'N')
                return true;
            else 
                continue;
        }


        return false;
    }

    /*
     * This function checks if a column has all the elements equal
     */
    public boolean checkColumns() {
        for(int j = 0; j < board.length ; j++) {
            if (board[0][j] == board[1][j] && board[0][j] == board[2][j] && board[0][j] != 'N')
                return true;
            else
                continue;
        }

        return false;
    }

    /*
     * This function checks if the diagonals of the board have the same elemente
     * In this board there's only 2 possible diagonals which are :
     *  + - -
     *  - + -
     *  - - +
     *  
     *  - - +
     *  - + -
     *  + - -
     */

    public boolean checkDiagonals() {
        if(board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] != 'N' || 
        board[2][0] == board[1][1] && board[2][0] == board[0][2] && board[2][0] != 'N') {
            return true;
        }
        else
            return false;
    }

    /*
     * Function to check if one of the players won
     */
    public boolean isWinner() {
        if(checkRows() || checkColumns() || checkDiagonals())
            return true;
        else 
            return false;
    }

    /*
     * Function to check if the game is finished
     * The game can finish if one of the following conditions if true:
     * Either the player won by making three in a row, column or diagonal
     * Or the board is completly full meaning that the number of moves is 9
     */
    @Override
    public boolean isFinished() {
        if(isWinner() || moves == 9) 
            return true;
        else  
            return false;
    }

    /*
     * This function check if someone won and returns who was that person
     * by calling the function isWinner but if this function returns false 
     * then the fuction checkResult will return ' ' meaning that there was a draw
     */
    @Override
    public char checkResult() {
        if(isWinner()) {
            // The function changePlayer() is called because one player made the winning move 
            // and then the program advances to the other player
            changePlayer(); 
            return getActualPlayer();
        } else 
            return ' ';
    }
}
