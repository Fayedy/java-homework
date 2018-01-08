package nju.java.Creatures;

import nju.java.Constant.SENIORITY;
import org.junit.Test;

import static org.junit.Assert.*;

public class CreatureTest {
    @Test
    public void dietest() {
        Huluwa hulu = new Huluwa(SENIORITY.大娃, null);
        hulu.die();
        assert (hulu.ifDead() == true);
    }

    @Test
    public void sameteamtest() {
        Huluwa hulu = new Huluwa(SENIORITY.七娃, null);
        Grandpa grandpa = new Grandpa(null);
        Snake snake = new Snake(null);

        assert (hulu.ifSameTeam(grandpa) == true);
        assert (hulu.ifSameTeam(snake) == false);
    }

}