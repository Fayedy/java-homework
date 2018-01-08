package nju.java.Constant;

//import nju.java.Creatures.Creature;
import nju.java.Position;

public class CommonFunc {
    public static final int countdis(Position p, Position q) {
        return Math.abs(p.getX() - q.getX()) + Math.abs(p.getY() - q.getY());
    }

    public static final Position nextpos(Position now, int dx, int dy) {
        Position next;
        if(Math.abs(dx) >= Math.abs(dy)) {
            int mx = dx > 0 ? 1:-1;
            next = new Position(now.getX() + mx, now.getY());
        }
        else {
            int my = dy > 0 ? 1:-1;
            next = new Position(now.getX(), now.getY() + my);
        }
        return next;
    }
}
