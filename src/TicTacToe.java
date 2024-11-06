import java.util.Scanner;

public class TicTacToe
{
    /**
     * a constant that contains the number of rows in the board array
     */
    private static final int ROWS = 3;

    /**
     * a constant that contains the number of columns in the board array
     */
    private static final int COLS = 3;
    /**
     * a two-dimensional array used to represent the 9 indices where moves can be made in Tic Tac Toe; holds String input
     */
    private static String board [][] = new String[ROWS][COLS];

    public static void main(String[] args)
    {
        /**
         * the Scanner that will be used to take player input
         */
        Scanner in = new Scanner(System.in);
        /**
         * a String that will be used to track if the player is currently X or O
         */
        String player = "";
        /**
         * an int that holds the row coordinate/index of the player's chosen move
         */
        int rowMove = 0;
        /**
         * an int that holds the column coordinate/index of the player's chosen move
         */
        int colMove = 0;
        /**
         * an int that tracks how many moves have been made so far
         */
        int moveCount = 0;
        /**
         * a boolean that holds a true or false value imparted from the isValidMove method; used to determine if the player placed a move in an open/valid space
         */
        boolean isValidMove = false;
        /**
         * a boolean that holds a true or false value imparted from the getYNConfirm method in SafeInput; used to determine if the players will play again
         */
        boolean playAgain = false;

        /**
         * this algorithm allows users to play Tic Tac Toe by toggling between players, allowing players to select coordinates for their moves, and testing if players have won or tied; the algorithm also resets the move counter and clears the board, as well as checking if players will play again
         */
        do {
            clearBoard();
            moveCount = 0;
            player = "X";
            /**
             * this algorithm allows users to play Tic Tac Toe by toggling between players, allowing players to select coordinates for their moves, and testing if players have won or tied
             */
            do {
                /**
                 * this algorithm is used to toggle players after each turn by testing which player just played (after turn 0)
                 */
                if (moveCount != 0) {
                    if (player.equals("X")) {
                        player = "O";
                    } else {
                        player = "X";
                    }
                }

                display();

                /**
                 * this algorithm is used to get the player's move coordinate, decrement it so that it corresponds to the array indices, and test if the move is valid
                 */
                do {
                    rowMove = SafeInput.getRangedInt(in, "Player " + player + ", enter the row coordinate of your move", 1, 3);
                    colMove = SafeInput.getRangedInt(in, "Player " + player + ", enter the column coordinate of your move", 1, 3);

                    rowMove = rowMove - 1;
                    colMove = colMove - 1;

                    isValidMove = isValidMove(rowMove, colMove);
                } while (!isValidMove);

                moveCount++;
                board[rowMove][colMove] = player;

                /**
                 * this algorithm tests if the current player has won at or after turn 5 and outputs the board and a congratulatory message if a player has won
                 */
                if (moveCount >= 5) {
                    isWin(player);

                    if(isWin(player)) {
                        display();
                        System.out.println("\nCongratulations, Player " + player + ", you won!");
                    }
                }

                /**
                 * this algorithm tests if there is a tie after turn 7 if neither player has won yet and outputs the board and a notification of the tie, if there is one
                 */
                if (!isWin(player) && moveCount >= 7) {
                    isTie();

                    if(isTie()) {
                        display();
                        System.out.println("\nThe players tied!");
                    }
                }
            } while (!isWin(player) && !isTie());

            playAgain = SafeInput.getYNConfirm(in, "Would you like to play again?");
        }while (playAgain);
    }

    /**
     * Fills all indices in the board array with spaces
     */
    private static void clearBoard()
    {
        for(int x = 0; x < ROWS; x++)
        {
            for(int y = 0; y < COLS; y++)
            {
                board[x][y] = " ";
            }
        }
    }

    /**
     * Loops through and prints out the values in the board array with additional formatting
     */
    private static void display()
    {
        System.out.println("\nGame Board:");
        for(int x = 0; x < ROWS; x++)
        {
            for(int y = 0; y < COLS; y++)
            {
                System.out.print(board[x][y]);

                if(y != 2) {
                    System.out.print(" | ");
                }
            }
            System.out.println();

            if(x != 2) {
                System.out.println("--+---+--");
            }
        }
    }

