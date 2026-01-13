package utils;
/**
 * tictactoe board object
 * This class maintains the state of the board cells using a 2D character array
 * and stores the configuration for the board size and the winning condition.
 */
public class Board {
    /**
     * 2d array representing the current state of the board
     *
     */
    public char[][] boardArr;

    /**
     * number consecutive symbols required to trigger a win
     *
     */
    public int consecutive;

    /**
     * the size of the sqaure board
     */
    public int size;

    /**
     *
     * @param size the size of the board > 1
     * @param consecutive the number of consecutive symbols needed to win
     */
    public Board(final int size, final int consecutive) {
        this.size = size;
        this.consecutive = consecutive;

        //initialize the board array with the defined size
        this.boardArr = new char[size][size];

        //iterate through the board
        for (int i = 0; i < this.boardArr.length; i++) {
            for (int j = 0; j < this.boardArr[i].length; j++) {
                // add _ to every spot to indicate its empty
                this.boardArr[i][j] = '_';
            }
        }
    }
}
