package model;
import model.*;
import view.*;
import controller.*;

import java.awt.*;

public class GoldMini extends Gold {
    public GoldMini() {
        this.width = 36;
        this.height = 36;
        this.weight = 0;
        this.img = Toolkit.getDefaultToolkit().getImage(strFile+"/image/gold0.gif");
        this.count = 2;
        //this.ismove = (int) (Math.random() * 100);
    }
}
