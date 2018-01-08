package nju.java;

import nju.java.Constant.Status;
import nju.java.Creatures.*;
import nju.java.Formations.*;
import nju.java.Constant.*;
import static nju.java.Constant.CommonValue.*;
//import nju.java.Creatures.Snake;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.*;

public class Field extends JPanel {




    private Ground ground;

    private Image bgImage;


    private ArrayList world = new ArrayList();
    private ArrayList deadworld = new ArrayList();
    private ArrayList<Thread> threads = new ArrayList<Thread>();


    private int w = 0;
    private int h = 0;
    private boolean completed = false;

    private String result = null;
    private Status status = Status.INIT;



    public Field(Ground d) {
        ground = d;
        addKeyListener(new TAdapter());
        setFocusable(true);
        initWorld();
    }

    public Status getStatus() {
        return status;
    }

    public int getBoardWidth() {
        return this.w;
    }

    public int getBoardHeight() {
        return this.h;
    }

    public boolean ifcompleted() {
        return completed;
    }

    public ArrayList getWorld() {
        return world;
    }

    public ArrayList getDeadworld() {
        return deadworld;
    }

    public final void initWorld() {


        completed = false;
        w = MAX_WIDTH;
        h = MAX_HEIGHT;

        URL loc = this.getClass().getClassLoader().getResource("背景.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.bgImage = image;







        Huluwa[] brothers = new Huluwa[7];
        for (int i = 0; i < brothers.length; i++) {
            brothers[i] = new Huluwa(SENIORITY.values()[i], this);

        }
        Grandpa grandpa = new Grandpa(this);
        ChangShe changshe = new ChangShe(3, 7, 10, 20, brothers, grandpa);

        changshe.fill(world);

        Creature[] c = new Creature[7];
        c[0] = new Scorpion(this);
        for(int i = 1; i < c.length; i++) {
            c[i] = new LouLuo(i, this);
        }
        Snake snake = new Snake(this);
        HeYi heyi = new HeYi(10, 3, 10, 10, c, snake);

        heyi.fill(world);

    }

    public void buildWorld(Graphics g) {







        g.drawImage(bgImage, 0, 0, this.getWidth(), this.getHeight(), this);



        for(int i = 0; i < deadworld.size(); i++) {
            Position item = (Position) deadworld.get(i);
            g.drawImage(item.getHolder().getImage(), item.getX() * SPACE, item.getY() * SPACE, SPACE, SPACE, this);
        }

        for (int i = 0; i < world.size(); i++) {


            Position item = (Position) world.get(i);


                g.drawImage(item.getHolder().getImage(), item.getX() * SPACE, item.getY() * SPACE, SPACE, SPACE, this);




        }

        if(status == Status.INIT) {
            g.setColor(new Color(250, 240, 170));
            g.fillRect(8 * SPACE, 7 * SPACE, 4 * SPACE, 5 * SPACE);
            g.setColor(new Color(0, 0, 0));

            g.drawString("按空格键开始", 9 * SPACE, 8 * SPACE);
            g.drawString("按L键回放", 9 * SPACE, 9 * SPACE);
            g.drawString("按R键重置", 9 * SPACE, 10 * SPACE);
            g.drawString("按S键暂停", 9 * SPACE, 11 * SPACE);
        }
        else if(status == Status.PLAY) {
            g.setColor(new Color(250, 240, 170));
            g.fillRect(20, 5, 100, 20);
            g.setColor(new Color(0, 0, 0));

            g.drawString("按S键暂停", 25, 20);
        }
        else if(status == Status.SUSPEND) {
            g.setColor(new Color(250, 240, 170));
            g.fillRect(20, 5, 100, 20);
            g.setColor(new Color(0, 0, 0));

            g.drawString("按S键继续", 25, 20);
        }
        else if(status == Status.FINISH) {
            g.setColor(new Color(250, 240, 170));
            g.fillRect(20, 5, 100, 40);
            g.setColor(new Color(0, 0, 0));

            g.drawString("按L键回放", 25, 20);
            g.drawString("按R键重置", 25, 40);
        }



        if (completed) {
            if(world.size() > 0) {
                Position p = (Position) world.get(0);
                if(p.getHolder().ifGood()) {
                    result = "Good Team Win! ^_^";
                }
                else {
                    result = "Bad Team Win! -_-";
                }
            }
            else {
                Position p = (Position) deadworld.get(deadworld.size() - 1);
                if(p.getHolder().ifGood()) {
                    result = "Good Team Win! ^_^";
                }
                else {
                    result = "Bad Team Win! -_-";
                }
            }







            g.setColor(new Color(250, 240, 170));
            g.fillRect(8 * SPACE, 7 * SPACE, 4 * SPACE, 2 * SPACE);
            g.setColor(new Color(255, 50, 72));


            g.drawString(result, (int)(8.7 * SPACE), 8 * SPACE);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        buildWorld(g);
    }

    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {






            int key = e.getKeyCode();




            if (status == Status.INIT && key == KeyEvent.VK_SPACE) {


                status = Status.PLAY;
                ground.InitTimer(TIME);
                initThread();



            } else if (status == Status.FINISH && key == KeyEvent.VK_R) {
                status = Status.INIT;
                restart();
            } else if (key == KeyEvent.VK_S) {
                if(status == Status.PLAY) {
                    status = Status.SUSPEND;
                    for (Thread t : threads)
                        t.suspend();
                }
                else if(status == Status.SUSPEND) {
                    status = Status.PLAY;
                    for(Thread t : threads)
                        t.resume();
                }

            } else if (status == Status.FINISH && key == KeyEvent.VK_L) {
                status = Status.REPLAY;
                replay();
            }

            repaint();
        }
    }


    public void restart() {


        world.clear();
        deadworld.clear();
        for(Thread t : threads)
            t.stop();
        threads.clear();
        initWorld();
        if (completed) {
            completed = false;
        }
        if(FileOperation.ifexistWriteFile()) {
            FileOperation.newWriteFile();
        }
    }

    private void initThread() {
        for (int i = 0; i < world.size(); i++) {


            Position item = (Position) world.get(i);
            threads.add(new Thread(item.getHolder()));
        }
        for(Thread t : threads)
            t.start();
    }

    public synchronized boolean nobody(Creature c, Position p) {
        if(p.getX() < 0 || p.getX() > MAX_POS_X || p.getY() < 0 || p.getY() > MAX_POS_Y)
            return false;
        for(int i = 0; i < world.size(); i++) {
            Position item = (Position) world.get(i);
            if(p.getX() == item.getX() && p.getY() == item.getY())
                return false;
        }
        c.getPosition().setPos(p.getX(), p.getY());
        return true;
    }

    public void reportdead(Creature c) {
        for(int i = 0; i < world.size(); i++) {
            Position item = (Position) world.get(i);
            if(item.getHolder() == c) {

                    deadworld.add(item);
                    world.remove(item);



                break;
            }

        }

    }

    public void reportwin(Creature c) {
        if(!completed) {
            completed = true;
            status = Status.FINISH;
            if (c.ifGood()) {
                result = "Good Team Win! ^_^";
            } else {
                result = "Bad Team Win! -_-";
            }

            this.repaint();
        }
    }

    public void replay() {
        int flag = 1;
        JFileChooser jFileChooser = null;



        jFileChooser = new JFileChooser(new File("save"));

        jFileChooser.setDialogTitle("选择需要回放的记录");
        flag = jFileChooser.showDialog(null, null);

        if (flag == JFileChooser.APPROVE_OPTION) {
            FileOperation.setReadFile(jFileChooser.getSelectedFile());



            for(Thread t : threads) {
                t.stop();
            }

            threads.clear();
            world.clear();
            deadworld.clear();
            initWorld();
            ground.InitTimer(REPLAY_TIME);
        }
    }

    public void play(String str) {
        if(str == null) {
            System.out.println("finish");
            completed = true;
            status = Status.FINISH;
        }
        else {
            String name = null;
            int x = -1, y = -1;
            boolean isAlive = false;

            String[] temp = str.split(" ");
            if( temp.length != 4 )
                return;
            name = temp[0];
            x = Integer.parseInt(temp[1]);
            y = Integer.parseInt(temp[2]);
            isAlive = temp[3].equals("1");

            for(int i = 0; i < world.size(); i++) {
                Position item = (Position) world.get(i);
                if(name.equals(item.getHolder().toString())) {
                    item.setPos(x, y);
                    if(!isAlive)
                        item.getHolder().die();
                }
            }

            for(int i = 0; i < deadworld.size(); i++) {
                Position item = (Position) deadworld.get(i);
                if (name.equals(item.getHolder().toString())) {
                    item.setPos(x, y);
                }
            }


            this.repaint();
        }
    }
}