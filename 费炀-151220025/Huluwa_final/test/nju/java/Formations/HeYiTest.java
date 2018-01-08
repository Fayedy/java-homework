package nju.java.Formations;

import nju.java.Creatures.Creature;
import nju.java.Creatures.LouLuo;
import nju.java.Creatures.Scorpion;
import nju.java.Creatures.Snake;
import nju.java.Position;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class HeYiTest {
    @Test
    public void arrangetest() {
        Creature[] c = new Creature[7];
        c[0] = new Scorpion(null);
        for(int i = 1; i < c.length; i++) {
            c[i] = new LouLuo(i, null);
        }
        Snake snake = new Snake(null);
        HeYi heyi = new HeYi(5, 5, 10, 10, c, snake);

        heyi.arrange();
        assert (snake.getPosition().getX() == 5);
        assert (snake.getPosition().getY() == 9);
        assert (c[0].getPosition().getX() == 5);
        assert (c[0].getPosition().getY() == 8);
        assert (c[6].getPosition().getX() == 8);
        assert (c[6].getPosition().getY() == 5);
    }

    @Test
    public void filltest() {
        ArrayList world = new ArrayList();
        Creature[] c = new Creature[7];
        c[0] = new Scorpion(null);
        for(int i = 1; i < c.length; i++) {
            c[i] = new LouLuo(i, null);
        }
        Snake snake = new Snake(null);
        HeYi heyi = new HeYi(5, 5, 10, 10, c, snake);

        heyi.fill(world);
        assert (world.size() == 8);
        Position p = (Position) world.get(world.size() - 1);
        assert (p.getX() == 10);
        assert (p.getY() == 14);
    }


}