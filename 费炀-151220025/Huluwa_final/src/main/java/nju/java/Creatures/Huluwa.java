package nju.java.Creatures;

import nju.java.Field;
import nju.java.Constant.SENIORITY;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Huluwa extends Creature {

    private SENIORITY seniority;


    public SENIORITY getSeniority() {
        return seniority;
    }


    public Huluwa(SENIORITY seiority, Field f) {
        super(f, true);


        this.seniority = seiority;

        URL loc = this.getClass().getClassLoader().getResource(this.seniority.toString() + ".png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }


    @Override
    public String toString() {
        return this.seniority.toString();
    }

}


