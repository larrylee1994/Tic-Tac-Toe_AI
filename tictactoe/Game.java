package tictactoe;

import java.util.Arrays;
import java.util.HashMap;

public class Game {
    private static String gameDone;
    private static int coordinate;
    private static int movesMade;
    private static final int[] winCondition = new int[8];
    private static final int[] xWinCondition = new int[8];
    private static final int[] availableMoves = new int[9];
    private static char[] gameField;
    private final Player player1;
    private final Player player2;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        Arrays.fill(availableMoves, 10);
        Arrays.fill(winCondition, 0);
        gameField = ("         ").toCharArray();
        gameDone = "N";
        movesMade = 0;
    }

    public void start() {

        displayField();
        while (gameDone.equals("N")) {

            player1.Move();
            showMove();

            if (!gameDone.equals("N")) {
                break;
            }

            player2.Move();
            showMove();
        }
    }

    private void showMove() {
        if(coordinate != 99)
        updateField();
        displayField();
        if(coordinate != 99)
        gameDone = checkGameState(gameField);
        if (gameDone.equals("X")) System.out.println("X wins");
        if (gameDone.equals("O")) System.out.println("O wins");
        if (gameDone.equals("T")) System.out.println("Draw");
    }

    public static HashMap<Integer, Integer> getFieldKeys() {
        HashMap<Integer, Integer> table = new HashMap<>();
        table.put(13, 0);
        table.put(23, 1);
        table.put(33, 2);
        table.put(12, 3);
        table.put(22, 4);
        table.put(32, 5);
        table.put(11, 6);
        table.put(21, 7);
        table.put(31, 8);
        table.put(99, 9);
        return table;
    }

    private static void displayField() {
        System.out.print(
            "---------\n"+
            "| "+ Game.gameField[0] + " " + Game.gameField[1] + " " + Game.gameField[2] + " |\n" +
            "| "+ Game.gameField[3] + " " + Game.gameField[4] + " " + Game.gameField[5] + " |\n" +
            "| "+ Game.gameField[6] + " " + Game.gameField[7] + " " + Game.gameField[8] + " |\n" +
            "---------\n"
        );
    }

    public static String checkGameState(char[] gameField) {

        if (gameWonBy(gameField, 'X')) {
            Game.gameDone = "X";
            return "X";
        }
        System.arraycopy(winCondition, 0, xWinCondition, 0, winCondition.length);

        if (gameWonBy(gameField, 'O')) {
            Game.gameDone = "O";
            return "O";
        }

        if (isDraw()) {
            Game.gameDone = "T";
            return "T";
        }

       return "N";
    }

    public static boolean gameWonBy(char[] gameField, char c) {
        int count = 0;
        Arrays.fill(winCondition, 0);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameField[count] == c){
                    checkWinConditions(i, j);
                }
                for (var element : winCondition) {
                    if (element == 3) {
                        return true;
                    }
                }
                count++;
            }
        }
        return false;
    }

    private static void checkWinConditions(int i, int j) {
        if(i == j)
            winCondition[0]++;// main diagonal
        if(i + j == 2)
            winCondition[1]++; // other diagonal
        if(i == 0)
            winCondition[2]++; // top row
        if(i == 1)
            winCondition[3]++; // middle row
        if(i == 2)
            winCondition[4]++; // bottom row
        if(j == 0)
            winCondition[5]++; // first col
        if(j == 1)
            winCondition[6]++; // second col
        if(j == 2)
            winCondition[7]++; // third col
    }

    private static boolean isDraw() {
        var count = 0;
        for (var element : Game.gameField) {
            if (element == 'X' || element == 'O') {
                count++;
            }
        }
        return count == 9;
    }

    private static void updateField() {
        if (!isAvailable(coordinate)) {
            coordinate = EasyAI.getRandom();
        }
        Game.gameField[Game.coordinate] = whosTurn() ? 'X' : 'O';
        availableMoves[movesMade++] = coordinate;
    }

    public static boolean whosTurn() {
        var xCount = 0;
        var oCount = 0;

        for (var element : Game.gameField) {
            switch (element) {
                case 'X':
                    xCount++;
                    break;
                case 'O':
                    oCount++;
                    break;
            }
        }
        return xCount == oCount;
    }


    public static boolean isAvailable(int number) {
        if (number == -1) return false;

        for (var element : availableMoves) {
            if (number == element) {
                return false;
            }
        }

        return number <= 8 && number >= 0;
    }

    public static int[] getAvailableMoves() {
        return availableMoves;
    }

    public static char[] getGameField() {
        return gameField;
    }

    public static void setCoordinate(int coordinate) {
        Game.coordinate = coordinate;
    }

    public static int[] getWinCondition() {
        return winCondition;
    }

    public static int[] getxWinCondition() {
        return xWinCondition;
    }

    public static void setGameField(char[] gameField) {
        Game.gameField = gameField;
    }

    public static void setGameDone(String gameDone) {
        Game.gameDone = gameDone;
    }
}

