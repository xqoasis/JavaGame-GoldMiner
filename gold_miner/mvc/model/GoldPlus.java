package model;
import model.*;
import view.*;
import controller.*;

import java.awt.*;

public class GoldPlus extends Gold {
    public GoldPlus() {
        this.x = (int) (Math.random() * 1400); // random
        this.width = 105;
        this.height = 105;
        this.weight = 2;
        this.img = Toolkit.getDefaultToolkit().getImage(strFile+"/image/gold2.gif");
        this.count = 8;
        this.ismove = (int) (Math.random() * 100);
    }
}
