package com.example.week12;

import java.util.Random;

public class Skeleton extends Monster {
    private static String[] names = new String[]{"skeleton 1", "skeleton 2", "skeleton 3"};
    private static Random random = new Random();
    public Skeleton() {

        super(generateRandomLife(), generateRandomName());
        /*Spooky and scary*/
    }

    private static int generateRandomLife() {
        return random.nextInt(30) + 20;
    }


    private static String generateRandomName() {
        return names[random.nextInt(names.length)];
    }


}
