package org.example;

import java.util.*;

public class Board {
    ArrayList<Tokenn> tokens;
    ArrayList<Player> players;
    int max;
    int win_points;
    Game game;
    public Board(int m, int n, Game game) {
        this.tokens = new ArrayList<Tokenn>();
        this.players = new ArrayList<Player>();
        this.game = game;
        this.win_points = n;
        this.max = m;
        //set tokens
        Tokenn token;
        for (int i = 0; i < n; i++) {
            token = new Tokenn(m);
            this.tokens.add(token);
        }
    }

   /* public int[] uniqueRandomElements (int size) {
        Random random=new Random();
        for (int i = 0; i < size; i++) {
            tokens[i] = random.nextInt(size);

            for (int j = 0; j < i; j++) {
                if (tokens[i] == tokens[j]) {
                    tokens[j] = (int) (Math.random() * 10);
                }
            }
        }
    }*/

    public boolean removeToken(int index) {
        if (tokens.size() < index) return false;
        for (int i = index; i <= tokens.size() - 2; i++)
            tokens.set(i, tokens.get(i + 1));
        tokens.remove(tokens.size()-1);
        return true;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
        player.addBoard(this);
    }

    public boolean GameFinished() {
        if (tokens.size() < 1)
        {for (Player p : players)
                p.addPoints(p.SizeOfAP());
        System.out.println("Egalitate");
        return true;
            }
        else {
            for (Player p : players)
                if (p.SizeOfAP() == max) {
                    p.addPoints(p.SizeOfAP());
                    System.out.println("Jucatorul "+p.name+" a castigat");
                    return true;
                }
        }
        return false;
    }

    public int getAmountTokens() {
        return tokens.size();
    }
}

