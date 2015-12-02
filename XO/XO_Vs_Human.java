/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package XO;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

/**
 *
 * @author AbdAllah Bodaz
 */
public class XO_Vs_Human extends XOGame
{
    private boolean player_turn;
    
    public XO_Vs_Human()
    {
        super();
        addMouseListener(new inner());
        this.setFocusable(true);
        player_turn = this.GameStart = this.GameOn =  true;
    }
    
    private class inner extends MouseAdapter
    {
        @Override
        public void mouseClicked (MouseEvent e)
        {
            //JOptionPane.showMessageDialog(null, e.getButton());
            if (e.getButton() == 3) {return;}
            
            i = e.getY() / Y_SIZE;
            j = e.getX() / X_SIZE;
            
            if (arr [i][j] != 0) {return;}
            
            if (player_turn) {arr[i][j] = 1;} // for X
            else {arr[i][j] = 2;} // for O
            
            player_turn = !player_turn;
            
            repaint();
        }
    }
}

