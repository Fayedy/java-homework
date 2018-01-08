package nju.java.Creatures;

import nju.java.Field;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class LouLuo extends Creature{
    private int index;

    public LouLuo(int i, Field f) {
        super(f, false);
        index = i;

        URL loc = this.getClass().getClassLoader().getResource("喽啰.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }

    @Override
    public String toString()
    {
        return "喽啰" + index;
    }
}
