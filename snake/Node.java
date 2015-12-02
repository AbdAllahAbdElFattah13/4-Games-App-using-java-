/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Snake;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author AbdAllah Bodaz
 */
public class Node
{
    private Image img;
    private int x, y;
   // public Node () {img = new ImageIcon("C:/Users/AbdAllah Bodaz/Desktop/images/snake/dot.png").getImage();}
    public Node (String Image_Path) 
    {
        this.x = this.y = 0;
        img = new ImageIcon(getClass().getResource(Image_Path)).getImage();
    }

    /**
     * @return the img
     */
    public Image getImg() {
        return img;
    }


    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }
    
    @Override
    protected Object clone() 
    {
        Node Temp = new Node("dot.png");
        Temp.x = this.x;
        Temp.y = this.y;
        return Temp;
    }
   
    @Override
    public boolean equals(Object obj)
    {
        Node temp = (Node) obj;
        if (temp.getX() == this.x && temp.getY() == this.y) {return true;}
        return false;
    }
    
}
