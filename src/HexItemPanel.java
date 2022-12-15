import javax.swing.*;
import java.awt.*;

/**
 * a small JPanel that holds and displays a single HexItem.
 * (Used in the control panel at the top of the window.)
 */
public class HexItemPanel extends JPanel
{
    private HexItem myItem;
    private Font myFont;

    public HexItemPanel()
    {
        super();
        setPreferredSize(new Dimension(2*HexItem.RADIUS+5, 2*HexItem.RADIUS+5));
        myFont = new Font("Courier",Font.PLAIN, 10);
    }

    public HexItem getMyItem()
    {
        return myItem;
    }

    public void setMyItem(HexItem myItem)
    {
        this.myItem = myItem;
        repaint();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        // draw outline box
        g.setColor(Color.BLACK);
        g.drawRect(0,0,getWidth()-1, getHeight()-1);
        // draw null or the hexItem
        if (myItem == null)
        {
            g.setFont(myFont);
            int w = g.getFontMetrics().stringWidth("null"); // width of the string "null" in current font
            g.drawString("null", getWidth()/2-w/2, getHeight()/2+5);  // 5 is half the fontsize.
        }
        else
            myItem.drawSelfAt(getBounds().width/2, getBounds().height/2,g);
    }

    public String toString()
    {
        if (myItem == null)
            return ("null");
        return myItem.toString();
    }
}
