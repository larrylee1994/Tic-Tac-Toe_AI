package tictactoe;

public class HardAI implements Player {

//    static char player = Game.whosTurn() ? 'X' : 'O';
    @Override
    public void Move() {
        //TODO: make it work for O
        System.out.println("Making move level \"hard\"");
        MediumAI.tryToWin();
        if (MediumAI.moveFound()) return;
        MediumAI.tryToDefend();
        if (MediumAI.moveFound()) return;
        Game.setCoordinate(new Minimax().bestMove());
    }
}

