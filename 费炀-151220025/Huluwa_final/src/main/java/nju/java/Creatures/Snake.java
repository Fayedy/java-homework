package nju.java.Creatures;

import nju.java.Field;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Random;

public class Snake extends Creature{


    public Snake(Field f) {
        super(f, false);

        URL loc = this.getClass().getClassLoader().getResource("蛇精.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }

    @Override
    public String toString(){
        return "蛇精";
    }


}
