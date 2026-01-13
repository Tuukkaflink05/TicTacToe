package utils;
public class LegalMoves {
    public int[][] legalMovesArr;


    public LegalMoves(final char[][] board) {
        this.legalMovesArr = new int[board.length * board.length][2];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int index = i * board.length + j;

                legalMovesArr[index][0] = i;
                legalMovesArr[index][1] = j;
            }
        }
    }




    public void removeMove(final int[] move) {

        int[][] updatedMoves = new int[this.legalMovesArr.length - 1][2];
        int j = 0;

        for (int i = 0; i < updatedMoves.length; i++) {

            if (this.legalMovesArr[j][0] == move[0]
                    && this.legalMovesArr[j][1] == move[1]) {
                j++;
            }

            updatedMoves[i][0] = this.legalMovesArr[j][0];
            updatedMoves[i][1] = this.legalMovesArr[j][1];
            j++;
        }

        this.legalMovesArr = updatedMoves;
    }
}
