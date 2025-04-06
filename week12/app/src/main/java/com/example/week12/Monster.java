package com.example.week12;



public abstract class Monster {
    private String name;
    private int life;
    private int maxLife;
    public Monster(int hirviönMaxHp, String hirviönNimi) {
        maxLife = hirviönMaxHp;
        name = hirviönNimi;
        life = maxLife;
    }

    public void takeDamage(int damage) {
        life -= damage;

    }
    public int getLife(){
        return life;
    }
    public String getName(){
        return name;
    }
    public int getMaxLife(){
        return maxLife;
    }

    public void setLife(int newlife) {
        life = newlife;
    }

}
