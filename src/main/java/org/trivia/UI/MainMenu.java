package org.trivia.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.ExecutionException;

public class MainMenu implements ActionListener {
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 200;
    JFrame frame;
    JButton typeOne, typeTwo, typeThree, typeFour, typeFive, typeSix;
    public MainMenu() {
        frame = new JFrame("Trivia Selection");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 1));

        typeOne = new JButton("Movies");
        typeOne.addActionListener(this);

        typeTwo = new JButton("Geography");
        typeTwo.addActionListener(this);

        typeThree = new JButton("Politics");
        typeThree.addActionListener(this);

        typeFour = new JButton("Sports");
        typeFour.addActionListener(this);

        typeFive = new JButton("Pop Culture");
        typeFive.addActionListener(this);

        typeSix = new JButton("Programming");
        typeSix.addActionListener(this);

        frame.add(typeOne);
        frame.add(typeTwo);
        frame.add(typeThree);
        frame.add(typeFour);
        frame.add(typeFive);
        frame.add(typeSix);

        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String triviaType = "";

        if (ae.getSource() == typeOne) {
            triviaType = typeOne.getText();
        } else if (ae.getSource() == typeTwo) {
            triviaType = typeTwo.getText();
        } else if (ae.getSource() == typeThree) {
            triviaType = typeThree.getText();
        } else if (ae.getSource() == typeFour) {
            triviaType = typeFour.getText();
        } else if (ae.getSource() == typeFive) {
            triviaType = typeFive.getText();
        } else if (ae.getSource() == typeSix) {
            triviaType = typeSix.getText() + " (Only ask facts questions, no code questions)";
        }

        try {
            new Trivia(triviaType);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        frame.dispose(); // use this to hide the main menu when a question type is generated
    }

    public static void main(String[] args) {
        new MainMenu();
    }
}

