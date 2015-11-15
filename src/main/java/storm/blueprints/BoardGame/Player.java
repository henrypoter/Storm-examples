package storm.blueprints.BoardGame;

public class Player {
	public static String next(String current) {
        if (current.equals("X")) return "O";
        else return "X";
    }
}
