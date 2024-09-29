package Main;

import Main.object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp ;
    Font arial_40 ,arial_80B;
    BufferedImage keyIm;
    public Boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean isFinished = false ;
    Double playTime = 0.000 ;
    DecimalFormat dFormat = new DecimalFormat("#0.00");
    public UI (GamePanel gp){
        this.gp = gp;
        this.arial_40 = (new Font("Arial",Font.PLAIN,40));
        this.arial_80B = (new Font("Arial",Font.BOLD,80));
        OBJ_Key key =new OBJ_Key();
        keyIm =key.image;
    }
    public void showMessage (String text){
        message = text;
        messageOn = true;

    }
    public void draw(Graphics2D g2){

        if (isFinished){
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            String text;
            int textLength;
            int x;
            int y;
            text = "You Found the Chest";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2-textLength/2;
            y = gp.screenHeight/2 - (gp.tileSize*3);

            g2.drawString(text,x,y);
            text = "Your time is:"+ dFormat.format(playTime);
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2-textLength/2;
            y = gp.screenHeight/2 + (gp.tileSize*4);

            g2.drawString(text,x,y);
            g2.setFont(arial_80B);
            g2.setColor(Color.yellow);
            text = "Congratulation!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2-textLength/2;
            y = gp.screenHeight/2 + (gp.tileSize*2);
            g2.drawString(text,x,y);
            gp.gameThread =null ;

        }else {
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            g2.drawImage(keyIm,gp.tileSize/2,gp.tileSize/2,gp.tileSize,gp.tileSize,null);
            g2.drawString("x "+ gp.player.hasKey ,74,65);

            //TIME
            playTime = playTime + (double) 1 / 60;
            g2.drawString("Time:"+dFormat.format(playTime) ,gp.tileSize*11,65);
            if (messageOn){
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message,gp.tileSize/2,gp.tileSize*5);
                messageCounter++;
                if (messageCounter>120){
                    messageCounter = 0;
                    messageOn = false ;
                }
            }
        }


    }
}
