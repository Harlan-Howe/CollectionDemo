import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Note: "implements ActionListener" is a promise to implement "ActionPeformed()" method, so this class can receive word
//     of button presses
public class CollectionDemoFrame extends JFrame implements ActionListener
{

    private CollectionDemoPanel mainPanel;
    private JComboBox arrayCommandsComboBox, ALCommandsComboBox;
    private JButton executeArrayButton, executeALButton;
    private JSpinner intSpinner;
    private HexItemPanel myHexPanel;
    private JButton newHexButton, nullHexButton, editHexButton;

    // these are the text lines for the popup menu.
    private final String[] arrayCommands = {"getHexAtIndex",
                                 "setHexAtIndex",
                                 "getNumHexes",
                                "removeAllHexes",
                                "insertHexAtLocation",
                                "removeHexAtLocation",
                                "removeHexAtLocation & shift down"};
    private final String[] arrayListCommands = {"getHexAtIndex",
                                "setHexAtIndex",
                                "getNumHexes",
                                "removeAllHexes",
                                "addHexToEnd",
                                "insertHexAtLocation",
                                "removeHexAtLocation",
                                "contains",
                                "indexOf"};

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

    /**
     * builds the GUI for this window.
     */
    public void buildInterface()
    {
        // Generate the main panel
        mainPanel = new CollectionDemoPanel();

        // Generate the Controls panel.
        Box controlsPanel = Box.createHorizontalBox();
        makeCommandsBox(controlsPanel);
        makeIOBox(controlsPanel);

        // Generate the exception Label
        exceptionLabel = new JLabel("");

        // add these generated components to the window.
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(controlsPanel, BorderLayout.NORTH);
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        getContentPane().add(exceptionLabel, BorderLayout.SOUTH);
    }

    /**
     * builds the top left GUI and adds it to the box displayed in the NORTH of the screen.
     * @param controlsPanel - the panel to which to add this.
     */
    private void makeCommandsBox(Box controlsPanel)
    {
        Box commandsBox = Box.createVerticalBox();

        JPanel arraysCommandPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel arrayListCommandPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        commandsBox.add(arraysCommandPanel);
        arraysCommandPanel.setBorder(new TitledBorder("Arrays"));
        commandsBox.add(arrayListCommandPanel);
        arrayListCommandPanel.setBorder(new TitledBorder("Array Lists"));
        controlsPanel.add(commandsBox);

        arrayCommandsComboBox = new JComboBox(arrayCommands);
        arraysCommandPanel.add(arrayCommandsComboBox);

        executeArrayButton = new JButton("Execute");
        executeArrayButton.addActionListener(this); // if somebody presses the button, call this.actionPeformed().
        arraysCommandPanel.add(executeArrayButton);

        ALCommandsComboBox = new JComboBox(arrayListCommands);
        arrayListCommandPanel.add(ALCommandsComboBox);

        executeALButton = new JButton("Execute");
        executeALButton.addActionListener(this); // if somebody presses the button, call this.actionPeformed().
        arrayListCommandPanel.add(executeALButton);
    }

