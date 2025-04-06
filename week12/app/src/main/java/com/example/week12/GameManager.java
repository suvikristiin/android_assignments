package com.example.week12;

import java.util.ArrayList;
import java.util.Random;

public class GameManager {
    private static GameManager gameManager = null;
    private Vampire vampire;
    private Skeleton skeleton;
    private static Monster latestMonster;
    public Player player;

    private GameManager() {
        player = new Player();
    }

    public static GameManager getInstance() {
        if (gameManager == null) {
            gameManager = new GameManager();
        }
        return gameManager;
    }


    public Player getPlayer() {
       return player;
    }

    public Monster generateMonster() {
        Random random = new Random();

        if (random.nextBoolean()) {
            latestMonster = new Vampire();
        } else {
            latestMonster = new Skeleton();
        }
        return latestMonster;

    }

    public Monster getLatestMonster() {
        return latestMonster;
    }

}
