package model;

import java.awt.*;
import model.*;
import controller.*;
import view.*;

public class Chain {
    public static final String strFile = System.getProperty("user.dir") +"/Resource";
//    public static final String strFile = System.getProperty("user.dir") + "/src/main/java/edu/uchicago/gerber/_08final/gold_miner/Resource";
    int x = 780;
    int y = 170; // location of the chain

    int endX = 500;
    int endY = 500;

    double length = 100;
    double MIN_LEN = 100;
    double MAX_LEN = 750;
    double n = 0;
    // change the n value

    // endX = x + len * cos(n * pi)
    // endY = y + len * sin(n * pi)
    // directiom: if n reache the range, turn back
    int direction = 1;

    // condition = 0: swag; 1: catch; 2: back; 3: catch back
    public int condition;
    public Image hook = Toolkit.getDefaultToolkit().getImage(strFile+"/image/hook.png");
    Game frame;

    // have parameter  construction
    public Chain(Game frame){
        // catch judgement
        this.frame = frame;
    }

    // judgment of clash
    void logic(){
        for (Object obj : this.frame.objectList){
            if (endX > obj.x && endX < obj.x + obj.width
                    && endY > obj.y && endY < obj.y + obj.height){
                // catch
                this.condition = 3;
                obj.flag = true;
                new Music().playMusic(strFile+"/music/pick.wav");
                // judge whether we touch the worm
                if(obj.type == 4){
                    //System.out.println("Oops!You touched the worm!");
                    // the worm are the hook
                    this.hook = null;
                }
            }
        }
    }

    // draw
    void chain(Graphics g){
        endX = (int) (x + length * Math.cos(n* Math.PI));
        endY = (int) (y + length * Math.sin(n* Math.PI));
        g.setColor(Color.red);
        g.drawLine(x - 1, y, endX -1 , endY);
        g.drawLine(x, y, endX, endY);
        g.drawLine(x + 1, y, endX - 1 , endY);
        g.drawImage(hook, endX - 36, endY - 2, null);

    }


    // catch
    public void paintSelf(Graphics g){
        logic();
        // chain condition
        switch (condition){
            case 0:
                if (n < 0.1){
                    direction = 1;
                } else if (n > 0.9) {
                    direction = -1;
                }

                n += 0.005 * direction;
                chain(g);
                break;
            case 1:
                //range
                if (length <= MAX_LEN){
                    length += 5;
                    // expand and draw again
                    chain(g);
                }else {
                    condition = 2;
                }
                break;
            case 2:
                if (length > MIN_LEN){
                    length -= 5;
                    chain(g);
                }else {
                    condition = 0;
                }
                break;
            case 3:
                int w = 0;//initial weight to determine the speed of catching

                if (length > MIN_LEN) {
                    // length -= 5;
                    length -= 5;

                    chain(g);
                    for (Object obj : this.frame.objectList) {
                        if (obj.flag){ //  can move
                            w = obj.weight; // heavier -> slower
                            length += w;
                            obj.x = endX - obj.getWidth()/2;
                            obj.y = endY;
                            if (length <= MIN_LEN) {
                                obj.x = -150;
                                obj.y = -150;
                                obj.flag = false;
                                new Music().playMusic(strFile+"/music/pick.wav");
                                GamePanel.potionFlag = false;
                                GamePanel.count += obj.count; // add cent
                                condition = 0;
                            }
                            if (GamePanel.potionFlag){
                                if(obj.type == 2){
                                    // explode the stone
                                    obj.x = -150;
                                    obj.y = -150;
                                    obj.flag = false;
                                    GamePanel.potionFlag = false;
                                    condition = 2;
                                }
                            }
                        }
                    }

                }

                break;
            default:
        }

    }

    public void remake(){
        n = 0;
        length = 100;
    }

}
