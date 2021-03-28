package org.example;

public class Game extends Thread {
    public Board board;
    int num_players;
    boolean GameIsOn = true;

    public Game(int m, int n) {
        board = new Board(64, 20, this);
        ManualPlayer manplay = new ManualPlayer("ManualPlayer");
        RandomPlayer randplay = new RandomPlayer("RandomPlayer");
        board.addPlayer(manplay);
        board.addPlayer(randplay);
    }

    public void run() {
        try {
            for (Player p : board.players) {
                new Thread(p).start();
            }

            new Timer(this.board).start();
            while (GameIsOn) {
                for (Tokenn t : board.tokens)
                    System.out.print(t.getValue() + "  ");
                System.out.println("");

                for (Player p : board.players) {
                    System.out.println("player:" + p.name + " " + GameIsOn);
                    p.Thread2();
                }
                System.out.println("Gata bool");
                GameIsOn = !board.GameFinished();
            }
        } catch (Exception e) {

        }

    }
}


