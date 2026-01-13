package utils;
//import utils.Board;

/**
 * utility class handling the win conditions and checking
 * for my tictactoe game
 */
public class WinChecker {


    /**
     * method for handling win checking
     * for a tictactoe game
     * @param boardArr the board represented by a 2d array
     * @param board board object
     * @return char of the winning player, '_' for no winner
     */
    public static char check(final char[][] boardArr, final Board board) {


        // loop through the board array
            // check columns first
        for (int i = 0; i < boardArr.length; i++) {

            //count how many charaters are in a row
            int xInlineHori = 0;
            int oInlineHori = 0;

            for (int j = 0; j < boardArr[i].length; j++) {

                // check if current position is a 'x'
                if (boardArr[i][j] == 'x') {

                    //put one "point" player 'x'
                    xInlineHori ++;

                    // reset player 'o' to 0
                    oInlineHori = 0;

                    // if player has enough "points" as is the consecutive in nedeed for a win
                    if (xInlineHori == board.consecutive) {
                        return 'x';
                    }

                    // same checks as above for player 'o'
                } else if (boardArr[i][j] == 'o') {
                    xInlineHori = 0;
                    oInlineHori ++;

                    if (oInlineHori == board.consecutive) {
                        return 'o';
                    }

                }


                //going to the next column reset "points" to 0
                else {
                    xInlineHori = 0;
                    oInlineHori = 0;
                }
            }
        }

        // loop through the rows next
            //same as above
        for (int j = 0; j < boardArr[0].length; j++) {

            int xInlineVert = 0;
            int oInlineVert = 0;

            for (int i = 0; i < boardArr.length; i++) {

                if (boardArr[i][j] == 'x') {
                    xInlineVert++;
                    oInlineVert = 0;

                    if (xInlineVert == board.consecutive) {
                        return 'x';
                    }
                }

                else if (boardArr[i][j] == 'o') {
                    xInlineVert = 0;
                    oInlineVert++;

                    if (oInlineVert == board.consecutive) {
                        return 'o';
                    }

                } else {
                    xInlineVert = 0;
                    oInlineVert = 0;
                }
            }
        }

        // loop through the  diagonals starting from the top row (going Down-Right)
        for (int k = 0; k < boardArr.length; k++) {
            int xInlineDiag = 0;
            int oInlineDiag = 0;

            // Start at (0, k) and move down-right
            for (int i = 0, j = k; i < boardArr.length && j < boardArr.length; i++, j++) {

                if (boardArr[i][j] == 'x') {
                    xInlineDiag++;
                    oInlineDiag = 0;
                    if (xInlineDiag == board.consecutive) {
                        return 'x';
                    }

                } else if (boardArr[i][j] == 'o') {
                    xInlineDiag = 0;
                    oInlineDiag++;
                    if (oInlineDiag == board.consecutive) {
                        return 'o';
                    }

                } else {
                    xInlineDiag = 0;
                    oInlineDiag = 0;
                }
            }
        }


        for (int k = 1; k < boardArr.length; k++) {
            int xInlineDiag = 0;
            int oInlineDiag = 0;

            for (int i = k, j = 0; i < boardArr.length && j < boardArr.length; i++, j++) {

                if (boardArr[i][j] == 'x') {
                    xInlineDiag++;
                    oInlineDiag = 0;

                    if (xInlineDiag == board.consecutive) {
                        return 'x';
                    }

                } else if (boardArr[i][j] == 'o') {
                    xInlineDiag = 0;
                    oInlineDiag++;
                    if (oInlineDiag == board.consecutive) {
                        return 'o';
                    }

                } else {
                    xInlineDiag = 0;
                    oInlineDiag = 0;
                }
            }
        }



        for (int k = 0; k < boardArr.length; k++) {
            int xInlineDiag = 0;
            int oInlineDiag = 0;


            for (int i = 0, j = k; i < boardArr.length && j >= 0; i++, j--) {

                if (boardArr[i][j] == 'x') {
                    xInlineDiag++;
                    oInlineDiag = 0;

                    if (xInlineDiag == board.consecutive) {
                        return 'x';
                    }

                } else if (boardArr[i][j] == 'o') {
                    xInlineDiag = 0;
                    oInlineDiag++;

                    if (oInlineDiag == board.consecutive) {
                        return 'o';
                    }

                } else {
                    xInlineDiag = 0;
                    oInlineDiag = 0;
                }
            }
        }


        for (int k = 1; k < boardArr.length; k++) {
            int xInlineDiag = 0;
            int oInlineDiag = 0;


            for (int i = k, j = boardArr.length - 1; i < boardArr.length && j >= 0; i++, j--) {

                if (boardArr[i][j] == 'x') {
                    xInlineDiag++;
                    oInlineDiag = 0;

                    if (xInlineDiag == board.consecutive) {
                        return 'x';
                    }

                } else if (boardArr[i][j] == 'o') {
                    xInlineDiag = 0;
                    oInlineDiag++;

                    if (oInlineDiag == board.consecutive) {
                        return 'o';
                    }

                } else {
                    xInlineDiag = 0;
                    oInlineDiag = 0;
                }
            }
        }

        return '_';

    }

/*
    [[x,x,x]]
    [[o,_,x]]
    [[_,o,x]]

    [[x,o,o,o]]
    [[o,_,x,o]]
    [[_,o,x,_]]
    [[_,o,x,x]]
*/

}
