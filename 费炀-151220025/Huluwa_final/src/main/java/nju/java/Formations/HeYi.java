package nju.java.Formations;

import nju.java.Creatures.Creature;
import nju.java.Position;

public class HeYi extends Formation{
    public HeYi(int a, int b, int w, int h, Creature[] c, Creature l) {
        super(a, b, w, h, c, l);
    }

    @Override
    public void arrange() {
        int head = width / 2;
        lp = new Position(head, height - 1);
        leader.setPosition(lp);
        for(int i = 0; i < creatures.length; i++) {
            if(i == 0) {
                positions[i] = new Position(head, height - 2);

            }
            else {
                if(i % 2 == 1) {
                    if((height - (i + 1)/2 - 2 < 0) || (head - (i + 1)/2 < 0)) break;
                    positions[i] = new Position(head - (i + 1)/2, height - (i + 1)/2 - 2);

                }
                else {
                    if((height - i / 2 - 2 < 0) || (head + i / 2 > width - 1)) break;
                    positions[i] = new Position(head + i / 2, height - i / 2 - 2);
                }
            }
            creatures[i].setPosition(positions[i]);
        }
    }
}
