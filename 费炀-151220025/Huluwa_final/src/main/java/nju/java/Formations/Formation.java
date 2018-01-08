package nju.java.Formations;

import nju.java.Creatures.Creature;
import nju.java.Position;

import java.util.ArrayList;

public abstract class Formation {
    protected Creature[] creatures;
    protected Creature leader;
    protected Position[] positions;
    protected Position lp;
    private int x;
    private int y;
    protected int width;
    protected int height;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Formation(int a, int b, int w, int h, Creature[] c, Creature l) {
        x = a;
        y = b;
        width = w;
        height = h;
        this.creatures = c;
        this.leader = l;
        this.positions = new Position[creatures.length];
    }



    public abstract void arrange();

    public void fill(ArrayList g) {
        arrange();

        for(Position p : positions) {
            p.setX(x + p.getX());
            p.setY(y + p.getY());
            g.add(p);
        }
        lp.setX(x + lp.getX());
        lp.setY(y + lp.getY());
        g.add(lp);
    }
}
