package players;

import utils.*;
import ai.*;

public class PlayerX  extends Players{

    public PlayerX() {
        this.position = new int[0][2];
    }



    public int[] makeBestMoveCache(int[][] legalMovesArr, Players playerO, Board board) {
        System.out.println("THINKING...");
        //int[][] playerXMovesCopy = Array.copyArray(this.position);
        //int[][] playerOMovesCopy = Array.copyArray(playerO.position);

        double bestScore = Integer.MIN_VALUE;
        int[] bestMove = {-1,-1};

        for (int i = 0; i < legalMovesArr.length; i++) {

            int[][] playerXMovesCopy = Array.copyArray(this.position);
            int[][] playerOMovesCopy = Array.copyArray(playerO.position);
            char[][] boardArrCopy = Array.copyCharArray(board.boardArr);
            double depth = 0;

            int[] move = new int[2];
            move[0] = legalMovesArr[i][0];
            move[1] = legalMovesArr[i][1];

            boardArrCopy[move[1]][move[0]] = 'x';


            int[][] newPlayerXMoves = Array.addMove(move, playerXMovesCopy);
            int[][] newLegalMoves = Array.removeMove(move, legalMovesArr);

            //int score = Minimax.miniMaxScore(board, newPlayerXMoves, playerOMovesCopy, newLegalMoves, false);
            double score = Minimax.miniMaxScore(board, newPlayerXMoves, playerOMovesCopy, newLegalMoves, false,boardArrCopy,depth);
            //System.out.println(score);



            if (score > bestScore) {
                bestScore = score;
                bestMove[0] = move[0];
                bestMove[1] = move[1];
            }
        }

        String filename = "brain-" + board.size
                            + "-consecutive-" + board.consecutive + ".txt";

        Minimax.saveBrain(filename);
        //Minimax.checkFileSize(filename);

        return bestMove;
    }

     public int[] makeBestMovePrune(int[][] legalMovesArr, Players playerO, Board board) {

        System.out.println("THINKING...");
        //int[][] playerXMovesCopy = Array.copyArray(this.position);
        //int[][] playerOMovesCopy = Array.copyArray(playerO.position);
        double MIN = Integer.MIN_VALUE;
        double MAX = Integer.MAX_VALUE;
        double bestScore = Integer.MIN_VALUE;
        int[] bestMove = {-1,-1};


        for (int i = 0; i < legalMovesArr.length; i++) {

            int[][] playerXMovesCopy = Array.copyArray(this.position);
            int[][] playerOMovesCopy = Array.copyArray(playerO.position);
            char[][] boardArrCopy = Array.copyCharArray(board.boardArr);
            double depth = 0;

            int[] move = new int[2];
            move[0] = legalMovesArr[i][0];
            move[1] = legalMovesArr[i][1];

            boardArrCopy[move[1]][move[0]] = 'x';

            int[][] newPlayerXMoves = Array.addMove(move, playerXMovesCopy);
            int[][] newLegalMoves = Array.removeMove(move, legalMovesArr);

            //int score = Minimax.miniMaxScore(board, newPlayerXMoves, playerOMovesCopy, newLegalMoves, false);
            double score = RandomMiniMax.miniMaxScore(board, newPlayerXMoves, playerOMovesCopy, newLegalMoves, false, boardArrCopy,MIN ,MAX, depth);
            //System.out.println(score);


            if (score > bestScore) {
                bestScore = score;
                bestMove[0] = move[0];
                bestMove[1] = move[1];
            }

            double progress = ( ((double)i / legalMovesArr.length) * 100);
            System.out.println((int) progress + "%");
        }

        return bestMove;
    }



}


