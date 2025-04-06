package com.example.week12;

public class Player {
    private int damage;
    private int score;

    public Player() {
        score = 0;
        /*Not the music kind*/
    }

    public void attack(Monster monsterType) {
        score += 10;
    }

    public int getScore() {
        return score;
    }
}
