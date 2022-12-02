package model;



import java.awt.*;

public class Stone extends Object {
    public Stone(){
        this.type = 2;
        this.x = (int) (Math.random() * 1400); // random
        this.y = (int) (Math.random() * 550 + 300);
        this.width = 71;
        this.height = 71;
        this.img = Toolkit.getDefaultToolkit().getImage(strFile+"/image/rock1.png");
        this.flag = false;
        this.weight = 3;
        this.count = 1;
        this.ismove = (int) (Math.random() * 100);
    }
}
