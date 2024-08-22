package main;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main
{
    public static void main(String[] args)
    {
        GamePanel gamePanel = new GamePanel();

        SwingUtilities.invokeLater(() ->
        {
            JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        window.setTitle("2D Battle");

        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.startGameThread();

        });
        
    }
}