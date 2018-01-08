package nju.java.Formations;

import java.util.Random;
//import java.util.concurrent.ThreadLocalRandom;

import nju.java.Creatures.Creature;
import nju.java.Position;

public class ChangShe extends Formation{
    public Position[] getPositions() {
        return positions;
    }


    public Creature[] getCreatures() {
        return creatures;
    }




    public ChangShe(int a, int b, int w, int h, Creature[] c, Creature l) {


        super(a, b, w, h, c, l);

    }


    private void shuffle() {
        Random rand = new Random();
        for (int i = creatures.length - 1; i > 0; i--) {
            int index = rand.nextInt(i + 1);
            Position position = creatures[index].getPosition();
            creatures[index].setPosition(creatures[i].getPosition());
            creatures[i].setPosition(position);
        }
    }


    @Override
    public void arrange() {
        for (int i = 0; i < creatures.length; i++) {

            this.positions[i] = new Position(0, i);
            this.creatures[i].setPosition(this.positions[i]);
        }
        shuffle();

        lp = new Position(2, 3);
        leader.setPosition(lp);
    }
}
