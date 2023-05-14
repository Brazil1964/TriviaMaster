package org.trivia.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.trivia.questions.QuestionGeneration;

public class Trivia implements ActionListener, MouseListener {
    JFrame frame;
    JPanel selections;

    String answer;
    JButton selectionOne, selectionTwo, selectionThree, selectionFour;

    Map<String, String> triviaQuestions;

    JLabel questionLabel;

    public Trivia() throws ExecutionException, InterruptedException{
        frame = new JFrame("Trivia Master");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        selections = new JPanel();
        selections.setLayout(new GridLayout(2, 2));

        selectionOne = new JButton("Selection One");
        selectionOne.addActionListener(this);
        selectionOne.setPreferredSize(new Dimension(50, 50));

        selectionTwo = new JButton("Selection Two");
        selectionTwo.addActionListener(this);
        selectionTwo.setPreferredSize(new Dimension(50, 50));

        selectionThree = new JButton("Selection Three");
        selectionThree.addActionListener(this);
        selectionThree.setPreferredSize(new Dimension(50, 50));

        selectionFour = new JButton("Selection Four");
        selectionFour.addActionListener(this);
        selectionFour.setPreferredSize(new Dimension(50, 50));

        frame.add(selections, BorderLayout.SOUTH);

        frame.setSize(500, 200);
        frame.setVisible(true);

        triviaQuestions = QuestionGeneration.generateQuestion();

        // Begin at index 8 because I have the "answer: " prefix
        answer = triviaQuestions.get("answer").substring(8);

        questionLabel = new JLabel(triviaQuestions.get("question"));
        frame.add(questionLabel, BorderLayout.NORTH);

        selectionOne.setText(triviaQuestions.get("optionA"));
        selections.add(selectionOne);

        selectionTwo.setText(triviaQuestions.get("optionB"));
        selections.add(selectionTwo);

        selectionThree.setText(triviaQuestions.get("optionC"));
        selections.add(selectionThree);

        selectionFour.setText(triviaQuestions.get("optionD"));
        selections.add(selectionFour);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == selectionOne) {
            if (answer.equals("a")){
                System.out.println("Correct!");
                selectionOne.setBackground(Color.GREEN);
            } else {
                System.out.println("Incorrect!");
                selectionOne.setBackground(Color.RED);
            }
        } else if (ae.getSource() == selectionTwo) {
            if (answer.equals("b")) {
                System.out.println("Correct!");
                selectionTwo.setBackground(Color.GREEN);
            } else {
                System.out.println("Incorrect!");
                selectionTwo.setBackground(Color.RED);
            }
        } else if (ae.getSource() == selectionThree) {
            if (answer.equals("c")) {
                System.out.println("Correct!");
                selectionThree.setBackground(Color.GREEN);
            } else {
                System.out.println("Incorrect!");
                selectionThree.setBackground(Color.RED);
            }
        } else if (ae.getSource() == selectionFour) {
            if (answer.equals("d")) {
                System.out.println("Correct!");
                selectionFour.setBackground(Color.GREEN);
            } else {
                System.out.println("Incorrect!");
                selectionFour.setBackground(Color.RED);
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
