/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package XO;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author AbdAllah Bodaz
 */
public class XO_Vs_AI extends XOGame
{
    int count = 0;
    int toto, soso;
    public XO_Vs_AI()
    {
        super();
        setVisible(true);
        addMouseListener(new inner());
        setFocusable(true);
        this.GameOn = this.GameStart = true;
        
    }
    
    private ArrayList<Point> GentateMoves(int [][] Bord)
    {
        ArrayList < Point > To_Return = new ArrayList<Point>();
        for (int k = 0; k < 3; k++)
        {
            for (int l = 0; l < 3; l++)
            {
                if (Bord[k][l] == 0)
                {
                    To_Return.add(new Point(k, l));
                }
            }
        }
        return To_Return;
    } //Done
    
    void Best_Move()
    {
        int Cur_Score, bst_Score = Integer.MIN_VALUE;
        ArrayList < Point > Childern = new ArrayList<>(GentateMoves(arr));
        Point Child = new Point(), To_Play = new Point();
        
        for (int i = 0; i < Childern.size(); i++)
        {
            Child = (Point) Childern.get(i).clone();
            arr [(int)Child.getX()][(int)Child.getY()] = 2; //for Computer play
            
            Cur_Score = MiniMax (true, arr);
            
            arr [(int)Child.getX()][(int)Child.getY()]= 0;
            
            if (Cur_Score > bst_Score)
            {
                To_Play = (Point)Child.clone();
                bst_Score = Cur_Score;
            }
        }
        arr [(int)To_Play.getX()][(int)To_Play.getY()] = 2;
        
    }
    
    private int MiniMax (boolean humen_turn, int [][] Bord) // Depth
    {
        //Base Case
        if (ChcekWin(arr) != 0 || isFinished(arr))
        {
            int win = ChcekWin(arr);
            if (win == 1)
                return -1;
            if (win == 2)
                return 1;
            return 0;
        } // end of base case
        
        int cur_score, bst_score = humen_turn ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        
        ArrayList < Point > Moves = GentateMoves(arr);
        
        Point Move;
        for (int k = 0; k < Moves.size(); k++)
        {
            Move = (Point)Moves.get(k).clone();
            
            Bord [(int)Move.getX()][(int)Move.getY()] = humen_turn ? 1 : 2;
            
            cur_score = MiniMax (!humen_turn, arr);
            
            Bord [(int)Move.getX()][(int)Move.getY()] = 0;
            this.Win = false;
            
            bst_score = humen_turn ? Math.min (cur_score, bst_score) : Math.max (cur_score, bst_score);
        }
        
        return bst_score;
    }
    
    private class inner extends MouseAdapter
    {
        @Override
        public void mouseClicked (MouseEvent e)
        {
            
            
            if (e.getButton() == 3) {return;}
            i = e.getY() / Y_SIZE;
            j = e.getX() / X_SIZE;
            
            if (arr [i][j] != 0) {return;}
            
            arr [i][j] = 1;
            Best_Move();
            
            repaint();
        }
    }
  }
