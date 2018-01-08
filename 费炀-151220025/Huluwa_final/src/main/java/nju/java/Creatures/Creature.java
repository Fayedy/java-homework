package nju.java.Creatures;

import nju.java.Constant.CommonFunc;
import static nju.java.Constant.CommonValue.*;
import nju.java.Field;
import nju.java.Position;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class Creature implements Runnable{
    private Field field;

    private Image image;
    private Image deadimg;

    private Position position;

    private boolean good;
    private boolean dead;

    public Creature(Field f, boolean g) {
        this.field = f;
        this.good = g;
        this.dead = false;
        URL loc = this.getClass().getClassLoader().getResource("幽灵.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        deadimg = image;
    }

    public boolean ifGood() {
        return good;
    }

    public boolean ifSameTeam(Creature creature) {
        return (good == creature.ifGood());
    }

    public Image getImage() {
        return this.image;
    }

    public void setImage(Image img) {
        image = img;
    }

    public Creature getCreature() {
        return this;
    }

    public void report() {
        System.out.print(this);
    }

    public void setPosition(Position position) {
        this.position = position;
        position.setHolder(this);
    }


    public Position getPosition()
    {
        return position;
    }

    public void gethurt() {
        Random rand = new Random();
        if(rand.nextInt(2) == 1) {
            System.out.println(this + "dead");
            dead = true;
            image = deadimg;
            if(field != null) {
                field.reportdead(this);
            }
        }
    }

    public void die() {
        dead = true;
        image = deadimg;
        if(field != null) {
            field.reportdead(this);
        }
    }

    public boolean ifDead() {
        return dead;
    }



    public void run() {
        while (!Thread.interrupted()) {
            Random rand = new Random();

            //if(!this.dead) {
                //synchronized (field) {
                    if (!this.dead) {
                        Position toAttack = search();
                        //System.out.println(this + "->" + toAttack.getHolder());
                        if (toAttack != null) {
                            int d = CommonFunc.countdis(this.position, toAttack);
                            if (d < ATTACK_DISTANCE) {
                                System.out.println("fire");
                                toAttack.getHolder().gethurt();
                            } else {
                                int dx = toAttack.getX() - position.getX();
                                int dy = toAttack.getY() - position.getY();
                                Position toGo = CommonFunc.nextpos(this.position, dx, dy);
                                System.out.println(toGo);
                                if (!this.dead && field.nobody(this, toGo)) {
                                    System.out.println("gogo");
                                    //this.position.setPos(toGo.getX(), toGo.getY());
                                }
                            }

                        } else if (!field.ifcompleted()){
                            field.reportwin(this);
                        }
                    }
                //}


                try {

                    Thread.sleep(rand.nextInt(100) + 500);
                    this.field.repaint();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            //}
        }
    }

    private Position search() {
        ArrayList positions = field.getWorld();
        Position attack = null;
        int distance = Integer.MAX_VALUE;
        for (int i = 0; i < positions.size(); i++) {
            Position item = (Position) positions.get(i);
            if(!ifSameTeam(item.getHolder())) {
                int d = CommonFunc.countdis(this.position, item);
                if (d < distance) {
                    distance = d;
                    attack = item;
                }
            }
        }
        //System.out.println(distance);
        return attack;
    }

}
