package nju.java.Creatures;

import nju.java.Field;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Grandpa extends Creature{

    public Grandpa(Field f) {
        super(f, true);

        URL loc = this.getClass().getClassLoader().getResource("爷爷.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }

    @Override
    public String toString(){
        return "爷爷";
    }
}
