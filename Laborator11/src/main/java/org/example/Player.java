package org.example;

import java.util.Random;

public class Player {
    String name;
    int Id;
    Game game;

    public Player(Game game, String name) {
        this.name = name;
        this.game = game;
        this.Id = generateId();
    }

    public int generateId() {
        int value;
        Random random = new Random();
        do {
            value = random.nextInt(100);
        } while (game.ocupiedId(value));
        return value;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return this.Id;
    }
}
