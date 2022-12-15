import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Note: "implements ActionListener" is a promise to implement "ActionPeformed()" method, so this class can receive word
//     of button presses
public class CollectionDemoFrame extends JFrame implements ActionListener
{

    private CollectionDemoPanel mainPanel;
    private JComboBox commandsComboBox;
    private JButton executeButton;
    private JSpinner intSpinner;
    private HexItemPanel myHexPanel;
    private JButton newHexButton, nullHexButton;

    // these are the text lines for the popup menu.
    private final String[] commands = {"Array: getHexAtIndex",
                                 "Array: setHexAtIndex",
                                 "Array: getNumHexes",
                                "Array: removeAllHexes",
                                "Array: insertHexAtLocation",
                                "Array: removeHexAtLocation",
                                "Array: removeHexAtLocation and shift down",
                                "-------",
                                "ArrayList: getHexAtIndex",
                                "ArrayList: setHexAtIndex",
                                "ArrayList: getNumHexes",
                                "ArrayList: removeAllHexes",
                                "ArrayList: addHexToEnd",
                                "ArrayList: insertHexAtLocation",
                                "ArrayList: removeHexAtLocation",
                                "ArrayList: contains",
                                "ArrayList: indexOf"};

    // a label at the bottom of the screen that displays any exceptions thrown.
    private JLabel exceptionLabel;

    public CollectionDemoFrame()
    {
        super("Collection Demo");
        setSize(800,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buildInterface();
        setVisible(true);
    }

    public void buildInterface()
    {
        // Generate the main panel
        mainPanel = new CollectionDemoPanel();

        // Generate the Controls panel.
        JPanel controlsPanel = new JPanel();
        controlsPanel.setLayout(new FlowLayout());

        commandsComboBox = new JComboBox(commands);
        controlsPanel.add(commandsComboBox);

        executeButton = new JButton("Execute.");
        executeButton.addActionListener(this); // if somebody presses the button, call this.actionPeformed().
        controlsPanel.add(executeButton);

        intSpinner = new JSpinner(new SpinnerNumberModel(0,-1,100,1));
        controlsPanel.add(intSpinner);

        myHexPanel = new HexItemPanel();
        myHexPanel.setMyItem(new HexItem());
        controlsPanel.add(myHexPanel);

        newHexButton = new JButton ("New Hex");
        newHexButton.addActionListener(this); // if somebody presses the button, call this.actionPeformed().
        controlsPanel.add(newHexButton);

        nullHexButton = new JButton ("Null Hex");
        nullHexButton.addActionListener(this); // if somebody presses the button, call this.actionPeformed().
        controlsPanel.add(nullHexButton);

        // Generate the exception Label
        exceptionLabel = new JLabel("");

        // add these generated components to the window.
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(controlsPanel, BorderLayout.NORTH);
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        getContentPane().add(exceptionLabel, BorderLayout.SOUTH);
    }


    /**
     * Invoked when an action occurs.
     * This method automatically is called when a button is pressed,
     * if we told the button to AddActionListener(this).
     * @param e information about the event to be processed, including which button was pressed.
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == executeButton)
        {
            executeCommand(commandsComboBox.getSelectedIndex()); // getSelectedIndex tells us the number of the row of
                                                                 //     the comboxbox that is showing.
        }
        if (e.getSource() == newHexButton)
        {
            myHexPanel.setMyItem(new HexItem());
        }
        if (e.getSource() == nullHexButton)
        {
            myHexPanel.setMyItem(null);
        }
    }

    public void executeCommand(int which)
    {
        exceptionLabel.setText("");
        int num = (Integer)(intSpinner.getValue());
        HexItem hex = myHexPanel.getMyItem();
        int n;
        try // because we might throw an exception doing this....
        {
            switch (which)
            {
                case 0: //Array get hex at index
                    myHexPanel.setMyItem(mainPanel.ArrayGetHexAtIndex(num));
                    break;
                case 1: //Array set hex at index
                    mainPanel.ArraySetHexAtIndex(hex,num);
                    break;
                case 2: //Array getNumHexes
                    n = mainPanel.ArrayGetNumHexes();
                    intSpinner.setValue(n);
                    JOptionPane.showMessageDialog(this, "Method returned "+n, "Array - getNumHexes",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 3: //Array removeAllHexes
                    mainPanel.ArrayRemoveAllHexes();
                    break;
                case 4: //Array insert hex at index
                    mainPanel.ArrayInsertHexAtIndex(hex,num);
                    break;
                case 5: //Array remove hex at index
                    mainPanel.ArrayRemoveHexAtIndex(num);
                    break;
                case 6: //Array remove hex at index and shift
                    mainPanel.ArrayRemoveHexAtIndexAndShift(num);
                    break;
                case 7: //hyphens....

                    break;
                case 8: //ArrayList get hex at index
                    myHexPanel.setMyItem(mainPanel.ALGetHexAtIndex(num));
                    break;
                case 9: //ArrayList set hex at index
                    mainPanel.ALSetHexAtIndex(hex,num);
                    break;
                case 10: //ArrayList get numHexes
                    n = mainPanel.ALGetNumHexes();
                    intSpinner.setValue(n);
                    JOptionPane.showMessageDialog(this,"Method returned "+n,
                            "AL - GetNumHexes",JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 11: //ArrayList removeAllHexes
                    mainPanel.ALRemoveAllHexes();
                    break;
                case 12: //ArrayList addHexToEnd
                    mainPanel.ALAddHexToEnd(hex);
                    break;
                case 13: //ArrayList insertHexAtLocation
                    mainPanel.ALInsertHexAtLocation(hex,num);
                    break;
                case 14: //ArrayList removeHexAtLocation
                    mainPanel.ALRemoveHexAtLocation(num);
                    break;
                case 15: //ArrayList contains
                    JOptionPane.showMessageDialog(this, mainPanel.ALContains(hex), "AL - Contains", JOptionPane.INFORMATION_MESSAGE);

                    break;
                case 16: //ArrayList indexOf
                {
                    int idx = mainPanel.ALIndexOf(hex);
                    intSpinner.setValue(idx);
                    JOptionPane.showMessageDialog(this, "Method returned " + idx,
                            "AL - Index Of", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            // since the above method calls might have changed what
            //   should have appeared on the screen, tell the main
            //   panel that it needs to call paintComponent() at its
            //   next opportunity.
            mainPanel.repaint();
        }
        catch (Exception exp)
        {
            // instead of crashing, put the exception at the bottom of
            // the screen...
            exceptionLabel.setText(exp.toString());
            JOptionPane.showMessageDialog(this,exp.toString(),"Error Returned",JOptionPane.WARNING_MESSAGE);
        }

    }
}
