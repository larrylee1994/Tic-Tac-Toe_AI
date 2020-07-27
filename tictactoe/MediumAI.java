package tictactoe;

public class MediumAI implements Player{

    static int[] winCondition = new int[8];

    @Override
    public void Move() {
        System.out.println("Making move level \"medium\"");
        getMove();
    }

    public void getMove() {
        tryToWin();
        if (moveFound()) return;
        tryToDefend();
        if (moveFound()) return;
    }

    static boolean moveFound() {
        int count = 0;
        for (var element : winCondition) {
            if (element == 2) {
                int test = findMove(count);
                if(Game.isAvailable(test)) {
                    Game.setCoordinate(test);
                    return true;
                } else {
                    count++;
                    continue;
                }
            }
            count++;
        }
        return false;
    }


    public static void tryToDefend() {
        winCondition = Game.whosTurn() ? Game.getWinCondition() : Game.getxWinCondition();
    }

    public static void tryToWin() {
        winCondition = Game.whosTurn() ? Game.getxWinCondition() : Game.getWinCondition();
    }

    private static int checkFields(int start, int incr) {
        var array = Game.getGameField();
        for (int i = start,j = 0; j < 3; i+= incr,j++) {
            if(array[i] == ' ') return i;
        }
        return -1;
    }

    private static int findMove(int count) {
        switch (count){
            case 0: return checkFields(0,4);
            case 1: return checkFields(2,2);
            case 2: return checkFields(0,1);
            case 3: return checkFields(3,1);
            case 4: return checkFields(6,1);
            case 5: return checkFields(0,3);
            case 6: return checkFields(1,3);
            case 7: return checkFields(2,3);
        }
        return 0;
    }
}

/*
* [0] [1] [2]
* [3] [4] [5]
* [6] [7] [8]
* */

//if(i == j) tryToDefend[0]++;     // main diagonal  0-4-8
//if(i + j == 2) tryToDefend[1]++; // other diagonal 2-4-6
//if(i == 0) tryToDefend[2]++;     // top row        0-1-2
//if(i == 1) tryToDefend[3]++;     // middle row     3-4-5
//if(i == 2) tryToDefend[4]++;     // bottom row     6-7-8
//if(j == 0) tryToDefend[5]++;     // first col      0-3-6
//if(j == 1) tryToDefend[6]++;     // second col     1-4-7
//if(j == 2) tryToDefend[7]++;     // third col      2-5-8