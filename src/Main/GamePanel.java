package Main;

import Main.object.SuperObject;
import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //screen settings
    final int originalTileSize = 16;//16x16
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;//48x48
   public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize*maxScreenCol;//768
    public final int screenHeight = tileSize*maxScreenRow;//576
    //WORLD SETTINGS

    public  final int  maxWorldCol = 50 ;
    public  final int  maxWorldRow  = 50 ;
    //FPS = 60
    int FPS = 60;
    TileManager tileM = new TileManager(this);

    KeyHandler keyH = new KeyHandler();
    Sound sound = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    Thread gameThread;

    // ENTITY AND OBJECT
   public   Player  player = new Player(this , keyH);
   public SuperObject [] obj = new SuperObject[10];

    //set original position
    //int playerX =100;
    //int playerY = 100;
   // int playerSpeed = 4;
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    public  void setUpGame(){
        aSetter.setObject();
      //  playS(0);
          playMusic(0);
    }
    public void startGame(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        // 0.016666 sec
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() +drawInterval;

        while(gameThread != null){
//            System.out.println("game is running");

            //1 update information such character
            update();
            //2 draws the screen
            repaint();
            try {
                double remainingTime = nextDrawTime -System.nanoTime();
                remainingTime = remainingTime/1000000;
                if (remainingTime<0){
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime+=drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void update(){
        player.update();
    }
    public void paintComponent( Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        //TILE
        tileM.draw(g2);
        //OBJECT
        for (int i=0;i< obj.length ;i++){
            if (obj[i] != null ){
                obj[i].draw(g2 ,this);
            }
        }
        //PLAYER
        player.draw(g2);
        g2.dispose();

    }
    public void playMusic(int i ){
        sound.setFile(i);
        sound.play();
        sound.loop();
    }
    public void stopMusic(){
        sound.stop();
    }

    public void playS(int i ) {
        sound.setFile(i);
        sound.play();
    }
    }
