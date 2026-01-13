package players;

public class Players {
    public int[][] position;

    public void addMove(int[] newPos) {

        int[][] tempPos = new int[this.position.length + 1][2];

            tempPos[0][0] = newPos[0];
            tempPos[0][1] = newPos[1];

            for (int i = 0; i < this.position.length; i++) {

                tempPos[i+1][0] = this.position[i][0];
                tempPos[i+1][1] = this.position[i][1];
            }
        this.position = tempPos;
    }

    public int[] makeRandomMove(int[][] legalMovesArr) {

        int[] moves = new int[2];

        int index = (int) (Math.random() * legalMovesArr.length);

        moves[0] = legalMovesArr[index][0];
        moves[1] = legalMovesArr[index][1];

        return moves;

    }
}
