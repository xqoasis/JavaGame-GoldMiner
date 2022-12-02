package controller;
import model.*;
import model.Object;
import view.*;
import controller.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Game extends JFrame {
    public static final String strFile = System.getProperty("user.dir") +"/Resource";
    // 0:unstart, 1: going, 2:store, 3:fail, 4:win
    public static int state;
    static int selectpaint  = 0;
    static int pelpleSelect; // choose the character
    // multiple gold and stone
    public List<Object> objectList = new ArrayList<>();
    GamePanel gamePanel = new GamePanel();
    Chain chain = new Chain(this);
    static  int x;
    static  int y;
    Worm worm = new Worm();

    //add gold (in class cannot write the for loop directly
    // code block or non-parameter construct
    {
        boolean isPlace = true; //whether it can be placed
        for (int i = 0; i < 13; ++i){
            double random = Math.random(); // different gold with different frequency

            //avoid overlapping
            Gold gold; // store the current gold
            if (random < 0.2){
                gold = new GoldMini();
            }else if (random < 0.7){
                gold = new Gold();
            }else {
                gold = new GoldPlus();
            }

            // judge overlapping
            for (Object obj : objectList){
                if (gold.getRectangle().intersects(obj.getRectangle())){
                    //if overlapping
                    isPlace = false;
                }
            }
            // different result of overlapping
            if (isPlace){
                objectList.add(gold);
            }else{
                isPlace = true;
                i--;
            }
        }
        for (int i = 0; i < 5; ++i){
            Stone stone = new Stone();
            // judge overlapping
            for (Object obj : objectList){
                if (stone.getRectangle().intersects(obj.getRectangle())){
                    //if overlapping
                    isPlace = false;
                }
            }
            // different result of overlapping
            if (isPlace){
                objectList.add(stone);
            }else {
                isPlace = true;
                i--;
            }
        }
        if (GamePanel.level >= 2){
            objectList.add(worm);
            objectList.add(new Worm());

        }

    }

    Image offScreenImage;
    void launch(){
        this.setVisible(true);
        this.setSize(1500, 1000); // corresponding to the background size
        this.setLocationRelativeTo(null);
        this.setTitle("Gold Miner");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // click right upper to close

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                switch (state){
                    case 0:
                        if(e.getButton() == 1&& selectpaint == 0){
                            x = e.getX();
                            y = e.getY();
                            if(x<800&&x>300&&y>600&&y<800){
                                new Music().playMusic(strFile+"/music/pick.wav");
                                selectpaint = 1;
                                state = 7;
                            }

                        }
                        break;
                    case 1:
                        // swing left or right, click the left
                        if (e.getButton() == 1 && chain.condition == 0){
                            chain.condition = 1;
                            new Music().playMusic(strFile+"/music/push.wav");
                        }
                        // when right click, we can use the portion
                        if (e.getButton() == 3 && chain.condition == 3 && GamePanel.potionNumber > 0){
                            GamePanel.potionFlag = true;
                            GamePanel.potionNumber --;
                        }
                        break;
                    case 2:
                        if (e.getButton() == 1){
                            gamePanel.shop = true;
                        }
                        if (e.getButton() == 3){
                            state = 1;
                            gamePanel.startTime = System.currentTimeMillis();
                        }
                        break;
                    case 3:// no break
                    case 4:
                        if (e.getButton() == 1){
                            state = 0;
                            chain.remake();
                        }
                        break;
                    case 7:
                        if(e.getButton() == 1) {
                            x = e.getX();
                            y  = e.getY();
                            if((x>258&&x<490)&&(y>540&&y<926)){
                                new Music().playMusic(strFile+ "/music/pick.wav");
                                pelpleSelect = 1;
                            }
                            if((x>748&&x<959)&&(y>453&&y<794)){
                                new Music().playMusic(strFile+"/music/pick.wav");
                                pelpleSelect = 2;
                            }
                            if((x>1131&&x<1378)&&(y>577&&y<926)){
                                new Music().playMusic(strFile+"/music/pick.wav");
                                pelpleSelect = 0;
                            }
                            state = 1;
                            gamePanel.startTime = System.currentTimeMillis(); // system time
                        }
                        break;

                    default:
                }
                // left 1, right 3, roll 2

            }
        });

        // repeat painting
        while (true){
            repaint();
            nextLevel();
            try{
                Thread.sleep(10);// 1s 100 times
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    //next level method
    public void nextLevel() {
        if (gamePanel.haveTime() && state == 1) {
            if (GamePanel.count >= gamePanel.goal) {
                // refresh, into next level
                if (GamePanel.level == 2) {
                    state = 4;
                } else {
                    state = 2;
                    GamePanel.level++;
                }
            }else {
                //fail
                state = 3;
            }
            dispose();
            Game game1 = new Game();
            game1.launch();
        }
    }

    public void paint(Graphics g){
        offScreenImage = this.createImage(1500,1000);
        Graphics gImage = offScreenImage.getGraphics();
        gamePanel.paintSelf(gImage,pelpleSelect); // g is the painter
        if (state == 1) {
            // obj first, then line
            for (Object obj : objectList) {
                obj.paintSelf(gImage);
            }
            chain.paintSelf(gImage);
            // paint the worm
            if (GamePanel.level >= 2){
                worm.paintSelf(gImage);
            }
            if(chain.hook==null){
                state = 6;
            }
            // when touch the worm, game over
            // extra canvas
        }
        // put into the window together

        g.drawImage(offScreenImage, 0, 0, null);

    }

    public static void main(String[] args) {
        new Music().playMusicLoop(strFile+"/music/back.wav");
        Game game = new Game();
        game.launch();
    }

}
