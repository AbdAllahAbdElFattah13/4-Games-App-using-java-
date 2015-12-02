/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package XO;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import sun.awt.windows.ThemeReader;

/**
 *
 * @author AbdAllah Bodaz
 */
public abstract class  XOGame extends JPanel
{
    int [][]arr;
    final Image BackGround, X_Img, O_Img, X_Win, O_Win, Tie;
    final int X_SIZE, Y_SIZE;
    boolean GameStart, GameOn, Win = false;
    int i, j;
    
    public XOGame ()
    {
        arr = new int [3][3];
        BackGround = new ImageIcon(getClass().getResource("xo.png")).getImage();
        X_Img = new ImageIcon(getClass().getResource("x.png")).getImage();
        X_Win = new ImageIcon(getClass().getResource("xwin.png")).getImage();
        
        O_Img = new ImageIcon(getClass().getResource("o.png")).getImage();
        O_Win = new ImageIcon(getClass().getResource("owin.png")).getImage();
        
        Tie = new ImageIcon(getClass().getResource("xoDraw.png")).getImage();
        X_SIZE = Y_SIZE = 168;
    }
    
    int ChcekWin (int [][] Bord)
    {
        
        if ( ((Bord[0][0] == Bord[0][1]) && (Bord[0][1] == Bord [0][2]) && (Bord[0][1] == 1))
                || (((Bord[1][0] == Bord[1][1]) && (Bord[1][1] == Bord [1][2]) && (Bord[1][1] == 1)))
                || ((Bord[2][0] == Bord[2][1]) && (Bord[2][1] == Bord [2][2]) && (Bord[2][1] == 1))
                
                
                || ((Bord[0][0] == Bord[1][0]) && (Bord[1][0] == Bord [2][0]) && (Bord[1][0] == 1))
                || ((Bord[0][1] == Bord[1][1]) && (Bord[1][1] == Bord [2][1]) && (Bord[1][1] == 1))
                || ((Bord[0][2] == Bord[1][2]) && (Bord[1][2] == Bord [2][2]) && (Bord[1][2] == 1))
                
                || ((Bord[0][2] == Bord[1][1]) && (Bord[1][1] == Bord [2][0]) && (Bord[1][1] == 1))
                || ((Bord[0][0] == Bord[1][1]) && (Bord[1][1] == Bord [2][2]) && (Bord[1][1] == 1))) {Win = true; return 1;}
        
        
        if ( ((Bord[0][0] == Bord[0][1]) && (Bord[0][1] == Bord [0][2]) && (Bord[0][1] == 2))
                || (((Bord[1][0] == Bord[1][1]) && (Bord[1][1] == Bord [1][2]) && (Bord[1][1] == 2)))
                || ((Bord[2][0] == Bord[2][1]) && (Bord[2][1] == Bord [2][2]) && (Bord[2][1] == 2))
                
                
                || ((Bord[0][0] == Bord[1][0]) && (Bord[1][0] == Bord [2][0]) && (Bord[1][0] == 2))
                || ((Bord[0][1] == Bord[1][1]) && (Bord[1][1] == Bord [2][1]) && (Bord[1][1] == 2))
                || ((Bord[0][2] == Bord[1][2]) && (Bord[1][2] == Bord [2][2]) && (Bord[1][2] == 2))
                
                || ((Bord[0][2] == Bord[1][1]) && (Bord[1][1] == Bord [2][0]) && (Bord[1][1] == 2))
                || ((Bord[0][0] == Bord[1][1]) && (Bord[1][1] == Bord [2][2]) && (Bord[1][1] == 2))) {Win = true; return 2;}
        
        return 0;
    } //Done
    
    boolean isFinished(int [][] Bord)
    {
        for (int k = 0; k < 3; k++)
        {
            for (int l = 0; l < 3; l++)
            {
                if (Bord [k][l] == 0) return false;
            }
        }
        return true;
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        g.setColor(Color.BLUE);
        g.fillRect(400, 0, 500, 500);
        g.drawImage(BackGround, 0, 0, this);
        for (int k = 0; k < 3; k++)
        {
            for (int l = 0; l < 3; l++)
            {
                if (arr[k][l] == 1) {g.drawImage(X_Img, 30 + l*168, 30 + k*168, this);}
                if (arr [k][l] == 2) {g.drawImage(O_Img, 30 + l*168,30+  k*168, this);}
            }
        }
        
        int Winner = ChcekWin(arr);
        if (Win || isFinished(arr))
        {
            if (GameOn) 
            {
                GameOn = false;
                repaint();
            }
            if (Winner == 1)
            {
                //frist Player Wins
                g.drawImage(X_Win, 0, 0, this);
            }
            else if (Winner == 2)
            {
                //seconed Player Wins
                g.drawImage(O_Win, 0, 0, this);
            }
            else
            {
                //Tie
                g.drawImage(Tie, 0, 0, this);
            }
        }
    }
}
