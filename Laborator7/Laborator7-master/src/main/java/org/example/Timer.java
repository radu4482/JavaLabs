package org.example;

public class Timer extends Thread {
    Board board;

    public Timer(Board board){
        this.board=board;
    }

    public void run(){
        try {
            sleep((int) (Math.random() * 1000000));
            System.out.println("Time is over");
            board.tokens.clear();
            board.GameFinished();
            System.exit(0);

        }catch (InterruptedException e) {
                System.err.println(e);
                }
    }
}
