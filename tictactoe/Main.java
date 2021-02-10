package tictactoe;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean gameRunning = true;
        while(gameRunning)
        gameRunning = startCommand(getCommand());
    }

    private static String[] getCommand() {
        String[] array;
        while(true) {
            System.out.print("Commands: start, exit. ");
            System.out.print("Input command: ");
            array = new Scanner(System.in).nextLine().toLowerCase().split(" ");
            if(array[0].equals("exit"))
                break;
            if(allValidPlayers(array, array.length - 1)) {
                break;
            } else System.out.println("bad parameters!");
        }
        return array;
    }

    private static boolean startCommand(String[] command) {
        switch (command[0]) {
            case "start":
                new tictactoe.Game(choosePlayers(command[1]),choosePlayers(command[2])).start();
                return true;
            case "exit":
            default: return false;
        }
    }

    private static boolean allValidPlayers(String[] args, int i) {
        if (args.length != 3 || !args[0].equals("start"))
            return false;
        switch (args[i - 1]) {
            case "user":
            case "easy":
            case "medium":
            case "hard":
                if (args.length == i) return true;
                else return allValidPlayers(args, ++i);
            default: return false;
        }
    }

    private static tictactoe.Player choosePlayers(String args) {

        switch(args){
            case "user": return new tictactoe.User();
            case "easy": return new tictactoe.EasyAI();
            case "medium": return new tictactoe.MediumAI();
            case "hard": return new tictactoe.HardAI();
            default:
                System.out.println("something went wrong");
                return new tictactoe.EasyAI();
        }
    }
}