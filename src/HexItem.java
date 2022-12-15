import java.awt.*;
import java.util.Objects;

public class HexItem
{
    // these variables are "static" which means they are two individual variables shared by all instances of this class.
    //    So if I have 21 HexItems, that means there will be 21 "myLetters" out there, but only one "deltaXs" that all 21
    //    HexItems will have access to.
    private static int[] deltaXs = null;
    private static int[] deltaYs = null;
    private static Font HexItemFont;

    // these variables are public, belongs to the class, and are"final" - that is, a constant. So the first of them
    //    can be referred to within this class as "RADIUS" and outside of this class as "HexItem.RADIUS".
    // Traditionally, constants are in all caps, and the camelCase is eschewed for underscores.
    public static final int RADIUS = 15;
    public static final int FONT_SIZE = 12;


    // Aaaaaand these are just normal class variables....
    private String myLetter;
    private Color myColor;
    private int myCenterX, myCenterY;
    private int[] myCornerXs, myCornerYs;



    public HexItem(String myLetter, Color myColor)
    {
        this();
        this.myLetter = myLetter;
        this.myColor = myColor;
    }

    public HexItem(Color myColor)
    {
        this();
        this.myColor = myColor;
    }

    public HexItem(String myLetter)
    {
        this();
        this.myLetter = myLetter;
    }

    public HexItem()
    {
        setupSingletons();
        // sneaky way of picking a random capitol letter....
        int randNum = (int)(Math.random()*26);
        myLetter = String.valueOf((char)('A'+randNum));
        myColor = new Color((float)(0.5+0.5*Math.random()),
                            (float)(0.5+0.5*Math.random()),
                            (float)( 0.5+0.5*Math.random()));
        myCornerXs = new int[6];
        myCornerYs = new int[6];

    }

    /**
     * updates the location of this hex's center and the vertices
     * of the hex shape to the given (x,y).
     * @param x
     * @param y
     */
    public void moveToLocation(int x, int y)
    {
        myCenterX = x;
        myCenterY = y;
        for (int i=0; i<6; i++)
        {
            myCornerXs[i] = x + deltaXs[i];
            myCornerYs[i] = y + deltaYs[i];
        }
    }

    /**
     * draws this hex centered at the given (x,y) in the given
     * graphics context.
     * @param x
     * @param y
     * @param g
     */
    public void drawSelfAt(int x, int y, Graphics g)
    {
        moveToLocation(x,y);
        drawSelf(g);
    }

    /**
     * does the actual drawing of the hex.
     * @param g
     */
    public void drawSelf(Graphics g)
    {
        // draw the hex (filled with black outline)
        g.setColor(myColor);
        g.fillPolygon(myCornerXs, myCornerYs, 6);
        g.setColor(Color.BLACK);
        g.drawPolygon(myCornerXs, myCornerYs, 6);

        // draw the letter, centered
        g.setFont(HexItemFont);
        int width = g.getFontMetrics().stringWidth(myLetter);
        g.drawString(myLetter, myCenterX-width/2, myCenterY + FONT_SIZE/2);
    }


    /**
     * Calculates how much to offset each vertex of the hexagon from
     * the center when drawing it.
     *
     * We only need to do this once... the first time we make a HexItem,
     *     so if deltaXs isn't the default null value, this method
     *     can just exit.
     */
    public void setupSingletons()
    {

        if (deltaXs == null)
        {
            deltaXs = new int[6];
            deltaYs = new int[6];
            HexItemFont = new Font("Helvetica",Font.BOLD, FONT_SIZE);
            for (int i = 0; i < 6; i++)
            {
                double angle = i * (Math.PI/3);
                deltaXs[i] = (int)(RADIUS * Math.cos(angle));
                deltaYs[i] = (int)(RADIUS * Math.sin(angle));
            }
        }
    }

    public String toString()
    {
        return "["+myLetter+"\t"+myColor.toString()+"]";
    }

    @Override
    // this is an auto-generated (and somewhat complicated) method to determine whether the given "o" is a HexItem
    //    with the same letter and color as this one.
    public boolean equals(Object o)
    {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HexItem hexItem = (HexItem) o;
        return myLetter.equals(hexItem.myLetter) && myColor.equals(hexItem.myColor);
    }

}
