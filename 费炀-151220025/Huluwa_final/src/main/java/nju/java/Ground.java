package nju.java;


//import jdk.vm.ci.common.InitTimer;

import nju.java.Constant.CommonValue;
import nju.java.Constant.Status;

import javax.swing.*;


import java.io.IOException;
import java.util.TimerTask;
import java.util.Timer;

import static nju.java.Constant.CommonValue.*;


public final class Ground extends JFrame {

    private Timer timer;
    private TimerTask timerCheck;

    private Field field;


    public Ground() {
        InitUI();


    }

    public void InitUI() {
        field = new Field(this);
        add(field);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(field.getBoardWidth() + OFFSET,
                field.getBoardHeight() + 2 * OFFSET);
        setLocationRelativeTo(null);
        setTitle("Huluwa vs Snake");
    }

    public void InitTimer(int time) {
        timerCheck = new TimerTask() {

            public void run() {
                //System.out.println("record" + timer.toString());
                if(field.getStatus() == Status.PLAY)
                    record();
                else if(field.getStatus() == Status.REPLAY)
                    replay();
                else if(field.getStatus() == Status.FINISH && FileOperation.ifexistWriteFile())
                    FileOperation.newWriteFile();
            }
        };

        timer = new Timer();
        timer.schedule(timerCheck, 0, time);

    }






    private void record() {
        try {
            FileOperation.writeFile(field.getWorld(), field.getDeadworld());
        }
        catch (IOException e){
                e.printStackTrace();
        }
    }

    private void replay() {
        String str;
        if (FileOperation.getReadFile() == null) {
            return;
        }

        str = FileOperation.getNextString();


        field.play(str);
    }


}