    /**
     * builds the top right GUI and adds it to the box displayed in the NORTH of the screen.
     * @param controlsPanel - the panel to which to add this.
     */
    private void makeIOBox(Box controlsPanel)
    {
        Box ioBox = Box.createHorizontalBox();
        ioBox.setBorder(new TitledBorder("input/output"));
        controlsPanel.add(ioBox);

        Box spinnerBox = Box.createVerticalBox();
        spinnerBox.add(Box.createVerticalGlue());

        intSpinner = new JSpinner(new SpinnerNumberModel(0,-1,100,1));
        spinnerBox.add(intSpinner);
        spinnerBox.add(Box.createVerticalGlue());
        ioBox.add(spinnerBox);

        ioBox.add(Box.createHorizontalStrut(60));

        Box hexBox = Box.createHorizontalBox();
        ioBox.add(hexBox);

        Box hexPanelBox = Box.createVerticalBox();
        hexPanelBox.add(Box.createVerticalGlue());
        myHexPanel = new HexItemPanel();
        myHexPanel.setMyItem(new HexItem());
        hexPanelBox.add(myHexPanel);
        hexPanelBox.add(Box.createVerticalGlue());
        hexBox.add(hexPanelBox);

        Box hexButtonsPanel = Box.createVerticalBox();
        newHexButton = new JButton ("\uD83C\uDD95");
        newHexButton.addActionListener(this); // if somebody presses the button, call this.actionPeformed().
        hexButtonsPanel.add(newHexButton);

        nullHexButton = new JButton ("∅");
        nullHexButton.addActionListener(this); // if somebody presses the button, call this.actionPeformed().
        hexButtonsPanel.add(nullHexButton);

        editHexButton = new JButton("✏");
        editHexButton.addActionListener(this);
//        hexButtonsPanel.add(editHexButton);

        hexBox.add(hexButtonsPanel);
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
        if (e.getSource() == executeArrayButton)
        {
            executeArrayCommand(arrayCommandsComboBox.getSelectedIndex()); // getSelectedIndex tells us the number of the row of
                                                                 //     the comboxbox that is showing.
        }
        if (e.getSource() == executeALButton)
        {
            executeALCommand(ALCommandsComboBox.getSelectedIndex()); // getSelectedIndex tells us the number of the row of
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
        if (e.getSource() == editHexButton)
            executeEditHex();
    }

    /**
     * helper method that calls the method in CollectionDemoPanel corresponding to the item selected in the array
     * commands combo box.
     * @param which - the number of the selected item in the combo box.
     */
    public void executeArrayCommand(int which)
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

    /**
     * helper method that calls the method in CollectionDemoPanel corresponding to the item selected in the AL commands
     * combo box.
     * @param which - the number of the selected item in the combo box.
     */
    public void executeALCommand(int which)
    {
        exceptionLabel.setText("");
        int num = (Integer)(intSpinner.getValue());
        HexItem hex = myHexPanel.getMyItem();
        int n;
        try // because we might throw an exception doing this....
        {
            switch (which)
            {
                case 0: //ArrayList get hex at index
                    myHexPanel.setMyItem(mainPanel.ALGetHexAtIndex(num));
                    break;
                case 1: //ArrayList set hex at index
                    mainPanel.ALSetHexAtIndex(hex,num);
                    break;
                case 2: //ArrayList get numHexes
                    n = mainPanel.ALGetNumHexes();
                    intSpinner.setValue(n);
                    JOptionPane.showMessageDialog(this,"Method returned "+n,
                            "AL - GetNumHexes",JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 3: //ArrayList removeAllHexes
                    mainPanel.ALRemoveAllHexes();
                    break;
                case 4: //ArrayList addHexToEnd
                    mainPanel.ALAddHexToEnd(hex);
                    break;
                case 5: //ArrayList insertHexAtLocation
                    mainPanel.ALInsertHexAtLocation(hex,num);
                    break;
                case 6: //ArrayList removeHexAtLocation
                    mainPanel.ALRemoveHexAtLocation(num);
                    break;
                case 7: //ArrayList contains
                    JOptionPane.showMessageDialog(this, mainPanel.ALContains(hex), "AL - Contains", JOptionPane.INFORMATION_MESSAGE);

                    break;
                case 8: //ArrayList indexOf
                    int idx = mainPanel.ALIndexOf(hex);
                    intSpinner.setValue(idx);
                    JOptionPane.showMessageDialog(this, "Method returned " + idx,
                            "AL - Index Of", JOptionPane.INFORMATION_MESSAGE);
                    break;
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

    /**
     * display a dialog box to allow the user to edit the letter and color of the current hex item.
     */
    public void executeEditHex()
    {
        if (myHexPanel.getMyItem() == null)
            return;
        JTextField letterField = new JTextField();
        JColorChooser colorChooser = new JColorChooser();
        letterField.setText(myHexPanel.getMyItem().getMyLetter());
        colorChooser.setColor(myHexPanel.getMyItem().getMyColor());
        JPanel dialogPane = new JPanel();
        dialogPane.add(new JLabel("Letter:"));
        dialogPane.add(letterField);
        dialogPane.add(colorChooser);

        int result = JOptionPane.showConfirmDialog(this, dialogPane );
        if (JOptionPane.OK_OPTION == result)
        {
            myHexPanel.getMyItem().setMyColor(colorChooser.getColor());
            if (!letterField.getText().isEmpty())
                myHexPanel.getMyItem().setMyLetter(letterField.getText().toUpperCase().substring(0,1));
        }
        repaint();
    }
}
