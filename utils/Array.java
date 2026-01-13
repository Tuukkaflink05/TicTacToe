package utils;

/**
 * utility class for array related helper methods.
 * all methods inside it are static.
 * most methods only work on 2d arrays sized
 * [x][2]
 * where x is larger than 0
 */
public class Array {

    /**
     * removes a specified move from passed
     * array containing moves
     * @param move array move to be deleted
     * @param oldArr 2dimenstional array from where to delete passed move
     * @return 2d array with the passed move deleted
     */
    public static int[][] removeMove(final int[] move, final int[][] oldArr) {

        // make a new array 1 shorter than the old one
        int[][] updatedMoves = new int[oldArr.length - 1][2];
        int j = 0;

        //loop through the new array
        for (int i = 0; i < updatedMoves.length; i++) {

            //skip the passed move if found
            if (oldArr[j][0] == move[0] && oldArr[j][1] == move[1]) {
                j++;
            }

            updatedMoves[i][0] = oldArr[j][0];
            updatedMoves[i][1] = oldArr[j][1];
            j++;
        }

        return updatedMoves;
    }

    /**
     * adds a new move to an array of moves.
     * @param move new move to be added to the array
     * @param oldArr array to add the move into
     * @return array with the new move added
     */
    public static int[][] addMove(final int[] move, final int[][] oldArr) {

        int[][] updatedMoves = new int[oldArr.length + 1][2];

        updatedMoves[0][0] = move[0];
        updatedMoves[0][1] = move[1];

        for (int i = 0; i < oldArr.length; i++) {

            updatedMoves[i + 1][0] = oldArr[i][0];
            updatedMoves[i + 1][1] = oldArr[i][1];
        }
        return updatedMoves;
    }

    /**
     * deep copies the passed array
     * @param oldArr array to be copied
     * @return the copied array
     */
    public static int[][] copyArray(final int[][] oldArr) {

        int[][] copiedArr = new int[oldArr.length][2];

        for (int i = 0; i < copiedArr.length; i++) {

            copiedArr[i][0] = oldArr[i][0];
            copiedArr[i][1] = oldArr[i][1];
        }
        return copiedArr;
    }

        /**
         * deep copies any size 2 dimensional array
         * @param oldArr array to be copied
         * @return deep copied array
         */
        public static char[][] copyCharArray(final char[][] oldArr) {

        char[][] copiedArr = new char[oldArr.length][oldArr[0].length];

        for (int i = 0; i < copiedArr.length; i++) {
            for (int j = 0; j < copiedArr[i].length; j++) {

                copiedArr[i][j] = oldArr[i][j];
            }

        }
        return copiedArr;
    }

}
