package storm.blueprints.BoardGame;

public class BestMove {
    public Board bestMove;
    public Integer score = Integer.MIN_VALUE;

    public String toString() {
        return bestMove.toString() + "[" + score + "]";
    }
}
