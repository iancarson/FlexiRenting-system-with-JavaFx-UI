package controller;

import javax.swing.*;

public class roomisfullexception extends Exception {
    public roomisfullexception(Throwable message)
    {
        super(message);
    }

    public roomisfullexception() {
        if (getMessage().equals("Room is full")){
            JOptionPane.showMessageDialog(null,"Try another room",null,JOptionPane.ERROR_MESSAGE);
        }
    }
}
