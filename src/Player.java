import java.io.IOException;

public abstract class Player {
    static class Move {
        private int x;
        private int y;
        private char symbol;

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public Move(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private final char symbol;

    public Player(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public abstract Move makeMove() throws IOException;
}
