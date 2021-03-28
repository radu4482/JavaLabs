package org.example;

import java.util.Random;

public class RandomPlayer extends Player implements Runnable {

    public RandomPlayer(String name) {
        super(name);
    }

    public void choose() {

        Random random = new Random();
        int index =0;
        while(index<=0) {
            index = random.nextInt(this.board.tokens.size()+1);
            System.out.println(index);
        }
        player_tokens.add(this.board.tokens.get(index-1));
        System.out.println("S.a ales: "+index);
        this.board.removeToken(index);
    }

    public void Thread1() throws InterruptedException {
        synchronized (this) {
            while (board.game.GameIsOn)
                wait();
            System.out.println("Gata Player Random");
        }
    }

    public void Thread2() throws InterruptedException {
        synchronized (this) {
            choose();
            notify();
        }
    }


    public synchronized void run() {
        while (board.game.GameIsOn) {
            try {
                Thread1();
            } catch (InterruptedException e) {
                System.out.print("Eroare ");
            }
        }
    }
}
