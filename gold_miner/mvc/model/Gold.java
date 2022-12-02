package model;

import model.*;
import view.*;
import controller.*;

import java.awt.*;

public class Gold extends Object {
    // tag

    public Gold(){
        this.type = 1;
        this.x = (int) (Math.random() * 1450); // random
        this.y = (int) (Math.random() * 550 + 300);
        this.width = 52;
        this.height = 52;
        this.img = Toolkit.getDefaultToolkit().getImage(strFile+"/image/gold1.gif");
        this.flag = false;
        this.weight = 1;
        this.count = 4;
        this.ismove = 100;

    }
}

