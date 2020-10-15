import java.util.Random;

public class ComputerPlayer extends Player {

    public ComputerPlayer(char symbol) {
        super(symbol);
    }

    @Override
    public Move makeMove() {
        Random random = new Random(System.currentTimeMillis());
        int x = random.nextInt(3);
        int y = random.nextInt(3);

        return new Move(x, y);
    }
}
