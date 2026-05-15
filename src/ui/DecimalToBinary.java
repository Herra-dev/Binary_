package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.ImageIcon;

import mg.hr.enumeration.FloatPrecision;
import mg.hr.Binary;
import mg.hr.exception.BinaryException;
import herra.exception.UnacceptableParameterException;

public class DecimalToBinary extends abstractBinary implements ActionListener {
    protected JPanel _mainPanel = new JPanel(new GridLayout(4, 1));
    protected JLabel _userInputLabel = new JLabel("Number in decimal");
    protected JLabel _testOutput = new JLabel();
    protected JSpinner _bitSpinner = new JSpinner(new SpinnerNumberModel(4, 1, 256, 1));
    protected FloatPrecision[] _availablePrecision = FloatPrecision.getAvailablePrecision();
    protected JComboBox<FloatPrecision> _precisionBox = new JComboBox<FloatPrecision>(_availablePrecision);
    protected JButton _buttonConvert = new JButton("Convert");
    protected BigDecimal _number = BigDecimal.ZERO;

//====================================================================================================

    public DecimalToBinary() {
        //WINDOW
        this._setDecimalToBinaryProperties();

        //INSTANCE VARIABLE
        this._setInstanceVariableProperties();

        this._addComponentToMainPanel();
    }

//====================================================================================================

    public void _setDecimalToBinaryProperties() {
        this.setTitle("Decimal to Binary Convertor");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(this._mainPanel);
        this.setMinimumSize(new Dimension(400, 700));

        ImageIcon icon = new ImageIcon("/usr/local/bin/assets/binaryconvertor.png");
        this.setIconImage(icon.getImage());
    }

//====================================================================================================

    public void _setInstanceVariableProperties() {
        this._userInputLabel.setFont(new Font("Times New Romance", Font.BOLD, 20));

        this._testOutput.setFont(new Font("Times New Roman", Font.BOLD, 20));

        this._bitSpinner.setEnabled(false);
        this._bitSpinner.setToolTipText("Number of bit");

        this._precisionBox.setEnabled(false);
        this._precisionBox.setEditable(false);

        this._buttonConvert.setFont(new Font("Times New Romance", Font.ITALIC, 25));
        this._buttonConvert.setEnabled(false);

        this._log.setEditable(false);
    }

//====================================================================================================

    public void _addComponentToMainPanel() {
        try {
            this.addInput(1);
            this.setAttributeProperty();
            this.addListenerToButtonAttribute();
        } catch (UnacceptableParameterException e) {
            e.printStackTrace();
        }

        JPanel ioPanel = new JPanel(new GridLayout(3, 2));
        JPanel numberPanel = new JPanel(new GridLayout(4, 3)); // Container of '0'-'9', '.' and '-' 
        JPanel controlPanel = new JPanel(new GridLayout(2, 1)); 
        JPanel inputControlPanel = new JPanel(new GridLayout(2, 3)); // Container of input control such as: button input tester, input clearer, ...
        JPanel outputControlPanel = new JPanel(new GridLayout(2, 3));
        JPanel log = new JPanel(new GridLayout());

    //--------------------------------------------------------------------------------------
        //IO
        ioPanel.add(_userInputLabel);
        ioPanel.add(_inputList.get(_inputFocusOwnerIndex));
        ioPanel.add(_outputLabel);
        ioPanel.add(_outputScroll);

    //--------------------------------------------------------------------------------------
        //COPY PASTE
        ioPanel.add(this._copyToClipboard);
        ioPanel.add(this._pasteFromClipboard);
    //--------------------------------------------------------------------------------------
        //NUMBER
        for(int i = 9; i >= 0; i--) {
            JButton button = new JButton(i+"");
            button.setFont(new Font("Times New Romance", Font.BOLD, 25));
            button.addActionListener(this);
            numberPanel.add(button);
        }

        String[] _str_ = {".", "-"};
        for(String string: _str_) {
            JButton button = new JButton(string);
            button.setFont(new Font("Times New Romance", Font.ITALIC, 25));
            button.addActionListener(this);
            numberPanel.add(button);
        }        

    //--------------------------------------------------------------------------------------
        //INPUT CONTROL
        JButton buttonTestUserInput = new JButton("Test input");
        buttonTestUserInput.setFont(new Font("Times New Romance", Font.ITALIC, 25));
        buttonTestUserInput.addActionListener(new _ButtonInputTesterListener());
        inputControlPanel.add(buttonTestUserInput);

        JButton buttonBackspace = new JButton("<-");
        buttonBackspace.setFont(new Font("Times New Romance", Font.ITALIC, 25));
        buttonBackspace.addActionListener(new _ButtonBackspaceListener());
        inputControlPanel.add(buttonBackspace);


        JButton buttonDelete = new JButton("Del");
        buttonDelete.setFont(new Font("Times New Romance", Font.ITALIC, 25));
        buttonDelete.addActionListener(new _ButtonDeleteListener());
        inputControlPanel.add(buttonDelete);

        JButton buttonClear = new JButton("Clear");
        buttonClear.setFont(new Font("Times New Romance", Font.ITALIC, 25));
        buttonClear.addActionListener(new _ButtonClearListener());
        buttonClear.setForeground(Color.RED);
        inputControlPanel.add(buttonClear);

        controlPanel.add(inputControlPanel);

    //--------------------------------------------------------------------------------------
        //OUTPUT CONTROL
        outputControlPanel.add(_testOutput);
        outputControlPanel.add(_bitSpinner);
        outputControlPanel.add(_precisionBox);

        _buttonConvert.addActionListener(new _ButtonConvertListener());
        outputControlPanel.add(_buttonConvert);

        controlPanel.add(outputControlPanel);

    //--------------------------------------------------------------------------------------
        //LOG
        log.add(_logScroll);
    //--------------------------------------------------------------------------------------
        _mainPanel.add(ioPanel);
        _mainPanel.add(numberPanel);
        _mainPanel.add(controlPanel);
        _mainPanel.add(log);
    }

//====================================================================================================
//====================================================================================================
    //OVERRIDED FUNCTION

