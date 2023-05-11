package org.trivia.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Trivia implements ActionListener, MouseListener {
    JFrame frame;
    JPanel selections;

    String answer;
    JButton selectionOne, selectionTwo, selectionThree, selectionFour;

    public Trivia() {
        frame = new JFrame("Trivia Master");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        answer = "Selection One";

        selections = new JPanel();
        selections.setLayout(new GridLayout(2, 2));

        selectionOne = new JButton("Selection One");
        selectionOne.setPreferredSize(new Dimension(50, 50));
        selections.add(selectionOne);

        selectionTwo = new JButton("Selection Two");
        selectionTwo.setPreferredSize(new Dimension(50, 50));
        selections.add(selectionTwo);

        selectionThree = new JButton("Selection Three");
        selectionThree.setPreferredSize(new Dimension(50, 50));
        selections.add(selectionThree);

        selectionFour = new JButton("Selection Four");
        selectionFour.setPreferredSize(new Dimension(50, 50));
        selections.add(selectionFour);

        frame.add(selections, BorderLayout.SOUTH);

        frame.add(new JLabel("Question"), BorderLayout.NORTH);

        frame.setSize(500, 200);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == selectionOne) {
            if (answer == "Selection One") {
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect!");
            }
        } else if (ae.getSource() == selectionTwo) {
            if (answer == "Selection Two") {
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect!");
            }
        } else if (ae.getSource() == selectionThree) {
            if (answer == "Selection Three") {
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect!");
            }
        } else if (ae.getSource() == selectionFour) {
            if (answer == "Selection Four") {
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect!");
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
