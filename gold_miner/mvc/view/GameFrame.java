package view;

import java.awt.*;

public class GameFrame {
    public static final String strFile = System.getProperty("user.dir") +"/Resource";
//    public static final String strFile = System.getProperty("user.dir") + "/src/main/java/edu/uchicago/gerber/_08final/gold_miner/Resource";

    Image fore = Toolkit.getDefaultToolkit().getImage(strFile+ "/image/temp.png");
    Image bg = Toolkit.getDefaultToolkit().getImage(strFile+ "/image/back.png");
    Image sky = Toolkit.getDefaultToolkit().getImage(strFile+"/image/sky.jpg");
    Image bg1 = Toolkit.getDefaultToolkit().getImage(strFile+"/image/back1.png");
    Image bg2 = Toolkit.getDefaultToolkit().getImage(strFile+"/image/back2.png");

    public void painOpenning(Graphics g){
        g.drawImage(fore, 0, 0, null);
    }

    public void painFrame(Graphics g){
        g.drawImage(bg, 0, 200, null);
        g.drawImage(sky, 0, 0, null);
        g.drawImage(bg1, 250, 50, null);
        g.drawImage(bg2, 1050, 50, null);
    }
}
