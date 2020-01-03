package com.HalfbeastyTracker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Dimension;

public class CloseButton extends JFrame implements ActionListener {

    public CloseButton() {
        JFrame close = new JFrame("Close HalfbeastyTracker");
        JButton button = new JButton("Close HalfbeastyTracker");
        close.setLayout(new FlowLayout());
        Dimension closeButton = new Dimension(250,25);
        button.setPreferredSize(closeButton);
        close.add(button);
        button.addActionListener(this);
        close.setSize(300,75);
        close.setVisible(true);
        close.setLocationRelativeTo(null);
        close.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String command = actionEvent.getActionCommand();
        if (command.equals("Close HalfbeastyTracker")) {
            System.exit(0);
        }
    }
}
