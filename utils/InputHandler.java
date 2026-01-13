package utils;
import java.io.Console;

import players.Players;

/**
 * utility class to handle input from the user
 */
public class InputHandler {


    /**
     * asks the user to input a number that has to be bigger
     * than 1
     * validates that the input is actually a number
     * @return user inputted number that is bigger than 1
     */
    public static int inputSize() {
        Console c = System.console();
        //initialize size to 0
        int size = 0;

        while (true) {

            try {
                //get input from user
                System.out.println("enter the size of the board");
                size = Integer.parseInt(c.readLine());

                // validate that number is bigger than 1
                if (size < 2) {
                    // if not throw an exception
                    throw new ArithmeticException();
                }
            return size;

            // catch exception if the number is not bigger than 1
            } catch (ArithmeticException e) {
                System.err.println("board size has to be bigger than 1");
                //ask again
                continue;

                //catch any other invalid inputs
            } catch (NumberFormatException e) {
                System.err.println("invalid input! try again!");
                //ask again
                continue;
            }

        }

    }

    /**
     * prompts the user to input a number between
     * @param size integer number
     * @return
     */
    public static int inputConsecutive(int size) {
        Console c = System.console();


        int consecutive = 0;

        while (true) {

            try {
                //get input from the user
                System.out.println("enter number of consecutive X's or O's required to win");
                consecutive = Integer.parseInt(c.readLine());

                if (consecutive > size || consecutive < 1) {
                    throw new ArithmeticException();
                }
                return consecutive;
            } catch (ArithmeticException e) {
                System.err.println("number of consecutives cannot be bigger than the board of: "+ size
                                     + " or smaller than 1");
                continue;
            } catch (NumberFormatException e) {
                System.err.println("invalid input! try again!");
                continue;
            }
        }

    }

    public static int[] humanMakeMove(int[][] legalMovesArr, Players playerO, Board board) {

        Console c = System.console();
        boolean illegalMove = true;
        int[] moves = new int[2];
        int xCo = -1;
        int yCo = -1;

        while (illegalMove) {

            while (true) {

                try {
                    System.out.println("enter move x coordinate");
                    xCo = Integer.parseInt(c.readLine());

                    if (xCo >= board.boardArr.length || xCo < 0) {
                        throw new ArithmeticException();
                    }
                    break;

                } catch (ArithmeticException e) {
                    System.err.println("coordinate has to be inside the board");
                    continue;
                } catch (NumberFormatException e) {
                    System.err.println("invalid input! try again!");
                    continue;
                }
            }

            while (true) {

                try {
                    System.out.println("enter move y coordinate");
                    yCo = Integer.parseInt(c.readLine());

                    if (yCo >= board.boardArr[0].length || yCo < 0) {
                        throw new ArithmeticException();
                    }

                    break;

                } catch (ArithmeticException e) {
                    System.err.println("coordinate has to be inside the board");
                    continue;
                } catch (NumberFormatException e) {
                    System.err.println("invalid input! try again!");
                    continue;
                }
            }

            for (int i = 0; i < legalMovesArr.length; i++) {

                if (legalMovesArr[i][0] == xCo
                        && legalMovesArr[i][1] == yCo) {

                    moves[0] = xCo;
                    moves[1] = yCo;
                    illegalMove = false;

                    return moves;
                }

            }
            if (illegalMove) {
                System.out.println("position used, try another postition");
            }
        }
        return moves;
    }

/**
 * Prompts the user to answer a yes or no question.
 * <p>
 * Continues prompting until the user enters either "yes" or "no".
 * Returns {@code true} if the answer is "yes".
 * </p>
 *
 * @return {@code true} if the user's answer is "yes"; otherwise {@code false}
 */

    public static boolean yesno() {
        Console c = System.console();

        System.out.println("yes / no");
        String input = c.readLine();

        //if input does not equal yes or no ask again until it does
        while (!(input.equals("yes") || input.equals("no"))) {
            System.out.println("input was not 'yes' or 'no' try again");
            input = c.readLine();
        }
        //if input equals yes set want info to true and pass it on
        return input.equals("yes");
    }



}
