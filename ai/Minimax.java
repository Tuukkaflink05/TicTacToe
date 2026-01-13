package ai;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import utils.Array;
import utils.Board;
import utils.WinChecker;

/**
 * method for the minimax algorithm using caching.
 */
public class Minimax {

    // initialize a new hashmap (illegal to use(lol))
    public static HashMap<String, Double> boardHashMap = new HashMap<String, Double>();

    /**
     * method to calculate the score of a move.
     *
     * @param board board object
     * @param playerXArr player x moves in an array
     * @param playerOArr player o move array
     * @param legalMovesArr legal moves array
     * @param isPlayerx boolean is the turn of player x
     * @param charArr board represented in a char array
     * @param depth depth of the search
     * @return returns a score if the game is in terminal state (win,lose,draw)
     */
    public static double miniMaxScore(final Board board,
                                    final int[][] playerXArr,
                                    final int[][] playerOArr,
                                    final int[][] legalMovesArr,
                                    final boolean isPlayerx,
                                    final char[][] charArr,
                                    final double depth) {


        // create the board id to search from hashmap
        String boardId = Arrays.deepToString(charArr);



        // if score has been calculated before get the score from hashmap
        if (boardHashMap.containsKey(boardId)) {
            return boardHashMap.get(boardId);
        }

        //check for terminal state
        char winner = WinChecker.check(charArr, board);

        if (winner == 'x') {
            return 10 - depth;

        } else if (winner == 'o') {
            return depth - 10;

        } else if (legalMovesArr.length == 0) {
            return 0;
        }


        double bestScore;
        if (isPlayerx) {
            bestScore = Integer.MIN_VALUE;

            // iterate thgough all legal moves
            // and calculate a score
            for (int i = 0; i < legalMovesArr.length; i++) {

                // copy arrays to not heck anything up
                int[][] playerXMovesCopy = Array.copyArray(playerXArr);
                int[][] playerOMovesCopy = Array.copyArray(playerOArr);
                char[][] boardArrCopy = Array.copyCharArray(charArr);

                int[] move = new int[2];
                move[0] = legalMovesArr[i][0];
                move[1] = legalMovesArr[i][1];

                boardArrCopy[move[1]][move[0]] = 'x';



                playerXMovesCopy = Array.addMove(move, playerXMovesCopy);
                int[][] newLegalMoves = Array.removeMove(move, legalMovesArr);

                // recursivly call thyself with the new move added
                double score = miniMaxScore(board, playerXMovesCopy,
                                                playerOMovesCopy,
                                                newLegalMoves, false,
                                                boardArrCopy,depth + 0.1);


                bestScore = Math.max(bestScore, score);

            }

        } else {
            bestScore = Integer.MAX_VALUE;

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

                double score = miniMaxScore(board, playerXMovesCopy,
                                                playerOMovesCopy,
                                                newLegalMoves,
                                                true,
                                                boardArrCopy, depth + 0.1);


                bestScore = Math.min(bestScore, score);
            }
        }

        boardHashMap.put(boardId, bestScore);
        return bestScore;

    }

/**
 * method for saving the cache.
 * @param filename String file name to save the cache to.
 */
    public static void saveBrain(final String filename) {
        File file = new File(filename);

        BufferedWriter bf = null;

        try {

            bf = new BufferedWriter(new FileWriter(file));

            // iterate map entries
            for (Map.Entry<String, Double> entry
                                : boardHashMap.entrySet()) {

                // put key and value separated by a colon
                bf.write(entry.getKey() + ":"
                         + entry.getValue());

                // new line
                bf.newLine();
            }

            bf.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {

            try {

                // always close the writer
                bf.close();
            }
            catch (Exception e) {
            }
        }
    }

    /**
     * method for loading the cache if it exists
     * if not make a new one.
     * @param filename name of the cache file
     */
    public static void loadBrain(final String filename) {


        File file = new File(filename);
        BufferedReader br = null;

        System.out.println("LOADING...");


        // check if there's already a file
        if (!file.exists()) {
            System.out.println("No save file found. Starting fresh.");
            boardHashMap = new HashMap<>();
            return;
        }

        try {

            // create BufferedReader object from the File
            br = new BufferedReader(new FileReader(file));

            String line = null;

            // read file line by line
            while ((line = br.readLine()) != null) {

                // split the line by :
                String[] parts = line.split(":");

                String boardId = parts[0].trim();
                Double bestScore = Double.parseDouble(parts[1].trim());

                boardHashMap.put(boardId, bestScore);
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {

            // Always close the BufferedReader
            if (br != null) {
                try {
                    br.close();
                }
                catch (Exception e) {
                }

            }
        }
    }
}
