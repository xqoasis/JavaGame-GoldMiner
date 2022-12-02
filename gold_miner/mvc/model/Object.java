package model;

import java.awt.*;

public class Object {
    public static final String strFile = System.getProperty("user.dir") +"/Resource";
//    public static final String strFile = System.getProperty("user.dir") + "/src/main/java/edu/uchicago/gerber/_08final/gold_miner/Resource";
    int ismove ;
    int x;
    int y;
    int width;
    int height;
    Image img;
    int dir = 0;
    boolean flag; // false: unmovable. true: can be moved

    //weight
    int weight;

    //cent
    int count;
    int type; //distinguish gold or stone. 1: gold; 2: stone

    public void paintSelf(Graphics g){
        move();
        g.drawImage(img,x,y, null);

    }

    public int getWidth(){
        return  width;
    }

    // avoid overlapping -> get the rectangle
    public Rectangle getRectangle(){
        return new Rectangle(x, y, width, height);
    }
    // add the move property
    public void move(){
        // direction
        // have 20% possibilities to move
        if(ismove<40){
            if(x>=1450){
                dir = 1;
            }
            if(x<=20){
                dir = 0;
            }
            if(dir==0){
                x = x + 2;
            }
            else {
                x = x-2;
            }

        }

    }

}
