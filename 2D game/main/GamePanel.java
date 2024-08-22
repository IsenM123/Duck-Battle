package main;
import javax.swing.JPanel;

import entity.Player;
import tile.tileManager;

//import javax.swing.plaf.basic.BasicSliderUI.ScrollListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable
{
    //Screen settings
    final int originalTileSize = 16; //16x16 tile
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; //48x48 tile

    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;


    //FPS
    int FPS = 60;

    tileManager tileM = new tileManager(this);


    KeyHandler keyH = new KeyHandler();
    Player player = new Player(this,keyH);


    Thread gameThread;

    


    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run()
    {

        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        

        //Gameloop
        while(gameThread != null)
        {
            
            //UPDATE info
            update();
            //DRAW screen with updated info
            repaint();
            
            

            try
            {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if(remainingTime < 0)
                {
                    remainingTime = 0;
                }
                
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }


        }
    }

    public void update()
    {
        player.update();

    }

    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        tileM.draw(g2);
        player.draw(g2);

        g2.dispose();

    }
}
