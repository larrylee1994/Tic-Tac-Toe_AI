package tictactoe;

import java.util.Random;

public class EasyAI implements Player{

    @Override
    public void Move() {
        System.out.println("Making move level \"easy\"");
    }

    public static int getRandom() {
        int number;
        while(true) {
            number = new Random().nextInt(9);
            boolean isAvailable = true;
            for (var element : Game.getAvailableMoves()) {
                if (number == element) {
                    isAvailable = false;
                    break;
                }
            }
            if(isAvailable)
                break;
        }
        return number;
    }
}

