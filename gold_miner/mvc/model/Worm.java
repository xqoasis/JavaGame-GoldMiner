package model;
import java.awt.*;

public class Worm extends Object {
    public Worm(){
        this.type = 4;
        this.x = (int) (Math.random() * 700); // random
        this.y = (int) (Math.random() * 550 + 300);
        this.width = 52;
        this.height = 52;
        this.img = Toolkit.getDefaultToolkit().getImage(strFile+"/image/worm.png");
        this.flag = false;
        this.weight = 30; // don't need the weight for worm
        this.count = 4;
        this.ismove = 1; // can move

    }
}
