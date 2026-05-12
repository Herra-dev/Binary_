package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import herra.exception.UnacceptableParameterException;
import mg.hr.BinaryMath;
import mg.hr.exception.NotABinaryNumberException;

public class BinaryCalculator extends abstractBinary implements ActionListener {
    protected JPanel _mainPanel = new JPanel(new GridLayout(3, 1));
    protected JPanel _inputsPanel = new JPanel(new GridLayout(3, 2));
    protected String[] _availableSign = {"+", "-", "*", "/"};
    protected JComboBox<String> _sign = new JComboBox<String>(_availableSign);
    protected JLabel _signLabel = new JLabel("Sign");
    protected JButton _buttonEqual = new JButton("=");
    protected JSpinner _bitNumber = new JSpinner(new SpinnerNumberModel(4, 1, 255, 1));
    protected int _inputIndexFocusOwner = 0;
    protected String result = new String();

    public BinaryCalculator() {
        this._setBinaryCalculatorProperties();
        this._setInstanceVariableProperties();
        this._addComponentToMainPanel();
    }

//====================================================================================================

    public void _setBinaryCalculatorProperties() {
        this.setTitle("Binary Calculator");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(this._mainPanel);
        this.setMinimumSize(new Dimension(400, 700));
        
        ImageIcon icon = new ImageIcon("../iconCalculator.png");
        this.setIconImage(icon.getImage());
        
    }

//====================================================================================================

    public void _setInstanceVariableProperties() {
        this._sign.setEditable(false);

        this._buttonEqual.setFont(new Font("Times New Romance", Font.BOLD, 40));
        this._buttonEqual.addActionListener(new _ButtonEqualListener());

        this._bitNumber.setFont(new Font("Times New Romance", Font.BOLD, 30));
        this._bitNumber.setToolTipText("Number of bit to represent the output");
    }

//====================================================================================================

    public void _addComponentToMainPanel() {
        try {
            this.addInput(2);
            this.setAttributeProperty();
            this.addListenerToButtonAttribute();
        } catch (UnacceptableParameterException e) {
            e.printStackTrace();
        }

        JPanel ioPanel = new JPanel(new GridLayout(3, 1));
        JPanel mainControlPanel = new JPanel(new GridLayout(3, 1));
        JPanel numberControl = new JPanel(new GridLayout(1, 2));
        JPanel inputControl = new JPanel(new GridLayout(1, 3));
        JPanel outputControl = new JPanel(new GridLayout(1, 1));

    //--------------------------------------------------------------------------------------
        //IO
        this._inputsPanel.add(new JLabel("First number"));
        this._inputsPanel.add(this._inputList.get(0));
        this._inputsPanel.add(this._sign);
        this._inputsPanel.add(this._signLabel);
        this._inputsPanel.add(new JLabel("Second number"));
        this._inputsPanel.add(this._inputList.get(1));

        ioPanel.add(_inputsPanel);
        ioPanel.add(_outputScroll);

    //--------------------------------------------------------------------------------------
        //COPY PASTE
        JPanel copyPastePanel = new JPanel(new GridLayout(1, 2));
        copyPastePanel.add(this._copyToClipboard);
        copyPastePanel.add(this._pasteFromClipboard);

        ioPanel.add(copyPastePanel);
    //--------------------------------------------------------------------------------------
        //CONTROL
            /*************************/ // NUMBER CONTROL
        for(int i = 0; i < 2; i++) {
            JButton button = new JButton(i+"");
            button.addActionListener(this);
            button.setFont(new Font("Times New Romance", Font.BOLD, 30));
            numberControl.add(button);
        }

            /*************************/ // INPUT CONTROL
        JButton buttonBackSpace = new JButton("<-");
        buttonBackSpace.setFont(new Font("Times New Romance", Font.BOLD, 30));
        buttonBackSpace.addActionListener(new _ButtonBackspaceListener());
        inputControl.add(buttonBackSpace);
        
        JButton buttonDelete = new JButton("Del");
        buttonDelete.setFont(new Font("Times New Romance", Font.BOLD, 30));
        buttonDelete.addActionListener(new _ButtonDeleteListener());
        inputControl.add(buttonDelete);

        JButton buttonClear = new JButton("Clear");
        buttonClear.setFont(new Font("Times New Romance", Font.BOLD, 30));
        buttonClear.addActionListener(new _ButtonClearListener());
        inputControl.add(buttonClear);

            /*************************/ //OUTPUT CONTROL
        outputControl.add(_buttonEqual);

        mainControlPanel.add(numberControl);
        mainControlPanel.add(inputControl);
        mainControlPanel.add(outputControl);
    //--------------------------------------------------------------------------------------
        
        this._mainPanel.add(ioPanel);
        this._mainPanel.add(mainControlPanel);
        this._mainPanel.add(_logScroll);
    }

//====================================================================================================

    protected boolean _testInput() {
        if(!(_inputList.get(0).getText().matches("[0-1]++")) || !(_inputList.get(1).getText().matches("[0-1]++"))) {
            this._output.setText("Invalid input");
            this._output.setForeground(Color.RED);
            return false;
        } else {
            this._output.setText(new String());
            this._output.setForeground(Color.BLACK);
            return true;
        }
    }

//====================================================================================================

    @Override public void actionPerformed(ActionEvent event) {
        
        String currentInput = this._inputList.get(_inputFocusOwnerIndex).getText();
        int caretPosition = this._inputList.get(_inputFocusOwnerIndex).getCaretPosition();
        
        String _output = new String();
        String firstString = new String();
        String lastString = new String();

        firstString = currentInput.substring(0, caretPosition);
        lastString = currentInput.substring(caretPosition, currentInput.length());

        _output += firstString;
        _output += event.getActionCommand();
        _output += lastString;

        if((event.getActionCommand().matches("[0-1]++"))) {
            this._inputList.get(_inputFocusOwnerIndex).setText(_output);

            try {
                this._inputList.get(_inputFocusOwnerIndex).moveCaretPosition(caretPosition+1); // Move caret position to its current position + 1
            }catch(IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

//====================================================================================================
    
    class _ButtonEqualListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if(!_testInput()) return;

            byte[] firstNumber = new byte[_inputList.get(0).getText().length()];
            byte[] secondNumber = new byte[_inputList.get(1).getText().length()];

            try {
                firstNumber = herra.string.HString.stringToByteArray(_inputList.get(0).getText());
                secondNumber = herra.string.HString.stringToByteArray(_inputList.get(1).getText());

            } catch (UnacceptableParameterException e) {
                e.printStackTrace();
            }

            String choosedSign = _sign.getSelectedItem().toString();
            int bitNumber = (firstNumber.length > secondNumber.length) ? firstNumber.length : secondNumber.length;
            
            try {
                switch (choosedSign) {
                    case "+": result = herra.string.HString.byteArrayToString(BinaryMath._addBinary(firstNumber, secondNumber, bitNumber)); break;
                    case "-": result = herra.string.HString.byteArrayToString(BinaryMath._subtractBinary(firstNumber, secondNumber, bitNumber)); break;
                    case "*": result = herra.string.HString.byteArrayToString(BinaryMath._multiplyBinary(firstNumber, secondNumber, bitNumber)); break;
                    case "/": result = "Division: coming soon";
                    default: break;
                }
            } catch (NotABinaryNumberException e) {
                e.printStackTrace();
            }

            _log.setText(_log.getText() + _inputList.get(0).getText() + " " + _sign.getSelectedItem().toString() + " " + _inputList.get(1).getText() + " = " + result + "\n");
            _output.setText(result);

        }
    }

}