package tictactoe;

public class Minimax {

    char[] board;

    Minimax () {
        this.board = Game.getGameField().clone();
    }

    public int bestMove () {
        var bestScore = Integer.MIN_VALUE;
        int score = 0;
        int move = -1;
        for (int i = 0; i < 9; i++) {
            if(board[i] == ' ') {
                board[i] = 'X';
                score = minimax(board, 0, false);
                board[i] = ' ';
                if (score > bestScore) {
                    bestScore = score;
                    move = i;
                }
            }
        }
        return move;
    }

    private enum  score {
        X(10), O(-10), T(0);
        int score;
        score(int score) {
            this.score = score;
        }
    }

    public int minimax (char[] board, int depth, boolean isMaximizing) {
        var result = Game.checkGameState(board);
        if (result.equals("X")) {
            return 10;
        }
        if (result.equals("O")) {
            return -10;
        }
        if (result.equals("T")) {
            return 0;
        }

        if (isMaximizing) {
            var bestScore = Integer.MIN_VALUE;
            int score = 0;
            for (int i = 0; i < 9; i++) {
                if (board[i] == ' ') {
                    board[i] = 'X';
                    score += minimax(board, depth++, false);
                    board[i] = ' ';
                    bestScore = Math.max(score, bestScore);
                }
            }
            return bestScore;
        } else {
            var bestScore = Integer.MAX_VALUE;
            int score = 0;
            for (int i = 0; i < 9; i++) {
                if(board[i] == ' ') {
                    board[i] = 'O';
                    score += minimax(board, depth++, true);
                    board[i] = ' ';
                    bestScore = Math.min(score, bestScore);
                }
            }
            return bestScore;
        }
    }
}


