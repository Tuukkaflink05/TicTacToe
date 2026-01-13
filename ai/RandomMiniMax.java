package ai;
import utils.*;

public class RandomMiniMax {

    /**
     * method to calculate the score of a move.
     * using the minimax algorithm
     *
     * @param board board object
     * @param playerXArr player x moves in an array
     * @param playerOArr player o move array
     * @param legalMovesArr legal moves array
     * @param isPlayerx boolean is the turn of player x
     * @param charArr board represented in a char array
     * @param depth current depth of the search
     * @return returns a score if the game is in terminal state (win,lose,draw)
     */

    public static double miniMaxScore(Board board, int[][] playerXArr,
                                    int[][] playerOArr, int[][] legalMovesArr,
                                    boolean isPlayerx, char[][] charArr, double alpha, double beta, double depth) {


        //check the winner
        char winner = WinChecker.check(charArr, board);

        if (winner == 'x') {
            return 10 -depth;

        } else if (winner == 'o') {
            return depth - 10;

        } else if (legalMovesArr.length == 0) {
            return 0;
        }



        if(isPlayerx) {
            //initialize the bestscore to min value so we always get better
            double bestScore = Integer.MIN_VALUE;

            //loop through all moves
            for (int i = 0; i < legalMovesArr.length; i++) {
                //copy things so we dont fuck anything up
                //takes time but easier
                int[][] playerXMovesCopy = Array.copyArray(playerXArr);
                int[][] playerOMovesCopy = Array.copyArray(playerOArr);
                char[][] boardArrCopy = Array.copyCharArray(charArr);


                int[] move = new int[2];
                move[0] = legalMovesArr[i][0];
                move[1] = legalMovesArr[i][1];

                boardArrCopy[move[1]][move[0]] = 'x';

                playerXMovesCopy = Array.addMove(move, playerXMovesCopy);
                int[][] newLegalMoves = Array.removeMove(move, legalMovesArr);

                //recursivly call the method to get the current move score
                double score = miniMaxScore(board, playerXMovesCopy,
                                            playerOMovesCopy,
                                            newLegalMoves, false,
                                            boardArrCopy, alpha, beta,
                                            depth + 0.1);


                //alphabeta pruning
                bestScore = Math.max(bestScore, score);
                alpha = Math.max(alpha, bestScore);

                if (beta <= alpha) {
                    break;
                }
            }

            return bestScore;

        } else {
            double bestScore = Integer.MAX_VALUE;

            for (int i = 0; i < legalMovesArr.length; i++) {
                int[][] playerXMovesCopy = Array.copyArray(playerXArr);
                int[][] playerOMovesCopy = Array.copyArray(playerOArr);
                char[][] boardArrCopy = Array.copyCharArray(charArr);

                int[] move = new int[2];
                move[0] = legalMovesArr[i][0];
                move[1] = legalMovesArr[i][1];

                boardArrCopy[move[1]][move[0]] = 'o';

                playerOMovesCopy = Array.addMove(move, playerOMovesCopy);
                int[][] newLegalMoves = Array.removeMove(move, legalMovesArr);

                double score = miniMaxScore(board, playerXMovesCopy, playerOMovesCopy, newLegalMoves, true, boardArrCopy, alpha, beta, depth + 0.1);

                bestScore = Math.min(bestScore, score);
                beta = Math.min(beta, bestScore);

                if (beta <= alpha) {
                    break;
                }
            }
            return bestScore;
        }
    }
}
