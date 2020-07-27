package tictactoe;

import java.util.HashMap;
import java.util.Scanner;

public class User implements Player{

    @Override
    public void Move() {
        HashMap<Integer, Integer> table = Game.getFieldKeys();

        int coordinate = 0;
        boolean isNumber = true;

        while (true) {
            try {
                System.out.print("Enter the coordinates: ");
                coordinate = Integer.parseInt(new Scanner(System.in).nextLine().replace(" ", ""));
                if(is99(coordinate)) return;
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                isNumber = false;
            }
            if (isNumber && !table.containsKey(coordinate)) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else {
                if(isNumber && Game.getGameField()[table.get(coordinate)] != ' ') {
                    System.out.println("This cell is occupied! Choose another one!");
                } else if (isNumber) break;
            }
            isNumber = true;
        }

        Game.setCoordinate(table.get(coordinate));
    }

    private boolean is99(int coordinate) {
        if (coordinate == 99) {
            Game.setGameField("EASTEREGG".toCharArray());
            Game.setCoordinate(99);
            Game.setGameDone("T");
            return true;
        }
        return false;
    }
}
