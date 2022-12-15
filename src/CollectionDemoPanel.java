import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CollectionDemoPanel extends JPanel
{
    private Font indexFont;
    private Font titleFont;
    private HexItem[] arrayOfHexes;
    private ArrayList<HexItem> arrayListOfHexes;

    public CollectionDemoPanel()
    {
        super();
        indexFont = new Font("Courier",Font.PLAIN, 10);
        titleFont = new Font("Serif",Font.BOLD, 18);
        arrayOfHexes = new HexItem[25];
        arrayListOfHexes = new ArrayList<HexItem>();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        // ============================================= ARRAY
        g.setFont(titleFont);
        g.drawString("Array",20,50);
        int w = g.getFontMetrics().stringWidth("null");

        int numSpacesInArray = 25;
        // ---------- draw the numbers
        for (int i=0; i<numSpacesInArray; i++)
        {
            g.setFont(indexFont);
            int x = 20 + 30 * i - g.getFontMetrics().stringWidth("" + i) / 2;
            g.drawString("" + i, x, 75);
        }
        // ---------- draw the hexes
        for (int i=0; i<numSpacesInArray; i++)
        {
            if (arrayOfHexes[i] != null)
                arrayOfHexes[i].drawSelfAt(20+30*i, 100, g);
            else
            {
                g.drawString("null",20+30*i - w/2, 105);
            }
        }
        // ============================================ ARRAY LIST
        g.setFont(titleFont);
        g.drawString("ArrayList",20,150);

        //TODO #1: fix the next line so that it actually asks the array how many spaces it has. (Yes, this should be
        // 25, but actually ask for it from the arrayOfHexes.) Then remove the warning comment at the end of the line.
        int numItemsInArrayList = 0; // currently, this is wrong.

        // ----------- draw the numbers
        for (int i=0; i<numItemsInArrayList; i++)
        {
            g.setFont(indexFont);
            int x = 20 + 30 * (i%25) - g.getFontMetrics().stringWidth("" + i) / 2;
            int y = 175 + 50 * (i/25);
            g.drawString("" + i, x, y);
        }
        // ----------- draw the Hexes
        for (int i=0; i<numItemsInArrayList; i++)
        {
            // TODO #2: draw the hexItem at index i at location (20+30*(i%25), 200+50*(i/25)) if it is non-null. If it
            //           _is_ null, write the word "null" at (20+30*(i%25) - w/2, 200+50*(i / 25))
            // hint: borrow heavily from the corresponding section above,
            //       initially line 40.
        }

    }

    //-----------------------------------------  Array Methods

    /**
     * gets the HexItem stored in the array at the given index
     * @param index - where to look
     * @return - the hex in the array, or null
     */
    public HexItem ArrayGetHexAtIndex(int index)
    {
        //TODO: you write this!
        return null; // temporary code for stub function
    }

    /**
     * replaces the HexItem in the array at index with the given HexItem
     * @param h - the hexItem to put into the array
     * @param index - where to put it
     */
    public void ArraySetHexAtIndex(HexItem h, int index)
    {
        //TODO: you write this!

    }

    /**
     * gets the number of spaces for hexes in the Array. (Note: this
     * includes null values - I'm not asking for the number of filled
     * in spaces.)
     * @return - the number of spaces in the Array
     */
    public int ArrayGetNumHexes()
    {
        //TODO: you write this!

        return 0;
    }

    /**
     * empties out all of the hexes in the array, so it is filled with
     * null values.
     */
    public void ArrayRemoveAllHexes()
    {
        //TODO: you write this!

    }

    /**
     * shifts all hexes starting at index forward one value
     * (potentially losing the last item off the list) and puts the
     * given HexItem at the given location.
     * @param h - the HexItem to insert
     * @param index - where to insert it.
     */
    public void ArrayInsertHexAtIndex(HexItem h, int index)
    {
        //TODO: you write this!

    }

    /**
     * takes out the HexItem at index and replaces it with null.
     * @param index - which item to remove
     */
    public void ArrayRemoveHexAtIndex(int index)
    {
        //TODO: you write this!

    }

    /**
     * takes out the HexItem at index and shifts all subsequent
     * items in the array back, putting null at the end.
     * @param index - which item to remove
     */
    public void ArrayRemoveHexAtIndexAndShift(int index)
    {
        //TODO: you write this!

    }
    //------------------------------------- ArrayList methods

    /**
     * gets the HexItem stored in the ArrayList at the given index
     * @param index - where to look
     * @return - the hex in the ArrayList, or null (?)
     */
    public HexItem ALGetHexAtIndex(int index)
    {
        //TODO: you write this!

        return null; // temp code for stub function
    }

    /**
     * replaces the HexItem in the ArrayList at index with the given HexItem
     * @param h - the hexItem to put into the ArrayList
     * @param index - where to put it
     */
    public void ALSetHexAtIndex(HexItem h, int index)
    {
        //TODO: you write this!

    }

    /**
     * gets the capacity of the ArrayList
     * @return - how many spaces are in the ArrayList
     */
    public int ALGetNumHexes()
    {
        //TODO: you write this!

        return 0; // temp code for stub function
    }

    /**
     * removes all the HexItems from this ArrayList.
     * Hint: you could do this with a loop, but there _is_ a special
     * one-line command for this.
     */
    public  void ALRemoveAllHexes()
    {
        //TODO: you write this!

    }

    /**
     * adds the given HexItem to the end of the ArrayList.
     * @param h - the HexItem to add
     */
    public void ALAddHexToEnd(HexItem h)
    {
        //TODO: you write this!

    }

    /**
     * Shifts the HexItems in the ArrayList at the given index and
     * all after it forward, and sets the space at the given index
     * to the given HexItem.
     * @param h - the HexItem to insert
     * @param index - the location at which to insert it
     */
    public void ALInsertHexAtLocation(HexItem h, int index)
    {
        //TODO: you write this!

    }

    /**
     * removes the HexItem at the given index from the ArrayList
     * and shifts all subsequent HexItems back one step. The
     * ArrayList should now be shorter.
     * @param index - the item to remove
     */
    public void ALRemoveHexAtLocation(int index)
    {
        //TODO: you write this!
    }

    /**
     * returns whether or not the give hexItem is stored in this ArrayList.
     * Note: you _could_ do this with a loop, but there is a built-in, one-line way.
     * @param h - the HexItem to search for
     * @return whether this HexItem is in the ArrayList
     */
    public boolean ALContains(HexItem h)
    {
        //TODO: you write this!
        return false; // temp code for stub function
    }

    /**
     * returns the index of the first instance of this hexItem in the ArrayList, or -1 if it is not in the ArrayList.
     * Note: you _could_ do this with a loop, but there is a built-in, one-line way.
     * @param h - the HexItem to search for
     * @return the index of the first example of HexItem, or -1 if it is not there at all.
     */
    public int ALIndexOf(HexItem h)
    {
        //TODO: you write this!
        return -1; // temp code for stub function
    }

}
