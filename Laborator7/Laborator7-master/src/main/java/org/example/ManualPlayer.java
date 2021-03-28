package org.example;

import java.util.Scanner;

public class ManualPlayer extends Player implements Runnable {

    public ManualPlayer(String name) {
        super(name);
    }

    public boolean choose(int index) {
        if (this.board.getAmountTokens() < index) return false;

        player_tokens.add(board.tokens.get(index));
        this.board.removeToken(index);
        return true;
    }

    public void choose() {
    }

    public void Thread1() throws InterruptedException {
        synchronized (this) {
            while (board.game.GameIsOn)
                wait();
            System.out.println("Gata Player Manual");
        }
    }

    public void Thread2() throws InterruptedException {
        int index;
        int ok = 0;
        Scanner scan = new Scanner(System.in);

        synchronized (this) {
            do {
                System.out.print("Enter any number: ");
                index = scan.nextInt();
                if (index >= 1 && index <= board.max) ok = 1;
            } while (ok == 0);

            choose(index - 1);
            notify();
        }
    }


    public void run() {

        while (board.game.GameIsOn) {
            try {
                Thread1();
            } catch (InterruptedException e) {
                System.out.print("Eroare ");
            }
        }
    }
}
