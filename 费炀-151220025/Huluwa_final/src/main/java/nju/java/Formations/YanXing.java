package nju.java.Formations;

import nju.java.Creatures.Creature;
import nju.java.Position;

public class YanXing extends Formation{
    public YanXing(int a, int b, int w, int h, Creature[] c, Creature l) {
        super(a, b, w, h, c, l);
    }

    @Override
    public void arrange() {
        int center = height / 2;
        lp = new Position(0, center);
        leader.setPosition(lp);
        int head = 1;
        for(int i = 0; i < creatures.length; i++) {
            if((height - i - 2 < 0) || (head + i > width - 1)) break;
            positions[i] = new Position(head + i, height - i - 2);
            creatures[i].setPosition(positions[i]);
        }
    }
}
