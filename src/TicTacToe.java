import java.util.Scanner;

public class TicTacToe
{
    //WRITE JAVADOC FOR METHODS FUNCTIONS AND VARIABLES

    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String board [][] = new String[ROWS][COLS];

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        String player = "";
        int rowMove = 0;
        int colMove = 0;
        int moveCount = 0;
        boolean isValidMove = false;
        boolean playAgain = false;

        do {
            clearBoard();
            moveCount = 0;
            player = "X";
            do {
                if (moveCount != 0) {
                    if (player.equals("X")) {
                        player = "O";
                    } else {
                        player = "X";
                    }
                }

                display();

                do {
                    rowMove = SafeInput.getRangedInt(in, "Player " + player + ", enter the row coordinate of your move", 1, 3);
                    colMove = SafeInput.getRangedInt(in, "Player " + player + ", enter the column coordinate of your move", 1, 3);

                    rowMove = rowMove - 1;
                    colMove = colMove - 1;

                    isValidMove = isValidMove(rowMove, colMove);
                } while (!isValidMove);

                moveCount++;
                board[rowMove][colMove] = player;

                if (moveCount >= 5) {
                    isWin(player);

                    if(isWin(player)) {
                        display();
                        System.out.println("\nCongratulations, Player " + player + ", you won!");
                    }
                }

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

    private static boolean isValidMove(int row, int col)
    {
        boolean isValidMove = false;
            if (board[row][col].equals(" ")) {
                isValidMove = true;
            } else {
                System.out.println("\nYou can only enter values in empty spaces. You tried to enter a value at index [" + row + ", " + col + "], which is full. Your move is invalid.");
            }
        return isValidMove;
    }

    private static boolean isWin(String player)
    {
        if(isColWin(player) || isRowWin(player) || isDiagonalWin(player)) {
            return true;
        }
        return false;
    }

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

    private static boolean isTie()
    {
        boolean xFoundRow1 = false;
        boolean oFoundRow1 = false;
        boolean xFoundRow2 = false;
        boolean oFoundRow2 = false;
        boolean xFoundRow3 = false;
        boolean oFoundRow3 = false;
        boolean row1 = false;
        boolean row2 = false;
        boolean row3 = false;
        boolean rowTie = false;
        boolean xFoundCol1 = false;
        boolean oFoundCol1 = false;
        boolean xFoundCol2 = false;
        boolean oFoundCol2 = false;
        boolean xFoundCol3 = false;
        boolean oFoundCol3 = false;
        boolean col1 = false;
        boolean col2 = false;
        boolean col3 = false;
        boolean colTie = false;
        boolean xFoundDiag1 = false;
        boolean oFoundDiag1 = false;
        boolean xFoundDiag2 = false;
        boolean oFoundDiag2 = false;
        boolean diag1 = false;
        boolean diag2 = false;
        boolean diagTie = false;
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
