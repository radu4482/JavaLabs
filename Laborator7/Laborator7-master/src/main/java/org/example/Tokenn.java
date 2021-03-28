package org.example;

import java.util.Random;

public class Tokenn {
    int value;

    public Tokenn(int max) {
        Random random = new Random();
        this.value = random.nextInt(max);
    }

    public int getValue() {
        return value;
    }
}