    @Override public void actionPerformed(ActionEvent event) {
        if((this._inputList.get(_inputFocusOwnerIndex).getText().contains(".")) && (event.getActionCommand().matches("[.]{1}"))) return; // if input contains already a comma (".") and user enter comma, quit function
        if((this._inputList.get(_inputFocusOwnerIndex).getText().contains("-")) && (event.getActionCommand().matches("[-]{1}"))) return; // if input contains already a minus sign ("-") and user enter comma, quit function
    
        int caretPosition = this._inputList.get(_inputFocusOwnerIndex).getCaretPosition();
        String currentInput = this._inputList.get(_inputFocusOwnerIndex).getText();
        String _output = new String();
        String firstString = new String();
        String lastString = new String();

        firstString = currentInput.substring(0, caretPosition);
        lastString = currentInput.substring(caretPosition, currentInput.length());

        _output += firstString;
        if(event.getActionCommand().matches("[-]{1}") && caretPosition > 0) return; 
        _output += event.getActionCommand();
        _output += lastString;

        if((event.getActionCommand().matches("[0-9]++|[.]{1}|[-]{1}"))) {
            this._inputList.get(_inputFocusOwnerIndex).setText(_output);

            try {
                this._inputList.get(_inputFocusOwnerIndex).moveCaretPosition(caretPosition+1); // Move caret position to its current position + 1
            }catch(IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

//====================================================================================================
//====================================================================================================
    //INTERN CLASS

    class _ButtonInputTesterListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if(_inputList.get(_inputFocusOwnerIndex).getText().isEmpty()) return; // User input is empty -> nothing to test, quit function

            String currentUserInput = _inputList.get(_inputFocusOwnerIndex).getText();
            do {
                currentUserInput = (currentUserInput.replaceAll("[+]{2}|[-]{2}", "+"));                  // ++ or --     --> +
                currentUserInput = (currentUserInput.replaceAll("[+]{1}[-]{1}|[-]{1}[+]{1}", "-"));      // +- or -+     --> -
            }while (currentUserInput.contains("--") || currentUserInput.contains("++") ||
                    currentUserInput.contains("-+") || currentUserInput.contains("+-"));

            _inputList.get(_inputFocusOwnerIndex).setText(currentUserInput);
            if(_inputList.get(_inputFocusOwnerIndex).getText().matches("[+]?+[-]?+[0-9]*+[.]??[0-9]*+") &&
                    !_inputList.get(_inputFocusOwnerIndex).getText().matches("[-]?+") && 
                        !_inputList.get(_inputFocusOwnerIndex).getText().matches("[+]?+") && 
                            !_inputList.get(_inputFocusOwnerIndex).getText().matches("[.]?+")) {     // [+][-][0-9][.][0-9] ---> Valid Input
                _testOutput.setText("Valid Input");
                _testOutput.setForeground(Color.GREEN);
                _buttonConvert.setEnabled(true);
                
                if(_inputList.get(_inputFocusOwnerIndex).getText().contains(".")) {
                    _bitSpinner.setEnabled(false);
                    _precisionBox.setEnabled(true);

                    try {
                        _number = new BigDecimal(_inputList.get(_inputFocusOwnerIndex).getText());
                    }catch(NullPointerException | NumberFormatException e) {
                        e.printStackTrace();
                    }
                } else {
                    _bitSpinner.setEnabled(true);
                    _precisionBox.setEnabled(false);

                    try {
                        _number = new BigDecimal(_inputList.get(_inputFocusOwnerIndex).getText());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            } else {                                                                // Invalid Input
                _testOutput.setText("Invalid Input");
                _testOutput.setForeground(Color.RED);
                
                _bitSpinner.setEnabled(false);
                _precisionBox.setEnabled(false);
                _buttonConvert.setEnabled(false);
            }
        }
    }

//====================================================================================================

    class _ButtonConvertListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            byte[] arrayResult = null;
            try {
                if(_inputList.get(_inputFocusOwnerIndex).getText().contains(".")) 
                    arrayResult = Binary._toBinaryFloat(_number, (FloatPrecision)_precisionBox.getSelectedItem());
                else 
                    arrayResult = Binary.toBinary(_number, (int)_bitSpinner.getValue());

            } catch (BinaryException e) {
                e.printStackTrace();
            }

            _output.setFont(new Font("Arial", 1, 20));
            _output.setText(herra.string.HString.byteArrayToString(arrayResult));
            _buttonConvert.setEnabled(false);
            _bitSpinner.setEnabled(false);
            _precisionBox.setEnabled(false);

            _log.setText(_log.getText() + _number + " = " + _output.getText() + "\n");
        }
    }
}