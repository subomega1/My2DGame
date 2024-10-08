package Main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D adventure");
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();  // adjust window size to fit components
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.setUpGame();
        gamePanel.startGame();
    }

}