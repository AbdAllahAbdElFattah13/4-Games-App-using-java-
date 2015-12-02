/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Snake;

import Screens.gameFrame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


/**
 *
 * @author AbdAllah Bodaz
 */
public class snakeBoard extends JPanel implements ActionListener
{
  
    private int [][]Bord ; // 0: Die, 1: Peace, 2: Food
    private final int Delay = 140, SIZE = 10, FrameSize = 500, ArraySize = 500,
                      Die_Number = 1, Food_Number = 2, Fine_Number = 0;
    private boolean GameEnd ,Right, Left, Up, Down;
    private ArrayList<Node> SnakeBody;
    private int NumOfLifes, Score;
    private Timer timer;
    private final Image Blocks = new ImageIcon(getClass().getResource("blocks.png")).getImage();;
    private Node Food;
   
    public snakeBoard()
    {
        //this.add(gameFrame.panel);
        Food = new Node ("apple.png");
        setFocusable(true);
        
        addKeyListener(new Inner());
        
        timer = new Timer(Delay, this);
        timer .start();
        
        InIt();
        
    }
    
    private void InIt()
    {
        SnakeBody = new ArrayList<Node>();
        Right = true;
        Left = Up = Down = false;

        NumOfLifes = 3;
        Score = 0;
        
        Bord = new int[ArraySize][ArraySize];
        
        for (int i = 0; i < ArraySize; i+=SIZE)
        {
            for (int j = 0; j < ArraySize; j+=SIZE)
            {
                if (i == 0 || j == 0 || i == ArraySize - SIZE || j == ArraySize - SIZE)
                {
                    Bord[i][j] = Die_Number;
                }
            }
        }
        
        Node Piece;
        for (int i = 0; i < 3; ++i)
        {
            if(i == 0)
            {
                Piece = new Node ("head.png");
            }
            else
            {
                Piece = new Node ("dot.png");
            }
            
            Piece.setX(100-(i*SIZE));
            Piece.setY(100);
            Bord[Piece.getX()][Piece.getY()] = Die_Number;
            SnakeBody.add(Piece);
        }
        
        GameEnd = false;

        setFood();
    }
    
    private void GameLife()
    {
        if(Check() == Die_Number)
        {
            if (NumOfLifes == 0)
            {
                int Dis = JOptionPane.showConfirmDialog(this, "Play Again?", "Test!!", JOptionPane.YES_NO_OPTION);
                if (Dis == JOptionPane.YES_OPTION) 
                {
                    InIt();
                }
                else {timer.stop();}
            }
            else {InIt();}
        }
        update();
        repaint();
    }
    
    private void update()
    {
        Node Temp, HeadPos = (Node)SnakeBody.get(0).clone();
        
        if(Right) SnakeBody.get(0).setX(HeadPos.getX() + SIZE);
        if (Left) SnakeBody.get(0).setX(HeadPos.getX() - SIZE);
        if (Up) SnakeBody.get(0).setY(HeadPos.getY() - SIZE);
        if (Down) SnakeBody.get(0).setY(HeadPos.getY() + SIZE);
        
        Bord[SnakeBody.get(0).getX()][SnakeBody.get(0).getY()] = Die_Number;
        Bord[SnakeBody.get(SnakeBody.size()-1).getX()][SnakeBody.get(SnakeBody.size()-1).getY()] = Fine_Number;
        
        for (int i = 1; i < SnakeBody.size(); ++i)
        {
            Temp = (Node) SnakeBody.get(i).clone();
            SnakeBody.set(i, HeadPos);
            HeadPos = (Node) Temp.clone();
        }
    }
    
    private void setFood ()
    {
        int x, y;
        
        Bord[Food.getX()][Food.getY()] = Fine_Number;
        do
        {
            x = new Random().nextInt(FrameSize - 10);
            x += SIZE - (x%SIZE);
            
            y = new Random().nextInt(FrameSize - 10);
            y += SIZE - (y%SIZE);
        } while (Bord [x][y] != Fine_Number);
        
        Food.setX(x);
        Food.setY(y);
        Bord[Food.getX()][Food.getY()] = Food_Number;
    }
    
    private int Check ()
    {
        int Cur = Fine_Number;
        
        if (Right)
        {
            Cur = Bord[SnakeBody.get(0).getX() + SIZE][SnakeBody.get(0).getY()];
        }
        if (Left)
        {
            Cur = Bord[SnakeBody.get(0).getX() - SIZE][SnakeBody.get(0).getY()];
        }
        if (Up)
        {
            Cur = Bord[SnakeBody.get(0).getX()][SnakeBody.get(0).getY() - SIZE];
        }
        if (Down) 
        {
            Cur = Bord[SnakeBody.get(0).getX()][SnakeBody.get(0).getY() + SIZE];
        }
        // 0: Die, 1: Peace, 2: Food
        if (Cur == Die_Number) //Die
        {
//            Boda();
            GameEnd = true;
            --NumOfLifes;
            //JOptionPane.showMessageDialog(null,"Pos = " + SnakeBody.get(0).getX() + " " + SnakeBody.get(0).getY()+ " V = " + Cur);
        }
        if (Cur == Food_Number) //eat
        {
            Score += 2;
            SnakeBody.add(new Node("dot.png"));
            setFood();
        }
        // 0: Die, 1: Peace, 2: Food
        return Cur;
    }
    
    @Override
    public void paintComponent(Graphics g) // !!!
    {
        g.setColor(Color.BLUE);
        g.fillRect(400, 0, 500, 500);
        if (!GameEnd)
        {
            super.paintComponents(g);
            
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 500, 500);
            for (int i = 0; i < ArraySize; i+=SIZE)
            {
                for (int j = 0; j < ArraySize; j+=SIZE)
                {
                    if (i == 0 || j == 0 || i == ArraySize - SIZE || j == ArraySize - SIZE)
                    {
                        g.drawImage(Blocks, i, j, this);
                    }
                }
            }
            //draw food
            g.drawImage(Food.getImg(), Food.getX(), Food.getY(), this);
            
            //draw Snake
            for (int i = 0; i < SnakeBody.size(); ++i)
            {
                g.drawImage(SnakeBody.get(i).getImg(), SnakeBody.get(i).getX(), SnakeBody.get(i).getY(), this);
            }
        }
        else
        {
            GameOver(g);
        }
    }
    
    
    private void GameOver(Graphics g)
    {
        //to do logic of game over :)
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setFont(new Font(Font.SANS_SERIF, Font.ITALIC + Font.BOLD, 25));
        g.setColor(Color.BLUE);
        g.drawString("You Lost :P", this.getWidth()/2 - 125, this.getHeight()/2);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        GameLife();
    }
    
    
    private class Inner extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            int Key = e.getKeyCode();
            
            if ((Key == KeyEvent.VK_RIGHT && Right)
                    || (Key == KeyEvent.VK_LEFT && Left)
                    || (Key == KeyEvent.VK_UP && Up)
                    || (Key == KeyEvent.VK_DOWN && Down)) {return;}
            
            if (Key == KeyEvent.VK_RIGHT && !Left)
            {
                Right=true;
                Up=Down=Left=false;
            }
            if (Key == KeyEvent.VK_LEFT && !Right)
            {
                Left=true;
                Up=Down=Right=false;
            }
            if (Key == KeyEvent.VK_UP && !Down)
            {
                Up=true;
                Left=Right=Down=false;
            }
            if (Key == KeyEvent.VK_DOWN && !Up)
            {
                Down=true;
                Right=Left=Up=false;
            }
            GameLife();
        } 
    }
}
