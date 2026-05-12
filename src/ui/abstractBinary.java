package ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import herra.exception.UnacceptableParameterException;

public class abstractBinary extends JFrame implements FocusListener {
    protected LinkedList<JTextField> _inputList = new LinkedList<JTextField>();
    protected int _inputFocusOwnerIndex = 0;
    protected JLabel _outputLabel = new JLabel("Output");
    protected JTextField _output = new JTextField();
    protected JScrollPane _outputScroll = new JScrollPane(_output);
    protected JTextArea _log = new JTextArea();
    protected JScrollPane _logScroll = new JScrollPane(_log);
    protected JButton _copyToClipboard = new JButton("Copy");
    protected JButton _pasteFromClipboard = new JButton("Paste");

//====================================================================================================

    @Override public void focusGained(FocusEvent event) {
        int _inputSize_ = this._inputList.size();

        for(int i = 0; i < _inputSize_; i++){
            if(_inputList.get(i).isFocusOwner()) {
                this._inputFocusOwnerIndex = i;
                return;
            }
        }        
    }

//====================================================================================================

    @Override public void focusLost(FocusEvent event) { }

//====================================================================================================

    //*******************************************/ ACCESSOR
    public LinkedList<JTextField> getInputList()                    { return this._inputList; }
    public int getInputFocusOwnerIndex()                            { return this._inputFocusOwnerIndex; }
    public JTextField getOutput()                                   { return this._output; }

//====================================================================================================

    public void addInput(int nbr) throws UnacceptableParameterException{
        if(nbr < 0) throw new UnacceptableParameterException("Parameter nbr must be a positive value");

        for(int i = 0; i < nbr; i++) {
            this._inputList.add(new JTextField());
        }
    }

//====================================================================================================

    public void addListenerToButtonAttribute() {
        this._copyToClipboard.addActionListener(new _ButtonCopyListener());
        this._pasteFromClipboard.addActionListener(new _ButtonPasteListener());
    }

//====================================================================================================

    public void setAttributeProperty() {
        for(JTextField jtf: _inputList) {
            jtf.setHorizontalAlignment(JTextField.RIGHT);
            jtf.setFocusable(true);
            jtf.addFocusListener(this);
            jtf.setFont(new Font("Times New Romance", Font.BOLD, 25));
        }

        this._outputLabel.setFont(new Font("Times New Romance", Font.BOLD, 20));

        this._output.setHorizontalAlignment(JTextField.RIGHT);
        this._output.setFont(new Font("Times New Romance", Font.BOLD, 35));
        this._output.setEditable(false);

        this._log.setFont(new Font("Times New Romance", Font.BOLD, 15));

        this._copyToClipboard.setFont(new Font("Times New Romance", Font.BOLD, 15));
        this._copyToClipboard.setToolTipText("Copy current output to the system clipboad");

        this._pasteFromClipboard.setFont(new Font("Times New Romance", Font.BOLD, 15));
        this._pasteFromClipboard.setToolTipText("paste head of the clipboad stack to the input");
    }

//====================================================================================================

    public String removeSelection(String str, int selectionStart, int selectionEnd) {
        String output = new String();
        for(int i = 0; i < selectionStart; i++)
            output += str.charAt(i);
        for(int i = selectionEnd; i < str.length(); i++)
            output += str.charAt(i);

        return output;
    }

//====================================================================================================

    class _ButtonBackspaceListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            String currentInput = _inputList.get(_inputFocusOwnerIndex).getText();
            String output = new String();

            int caretPosition = _inputList.get(_inputFocusOwnerIndex).getCaretPosition();

            if(caretPosition <= 0) return; 

            String firstString = currentInput.substring(0, caretPosition-1);
            String lastString = currentInput.substring(caretPosition, currentInput.length());
            output = firstString + lastString;
            _inputList.get(_inputFocusOwnerIndex).setText(output);

            try {
                _inputList.get(_inputFocusOwnerIndex).moveCaretPosition(caretPosition-1); // Move caret position to its current position - 1
            }catch(IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

//====================================================================================================

    class _ButtonDeleteListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {            
            String output = new String();
            String currentInput = _inputList.get(_inputFocusOwnerIndex).getText();

            String firstString = new String();
            String lastString = new String();
            int caretPosition = _inputList.get(_inputFocusOwnerIndex).getCaretPosition();
            
            if(caretPosition >= currentInput.length()) return; // If caret is placed in the end of the text field, quit function
            firstString = currentInput.substring(0, caretPosition);
            lastString = currentInput.substring(caretPosition+1, currentInput.length());
            output = firstString + lastString;
            _inputList.get(_inputFocusOwnerIndex).setText(output);

            try {
                _inputList.get(_inputFocusOwnerIndex).moveCaretPosition(caretPosition); // Do not move caret position
            }catch(IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

//====================================================================================================

    class _ButtonClearListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            _inputList.get(_inputFocusOwnerIndex).setText(new String());
        }
    }

//====================================================================================================

    class _ButtonCopyListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if(_output.getText().isEmpty()) return;
            _output.selectAll();
            _output.copy();
            _log.setText(_log.getText() + "Output successfully copied to clipboard\n");
        }
    }

//====================================================================================================

    class _ButtonPasteListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            _inputList.get(_inputFocusOwnerIndex).paste();
        }
    }

}