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

        do
        {
            clearBoard();
            moveCount = 0;
            player = "X";

            display();

            do
            {
                rowMove = SafeInput.getRangedInt(in, "Enter the row coordinate of your move", 1, 3);
                colMove = SafeInput.getRangedInt(in, "Enter the column coordinate of your move", 1, 3);

                rowMove = rowMove - 1;
                colMove = colMove - 1;

                isValidMove = isValidMove(rowMove, colMove);
            }while(!isValidMove);

            board[rowMove][colMove] = player;
            moveCount++;

            if(moveCount >= 5) {

            }

        }while();
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
        }
    }

    private static boolean isValidMove(int row, int col)
    {
        boolean isValidMove = false;

        do {
            if (board[row][col].equals(" ")) {
                isValidMove = true;
            } else {
                System.out.println("You can only enter values in empty spaces. You tried to enter a value at index [" + row + ", " + col + "], which is full. Your move is invalid.");
            }
        }while(!isValidMove);

        return isValidMove;
    }

}
