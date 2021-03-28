package org.example;
import java.util.*;

public abstract class Player implements Runnable{
    int points;
    ArrayList<Tokenn> player_tokens;
    int playerNumber;
    String name;
    Board board;
    boolean notchosen=true;
    public Player(String name) {
        this.name = name;
        this.points = 0;
        player_tokens=new ArrayList<Tokenn>();
    }

    abstract void choose();

    public void TokenSort() {
        Tokenn aux;
        for (int i = 0; i < player_tokens.size() - 1; i++)
            if (player_tokens.get(i).getValue() > player_tokens.get(i + 1).getValue()) {
                aux = player_tokens.get(i);
                player_tokens.set(i, player_tokens.get(i+1));
                player_tokens.set(i+1,aux);
            }
    }

    public int SizeOfAP() {
        if (this.player_tokens.size() < 3) return this.player_tokens.size();

        int max = 1;
        int aux = 1;

        // Sort array
        TokenSort();

        //Dupa ce sortez tokenurile, verific portiunea cea mai lunga de progresie aritmetica
        int d = player_tokens.get(1).getValue() - player_tokens.get(0).getValue();
        for (int i = 2; i < player_tokens.size(); i++)
            if (player_tokens.get(i).getValue()- player_tokens.get(i - 1).getValue() != d)
                aux++;
            else {
                if (aux > max)
                    max = aux;
                aux = 0;
            }

        return max;
    }

    abstract void Thread1() throws InterruptedException;

    abstract void Thread2() throws InterruptedException;

    public void addBoard(Board board) {
        this.board = board;

        System.out.println("gata player:"+this.name);
    }

    public void setNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public void addPoints(int amount) {
        this.points += amount;
    }

}
