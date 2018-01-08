package nju.java;

import nju.java.Creatures.Creature;
import nju.java.Creatures.Snake;
import org.junit.Test;

import static org.junit.Assert.*;

public class PositionTest {
    @Test
    public void holdertest() {
        Snake c = new Snake(null);
        Position p = new Position(0, 0);
        p.setHolder(c);
        assert (p.getHolder() == c);
        assert (p.getHolder().getClass() == Snake.class);
    }

}