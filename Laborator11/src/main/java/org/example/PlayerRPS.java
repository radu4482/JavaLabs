package org.example;

import java.util.Random;

public class PlayerRPS {
    String theChoose;

    public PlayerRPS() {
    }


    public void randomChoose() {
        Random random = new Random();
        int value = random.nextInt(3);
        if (value == 0) theChoose = "Rock";
        else if (value == 1) theChoose = "Paper";
        else theChoose = "Scissors";
    }
}
