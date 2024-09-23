package Main;

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
    public  final int  worldWidth = tileSize *maxWorldCol ;
    public  final int  worldHeight = tileSize *maxWorldRow ;
    //FPS = 60
    int FPS = 60;
    TileManager tileM = new TileManager(this);

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
   public   Player  player = new Player(this , keyH);
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
        tileM.draw(g2);
        player.draw(g2);
        g2.dispose();

    }
    }