    /**
     * tests if the index the player chose for their move is valid (i.e., has a space in it) or invalid (has an X or an O)
     *
     * @param row the row value chosen by the player
     * @param col the column value chosen by the player
     * @return a boolean value representing the validity of the player's move location (true/false)
     */
    private static boolean isValidMove(int row, int col)
    {
        /**
         * holds a boolean value representing whether the player's move is valid
         */
        boolean isValidMove = false;
            if (board[row][col].equals(" ")) {
                isValidMove = true;
            } else {
                System.out.println("\nYou can only enter values in empty spaces. You tried to enter a value at index [" + row + ", " + col + "], which is full. Your move is invalid.");
            }
        return isValidMove;
    }

    /**
     * determines whether the current player has won by employing three other methods to test for specific win states
     *
     * @param player the player whose turn it currently is (X or O)
     * @return a boolean value representing whether the player has won (true/false)
     */
    private static boolean isWin(String player)
    {
        if(isColWin(player) || isRowWin(player) || isDiagonalWin(player)) {
            return true;
        }
        return false;
    }

    /**
     * tests whether the current player has achieved a column win by placing three of their characters in a given column
     *
     * @param player the player whose turn it currently is (X or O)
     * @return a boolean value representing whether the player has achieved a column win (true/false)
     */
    private static boolean isColWin(String player)
    {
        for(int col = 0; col < COLS; col++)
        {
            if(board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * tests whether the current player has achieved a row win by placing three of their characters in a given row
     *
     * @param player the player whose turn it currently is (X or O)
     * @return a boolean value representing whether the player has achieved a row win (true/false)
     */
    private static boolean isRowWin(String player)
    {
        for(int row = 0; row < ROWS; row++)
        {
            if(board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * tests whether the current player has achieved a diagonal win by placing three of their characters diagonally and uninterrupted
     *
     * @param player the player whose turn it currently is (X or O)
     * @return a boolean value representing whether the player has achieved a diagonal win (true/false)
     */
    private static boolean isDiagonalWin(String player)
    {
        if(board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player))
        {
            return true;
        } else if(board[2][0].equals(player) && board[1][1].equals(player) && board[0][2].equals(player)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Tests whether the game has tied; i.e., if it is no longer possible for either player to win
     *
     * @return a boolean value representing whether the game has tied (true/false)
     */
    private static boolean isTie()
    {
        /**
         * boolean value representing whether an X is present in row 1
         */
        boolean xFoundRow1 = false;
        /**
         * boolean value representing whether an O is present in row 1
         */
        boolean oFoundRow1 = false;
        /**
         * boolean value representing whether an X is present in row 2
         */
        boolean xFoundRow2 = false;
        /**
         * boolean value representing whether an O is present in row 2
         */
        boolean oFoundRow2 = false;
        /**
         * boolean value representing whether an X is present in row 3
         */
        boolean xFoundRow3 = false;
        /**
         * boolean value representing whether an O is present in row 3
         */
        boolean oFoundRow3 = false;
        /**
         * boolean value representing whether there is a tie in row 1
         */
        boolean row1 = false;
        /**
         * boolean value representing whether there is a tie in row 2
         */
        boolean row2 = false;
        /**
         * boolean value representing whether there is a tie in row 3
         */
        boolean row3 = false;
        /**
         * boolean value representing whether there is a tie in all rows
         */
        boolean rowTie = false;
        /**
         * boolean value representing whether an X is present in column 1
         */
        boolean xFoundCol1 = false;
        /**
         * boolean value representing whether an O is present in column 1
         */
        boolean oFoundCol1 = false;
        /**
         * boolean value representing whether an X is present in column 2
         */
        boolean xFoundCol2 = false;
        /**
         * boolean value representing whether an O is present in column 2
         */
        boolean oFoundCol2 = false;
        /**
         * boolean value representing whether an X is present in column 3
         */
        boolean xFoundCol3 = false;
        /**
         * boolean value representing whether an O is present in column 3
         */
        boolean oFoundCol3 = false;
        /**
         * boolean value representing whether there is a tie in column 1
         */
        boolean col1 = false;
        /**
         * boolean value representing whether there is a tie in column 2
         */
        boolean col2 = false;
        /**
         * boolean value representing whether there is a tie in column 3
         */
        boolean col3 = false;
        /**
         * boolean value representing whether there is a tie in all columns
         */
        boolean colTie = false;
        /**
         * boolean value representing whether an X is present in diagonal 1 (starts at [0,0], ends at [2,2])
         */
        boolean xFoundDiag1 = false;
        /**
         * boolean value representing whether an O is present in diagonal 1 (starts at [0,0], ends at [2,2])
         */
        boolean oFoundDiag1 = false;
        /**
         * boolean value representing whether an X is present in diagonal 2 (starts at [0,2], ends at [2,0])
         */
        boolean xFoundDiag2 = false;
        /**
         * boolean value representing whether an O is present in diagonal 2 (starts at [0,2], ends at [2,0])
         */
        boolean oFoundDiag2 = false;
        /**
         * boolean value representing whether there is a tie in diagonal 1
         */
        boolean diag1 = false;
        /**
         * boolean value representing whether there is a tie in diagonal 2
         */
        boolean diag2 = false;
        /**
         * boolean value representing whether there is a tie in all diagonals
         */
        boolean diagTie = false;
        /**
         * boolean value representing whether the game has tied
         */
        boolean isTie = false;

        for(int row = 0; row < ROWS; row++)
        {
            for(int col = 0; col < COLS; col++)
            {
                if(board[0][0].equals("X"))
                {
                    xFoundRow1 = true;
                    xFoundCol1 = true;
                    xFoundDiag1 = true;
                }

                if(board[0][0].equals("O"))
                {
                    oFoundRow1 = true;
                    oFoundCol1 = true;
                    oFoundDiag1 = true;
                }

                if(board[0][1].equals("X"))
                {
                    xFoundRow1 = true;
                    xFoundCol2 = true;
                }

                if(board[0][1].equals("O"))
                {
                    oFoundRow1 = true;
                    oFoundCol2 = true;
                }

                if(board[0][2].equals("X"))
                {
                    xFoundRow1 = true;
                    xFoundCol3 = true;
                    xFoundDiag2 = true;
                }

                if(board[0][2].equals("O"))
                {
                    oFoundRow1 = true;
                    oFoundCol3 = true;
                    oFoundDiag2 = true;
                }

                if(board[1][0].equals("X"))
                {
                    xFoundRow2 = true;
                    xFoundCol1 = true;
                }

                if(board[1][0].equals("O"))
                {
                    oFoundRow2 = true;
                    oFoundCol1 = true;
                }

                if(board[1][1].equals("X"))
                {
                    xFoundRow2 = true;
                    xFoundCol2 = true;
                    xFoundDiag1 = true;
                    xFoundDiag2 = true;
                }

                if(board[1][1].equals("O"))
                {
                    oFoundRow2 = true;
                    oFoundCol2 = true;
                    oFoundDiag1 = true;
                    oFoundDiag2 = true;
                }

                if(board[1][2].equals("X"))
                {
                    xFoundRow2 = true;
                    xFoundCol3 = true;
                }

                if(board[1][2].equals("O"))
                {
                    oFoundRow2 = true;
                    oFoundCol3 = true;
                }

                if(board[2][0].equals("X"))
                {
                    xFoundRow3 = true;
                    xFoundCol1 = true;
                    xFoundDiag2 = true;
                }

                if(board[2][0].equals("O"))
                {
                    oFoundRow3 = true;
                    oFoundCol1 = true;
                    oFoundDiag2 = true;
                }

                if(board[2][1].equals("X"))
                {
                    xFoundRow3 = true;
                    xFoundCol2 = true;
                }

                if(board[2][1].equals("O"))
                {
                    oFoundRow3 = true;
                    oFoundCol2 = true;
                }

                if(board[2][2].equals("X"))
                {
                    xFoundRow3 = true;
                    xFoundCol3 = true;
                    xFoundDiag1 = true;
                }

                if(board[2][2].equals("O"))
                {
                    oFoundRow3 = true;
                    oFoundCol3 = true;
                    oFoundDiag1 = true;
                }

                if(xFoundRow1 && oFoundRow1) {
                    row1 = true;
                }

                if(xFoundRow2 && oFoundRow2) {
                    row2 = true;
                }

                if(xFoundRow3 && oFoundRow3) {
                    row3 = true;
                }

                if(row1 && row2 && row3) {
                    rowTie = true;
                }

                if(xFoundCol1 && oFoundCol1) {
                    col1 = true;
                }

                if(xFoundCol2 && oFoundCol2) {
                    col2 = true;
                }

                if(xFoundCol3 && oFoundCol3) {
                    col3 = true;
                }

                if(col1 && col2 && col3) {
                    colTie = true;
                }

                if(xFoundDiag1 && oFoundDiag1) {
                    diag1 = true;
                }

                if(xFoundDiag2 && oFoundDiag2) {
                    diag2 = true;
                }

                if(diag1 && diag2) {
                    diagTie = true;
                }

                if(rowTie && colTie && diagTie) {
                    isTie = true;
                    return isTie;
                }else {
                    isTie = false;
                }

                if(board[row][col].equals(" "))
                {
                    isTie = false;
                    break;
                }else {
                    isTie = true;
                }
            }
        }
        return isTie;
    }
}
