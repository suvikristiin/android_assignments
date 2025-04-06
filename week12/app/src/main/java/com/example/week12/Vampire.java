package com.example.week12;

import java.util.Random;

public class Vampire extends Monster {
    private static String[] names = new String[]{"vampire 1", "vampire 2", "vampire 3"};
    private static Random random = new Random();
    public Vampire() {

        super(generateRandomLife(), generateRandomName());

        /*The Count: 1 bat Ha Ha Ha, 2 bats Ha Ha Ha...*/
    }

    private static int generateRandomLife() {
        return random.nextInt(30) + 20;
    }


    private static String generateRandomName() {
        return names[random.nextInt(names.length)];
    }

}
