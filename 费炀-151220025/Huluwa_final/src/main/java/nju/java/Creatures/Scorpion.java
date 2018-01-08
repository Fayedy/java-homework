package nju.java.Creatures;

import nju.java.Field;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Scorpion extends Creature{

    public Scorpion(Field f) {
        super(f, false);

        URL loc = this.getClass().getClassLoader().getResource("蝎子.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }

    @Override
    public String toString(){
        return "蝎子";
    }
}
