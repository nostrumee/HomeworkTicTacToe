import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HumanPlayer extends Player {
    public HumanPlayer(char symbol) {
        super(symbol);
    }

    @Override
    public Move makeMove() throws IOException {
        String input1 = null;
        String input2 = null;
        String errorMessage = "Error, please enter a correct value";
        int x;
        int y;

        while (true) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Player (" + this.getSymbol() + "), it's your turn, please enter row (0..2)");
            input1 = br.readLine();
            System.out.println("Please enter column");
            input2 = br.readLine();

            try {
                x = Integer.parseInt(input1);
                y = Integer.parseInt(input2);
                break;
            } catch (NumberFormatException nfe) {
                System.out.println(errorMessage);
            }
        }
        return new Move(x, y);
    }
}