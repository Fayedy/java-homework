package nju.java;

import nju.java.Creatures.Creature;

public class Position {

    private int x;
    private int y;

    public Creature getHolder() {
        return holder;
    }

    public void setHolder(Creature holder) {
        this.holder = holder;
    }



    private Creature holder;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return x + "," + y;
    }
}
