
//imports
import ai.Minimax;
import players.PlayerO;
import players.PlayerX;
import utils.Array;
import utils.Board;
import utils.InputHandler;
import utils.LegalMoves;
import utils.WinChecker;

/**
 * A Class for the main game functions.
 */
class Game {

    /**
     * the game board and its objects.
     */
    private final Board board;

    /**
     * legal move array containg all the legal moves.
     */
    private LegalMoves legalMoves;

    /**
     *playerO object.
     */
    private PlayerO playerO;

    /**
     * playerX object.
     */
    private PlayerX playerX;


    /**
     * constructor method for initializing the game classes.
     * @param size the size of the game board
     */
    Game(final int size) {

        // initialize the board and ask the size
        this.board = new Board(size, InputHandler.inputConsecutive(size));
        this.playerO = new PlayerO();
        this.playerX = new PlayerX();
        //make the legal moves array
        this.legalMoves = new LegalMoves(board.boardArr);

        int boardsize = this.board.size;
        int boardcons = this.board.consecutive;
        String filename = "brain-" + boardsize
                            + "-consecutive-" + boardcons + ".txt";

        final int MAX_SIZE  = 4;
        // if board size is 4 load cached values from a file
        if (boardsize == MAX_SIZE) {
            Minimax.loadBrain(filename);
        }


    }

    /**
     * method for handling game turns wins and draws.
     */
    public void run() {

        boolean gameOver = false;
        // set the user to start the game
        boolean playerOTurn = true;
        //render the empty board
        render();

        while (!gameOver) {

            if (playerOTurn) {
                // call method for the player move
                humanMakeMove();
                // render board with the new player move
                render();

                //check for a winner
                if (WinChecker.check(board.boardArr, board) == 'o') {
                    System.out.println("Player O won the game");
                    gameOver = true;
                    break;
                }
                // set player turn to false
                playerOTurn = false;
            }

            // check for draw if there are no legal moves
            if (legalMoves.legalMovesArr.length == 0) {
                System.out.println("Draw");
                gameOver = true;
                break;
            }

            if (!playerOTurn) {
                computerMakeMove();
                render();

                if (WinChecker.check(board.boardArr, board) == 'x') {
                    System.out.println("Player X won the game");
                    gameOver = true;
                    break;
                }
                playerOTurn = true;
            }

            if (legalMoves.legalMovesArr.length == 0) {
                System.out.println("Draw");
                gameOver = true;
                break;
            }

        }

    }

    /**
     * method for handling the computer move.
     */
    public void computerMakeMove() {
        int[] computerMove = new int[2];

        final int SMALL_BOARD_SIZE = 3;
        final int LARGE_BOARD_SIZE = 4;
        final int MIN_MOVES_FOR_CACHE = 10;
        final int RANDOM_MOVE_THRESHOLD = 12;

        // if the board size is 3 bruteforce the best move
        if (board.size == SMALL_BOARD_SIZE) {
            computerMove = playerX.makeBestMovePrune(legalMoves.legalMovesArr,
                                                    playerO, this.board);

        // if the board size is 4 use the cache to get the best move
        } else if (board.size == LARGE_BOARD_SIZE
                    && legalMoves.legalMovesArr.length >= MIN_MOVES_FOR_CACHE) {

            computerMove = playerX.makeBestMoveCache(legalMoves.legalMovesArr,
                                                    playerO, this.board);

        // if there is more than 12 legal move just get a random move
        } else if (legalMoves.legalMovesArr.length > RANDOM_MOVE_THRESHOLD) {

            computerMove = playerX.makeRandomMove(legalMoves.legalMovesArr);

        } else {
            computerMove = playerX.makeBestMovePrune(legalMoves.legalMovesArr,
                                                    playerO, this.board);
        }

        //
        this.board.boardArr[computerMove[1]][computerMove[0]] = 'x';

        //remove chosen move from the legal moves array
        legalMoves.removeMove(computerMove);

        playerX.position = Array.addMove(computerMove, playerX.position);

    }

    /**
     * method for handling the player move.
     */
    public void humanMakeMove() {

        int[] humanMove = InputHandler.humanMakeMove(legalMoves.legalMovesArr,
                                                        playerO, this.board);


        this.board.boardArr[humanMove[1]][humanMove[0]] = 'o';

        legalMoves.removeMove(humanMove);
        playerO.position = Array.addMove(humanMove, playerO.position);

    }


    /**
     * method for rendering the board to the CLI.
     */
    public void render() {

        // print the borders and numbers of the board

        System.out.print("  ");
        for (int i = 0; i < board.boardArr[0].length; i++) {
            System.out.print(i + " ");
        }

        for (int i = 0; i < board.boardArr.length; i++) {
            System.out.println();
            System.out.print(i + "|");


            // loop through the board and check the player positions
            // if they match print the corresbonding character
            // otherwise print something else
            for (int j = 0; j < board.boardArr[i].length; j++) {

                boolean isPlayerO = false;
                boolean isPlayerX = false;
                int xCo;
                int yCo;

                //loop through the playerO positions and check if
                // match the it mathces current board coords
                for (int k = 0; k < playerO.position.length; k++) {

                    xCo = playerO.position[k][0];
                    yCo = playerO.position[k][1];

                    isPlayerO = xCo == j && yCo == i;

                    if (isPlayerO) {
                        break;
                    }
                }

                // same check as above
                for (int k = 0; k < playerX.position.length; k++) {

                    xCo = playerX.position[k][0];
                    yCo = playerX.position[k][1];

                    isPlayerX = xCo == j && yCo == i;

                    //if is player stop checking and break
                    if (isPlayerX) {
                        break;
                    }
                }

                // print the corresbonding char
                if (isPlayerO && !isPlayerX) {
                    System.out.print("O");
                }

                if (isPlayerX && !isPlayerO) {
                    System.out.print("X");
                }

                if (!isPlayerO && !isPlayerX) {
                    System.out.print("_");

                }

                System.out.print("|");

            }
        }
        System.out.println();
    }
    // voisi käyttää board char array mutta en jaksa muuttaa

}

/**
 * main class for the application.
 */
public class TicTacToeApp {

    /**
     * main method for starting the app.
     * @param args no use for the args
     */
    public static void main(final String[] args)  {

        // initialize play again boolean
        boolean playAgain = true;


        while (playAgain) {

            Game game = new Game(InputHandler.inputSize());

            game.run();


            // ask if the player likes to play again
            System.out.println("would you like to play again?");
            playAgain = InputHandler.yesno();

        }
    }
}
// legalmoves array voisi olla board objectissa