package view;


import controller.Game;

import java.awt.*;

public class GamePanel {
    public static final String strFile = System.getProperty("user.dir") +"/Resource";
//    public static final String strFile = System.getProperty("user.dir") + "/src/main/java/edu/uchicago/gerber/_08final/gold_miner/Resource";
//    Image bg = Toolkit.getDefaultToolkit().getImage("pic/back.png");
    Image character = Toolkit.getDefaultToolkit().getImage(strFile + "/image/people1.png");
    Image character1 = Toolkit.getDefaultToolkit().getImage(strFile+"/image/people2.png");
    Image character2 = Toolkit.getDefaultToolkit().getImage(strFile+"/image/people3.png");
    Image potion = Toolkit.getDefaultToolkit().getImage(strFile+"/image/water.png");
    Image selectImg = Toolkit.getDefaultToolkit().getImage(strFile+"/image/select.png");

    // level
    public static int level = 1;
    //target cent
    public int goal = level * 13;
    // when reach level * 10 cent, we can go into the next level

    // total cent
    public static int count = 0;
    public static int potionNumber = 3;
    public static  boolean potionFlag = false; // true is using now
    int potionPrice = (int) (Math.random() * 10);
    //whether buy the potion
    public boolean shop = false;
    //time
    public long startTime;
    long endTime;
    public void paintSelf(Graphics g, int select){
        GameFrame frame = new GameFrame();

        switch (Game.state){
            case 0:
                frame.painOpenning(g);
                drawString(g,50, Color.WHITE, "Click here to start!",300, 780);
                break;
            case 1:
                frame.painFrame(g);
//                g.drawImage(bg, 0, 200, null);
//                g.drawImage(sky, 0, 0, null);
//                g.drawImage(bg1, 250, 50, null);
//                g.drawImage(bg2, 1050, 50, null);
                if(select == 0) {
                    g.drawImage(character, 710, 50, null);
                }
                if(select == 1) {
                    g.drawImage(character1, 710, 50, null);
                }
                if(select == 2){
                    g.drawImage(character2, 710, 50, null);
                }
                g.drawImage(potion, 900, 40, null);
                // if you want to create different people, need create people class

                drawString(g, 30, Color.BLACK, "Your Cent: "+count, 30, 150);
                drawString(g, 30, Color.BLACK, "*"+ potionNumber, 1000, 70);

                drawString(g, 20, Color.BLACK,"Level: "+level, 30, 60);
                drawString(g, 30, Color.BLACK, "goal: "+goal, 30,110);
                endTime = System.currentTimeMillis();
                //count time
                long coundDown = 30- (endTime - startTime)/1000; //millisecond to second, count down
                //show time
                drawString(g, 30, Color.BLACK, "Time: "+ (coundDown > 0?coundDown:0), 1300, 100);
                break;
            case 2:
                frame.painFrame(g);
//                g.drawImage(bg, 0, 200, null);
//                g.drawImage(bg1, 250, 50, null);
//                g.drawImage(bg2, 1050, 50, null);
//                g.drawImage(sky, 0, 0, null);
                g.drawImage(potion, 400, 400, null);

                drawString(g, 30, Color.WHITE, "Price:"+potionNumber, 300, 500);
                drawString(g, 30, Color.WHITE, "Left-click to buy.", 300, 550);
                drawString(g, 30, Color.WHITE, "Right-click to give up.", 300, 600);
                drawString(g, 15, Color.WHITE, "Notice: Your will meet dangerous worms in the next level.", 300, 700);
                drawString(g, 15, Color.WHITE, "They will eat your hook", 300, 720);
                if (shop){
                    count -= potionPrice;
                    potionNumber ++;
                    shop = false;
                    Game.state = 1;
                    startTime = System.currentTimeMillis();
                }
                break;
            case 3:
                frame.painFrame(g);
//                g.drawImage(bg, 0, 200, null);
//                g.drawImage(sky, 0, 0, null);
//                g.drawImage(bg1, 250, 50, null);
//                g.drawImage(bg2, 1050, 50, null);
                //fail
                drawString(g, 80, Color.BLACK, "Your Cent: "+count, 500, 650);
                drawString(g, 80, Color.BLACK, "Game Over", 550, 550);
                break;
            case 4:
                frame.painFrame(g);
//                g.drawImage(bg, 0, 200, null);
//                g.drawImage(sky, 0, 0, null);
//                g.drawImage(bg1, 250, 50, null);
//                g.drawImage(bg2, 1050, 50, null);
                drawString(g, 80, Color.RED, "Your Cent: "+count, 500, 650);
                drawString(g, 80, Color.RED, "You Win", 650, 550);
                drawString(g, 50, Color.WHITE, "Left-click to restart", 750, 550);
                break;
            case 6:
                frame.painFrame(g);
//                g.drawImage(bg, 0, 200, null);
//                g.drawImage(sky, 0, 0, null);
//                g.drawImage(bg1, 250, 50, null);
//                g.drawImage(bg2, 1050, 50, null);
                //g.setColor(Color.cyan);
                //g.setFont(new Font("Times", Font.BOLD, 50));
                drawString(g,80, Color.cyan, "Oops!Your hook was eaten", 250, 350);
                drawString(g,80, Color.cyan, "Game Over", 550, 500);
                break;
            case 7:
                g.drawImage(selectImg, 0, 0, null);
                drawString(g,80, Color.ORANGE, "Hey! Choose your character",200, 250);
                break;
            default:
        }

    }

    //true: finish, false:going
    public boolean haveTime(){
        long spendTime = (endTime - startTime)/1000;
        return spendTime > 30;

    }

    //remake
    public void remake(Graphics g){
        drawString(g,80, Color.cyan, "Oops!Your hook was eaten", 250, 350);
        level = 1;
        goal = level * 13;
        count = 0;
        potionNumber = 3;
        potionFlag = false;
    }

    //print word
    public static void drawString(Graphics g, int size, Color color, String str, int x, int y){
        // print the current credit
        g.setColor(color);
        g.setFont(new Font("Times", Font.BOLD, size));
        g.drawString(str, x, y);
    }
}
