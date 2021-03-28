package org.example;

import java.util.Scanner;

public class SmartPlayer extends Player implements Runnable {
    public SmartPlayer(String name) {
        super(name);
    }

    public void choose() {
    }

    public void Thread1() throws InterruptedException {
        synchronized (this) {
            while (board.game.GameIsOn)
                this.wait();
        }
    }

    public void Thread2() throws InterruptedException {
        synchronized (this) {
            System.out.print("Enter any number: ");
            choose();
        }
        this.notify();
    }


    public synchronized void run() {
        while (board.game.GameIsOn) {
            try {
                wait();
                choose();
            } catch (Exception e) {
            }
        }
    }
}
