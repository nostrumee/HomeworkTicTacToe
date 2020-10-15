import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {
    private static int typeOfGame;
    private static char[][] board;
    private static int movesCount;

    public static void start() throws IOException {
        while (true) {
            chooseType();
            initializeBoard();
            if (typeOfGame == 1) {
                runSingleplayer();
            } else {
                runMultiplayer();
            }

            if (!restart()) {
                System.out.println("Thanks for playing my game");
                return;
            }
        }
    }

    private static boolean restart() throws IOException {
        String input = null;
        String errorMessage = "Error, please enter a correct value";

        System.out.println("Wanna try once more? Y or N");

        while (true) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            input = br.readLine();
            if (input.equals("Y")) {
                return true;
            } else if (input.equals("N")) {
                return false;
            } else {
                System.out.println(errorMessage);
            }
        }

    }

    private static void initializeBoard() {
        board = new char[][]{{'_', '_', '_'}, {'_', '_', '_'}, {'_', '_', '_'}};
        movesCount = 0;
    }

    private static void chooseType() throws IOException {
        String input = null;
        String errorMessage = "Error, please enter a correct value";

        System.out.println("Choose a type of the game - (1) singleplayer OR (2) multiplayer");

        while (true) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                input = br.readLine();

                if (validateType(input)) {
                    typeOfGame = Integer.parseInt(input);
                    return;
                } else {
                    System.out.println(errorMessage);
                }
            }
    }

    private static boolean validateType(String input) {
        int type;
        try {
            type = Integer.parseInt(input);
        } catch (NumberFormatException nfe) {
            return false;
        }

        return type == 1 || type == 2;
    }

    private static void drawBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("~~~~~~~~~~~~~~~~");
    }

    private static void runSingleplayer() throws IOException {
        drawBoard();
        Player humanPlayer = new HumanPlayer('X');
        Player computerPlayer = new ComputerPlayer('0');

        while (true) {
            while (true) {
                Player.Move move = humanPlayer.makeMove();
                if (validateMove(move)) {
                    updateBoard(move, humanPlayer);
                    if (checkTheWinner(humanPlayer.getSymbol())) {
                        System.out.println("Congrats, you win!");
                        return;
                    }
                    break;
                } else {
                    System.out.println("You can't make a move here");
                }
            }

            if (movesCount == 9) {
                System.out.println("Tie!");
                return;
            }

            while (true) {
                Player.Move move = computerPlayer.makeMove();
                if (validateMove(move)) {
                    updateBoard(move, computerPlayer);
                    if (checkTheWinner(computerPlayer.getSymbol())) {
                        System.out.println("Computer win:(");
                        return;
                    }
                    break;
                }
            }
        }

    }

    private static void updateBoard(Player.Move move, Player player) {
        board[move.getX()][move.getY()] = player.getSymbol();
        drawBoard();
        movesCount++;
    }

    private static boolean checkTheWinner(char symbol) {
        return checkHorizontalWinner(symbol) || checkVerticalWinner(symbol) || checkDiagonalWinner(symbol);
    }

    private static boolean checkDiagonalWinner(char symbol) {
        return board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == symbol
                || board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] == symbol;
    }

    private static boolean checkVerticalWinner(char symbol) {
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == board[1][j] && board[0][j] == board[2][j] && board[0][j] == symbol) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkHorizontalWinner(char symbol) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == symbol) {
                return true;
            }
        }
        return false;
    }

    private static void runMultiplayer() throws IOException {
        drawBoard();
        Player player1 = new HumanPlayer('X');
        Player player2 = new HumanPlayer('0');

        while (true) {
            while (true) {
                Player.Move move = player1.makeMove();
                if (validateMove(move)) {
                    updateBoard(move, player1);
                    if (checkTheWinner(player1.getSymbol())) {
                        System.out.println("Player (X) win");
                        return;
                    }
                    break;
                } else {
                    System.out.println("You can't make a move here");
                }
            }

            if (movesCount == 9) {
                System.out.println("Tie!");
                return;
            }

            while (true) {
                Player.Move move = player2.makeMove();
                if (validateMove(move)) {
                    updateBoard(move, player2);
                    if (checkTheWinner(player2.getSymbol())) {
                        System.out.println("Player (0) win");
                        return;
                    }
                    break;
                } else {
                    System.out.println("You can't make a move here");
                }
            }
        }


    }

    private static boolean validateMove(Player.Move move) {
        int x = move.getX();
        int y = move.getY();

        return x >= 0 && x < 3 && y >= 0 && y < 3 && board[x][y] == '_';
    }
}