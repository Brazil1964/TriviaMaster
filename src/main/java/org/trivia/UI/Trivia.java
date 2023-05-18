package org.trivia.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import static javax.swing.JOptionPane.showMessageDialog;

import org.trivia.questions.QuestionGeneration;

public class Trivia implements ActionListener, MouseListener {
    JFrame frame;
    JPanel selections;
    String answer;
    JButton selectionOne, selectionTwo, selectionThree, selectionFour;
    Map<String, String> triviaQuestions;
    JLabel questionLabel, scoreLabel, highScoreLabel;
    int scoreCounter;
    int highScore = 0;
    String triviaType;
    public Trivia(String triviaType) throws ExecutionException, InterruptedException{
        scoreCounter = 0;
        this.triviaType = triviaType;

        frame = new JFrame("Trivia Master");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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

        QuestionGeneration.triviaType = triviaType;
        triviaQuestions = QuestionGeneration.generateQuestion();

        // Begin at index 8 because I have the "answer: " prefix
        answer = triviaQuestions.get("answer").substring(8);

        // pull generated question from QuestionGeneration
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

        scoreLabel = new JLabel("Score: " + scoreCounter);
        frame.add(scoreLabel, BorderLayout.CENTER);

        highScoreLabel = new JLabel("High Score: " + highScore);
        frame.add(highScoreLabel, BorderLayout.CENTER);
    }

    public void generateNewQuestions() throws ExecutionException, InterruptedException {
        QuestionGeneration.triviaType = triviaType;
        triviaQuestions = QuestionGeneration.generateQuestion();

        scoreLabel.setText("Score: " + scoreCounter);
        highScoreLabel.setText("High Score: " + highScore);

        selectionOne.setBackground(Color.WHITE);
        selectionTwo.setBackground(Color.WHITE);
        selectionThree.setBackground(Color.WHITE);
        selectionFour.setBackground(Color.WHITE);

        selectionOne.setText(triviaQuestions.get("optionA"));

        selectionTwo.setText(triviaQuestions.get("optionB"));

        selectionThree.setText(triviaQuestions.get("optionC"));

        selectionFour.setText(triviaQuestions.get("optionD"));
        answer = triviaQuestions.get("answer").substring(8);

        questionLabel.setText(triviaQuestions.get("question"));
    }

    public void correctSelection() {
        highScore = Math.max(highScore, scoreCounter);
        scoreCounter += 10;
    }

    public void wrongSelection(JButton selection) {
        highScore = Math.max(highScore, scoreCounter);
        scoreCounter = 0;
        selection.setBackground(Color.RED);
        showMessageDialog(null, "The correct answer is: " + answer + "\n" + "High Score: " + highScore);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == selectionOne) {
            if (answer.equals("a")){
                correctSelection();
            } else {
                wrongSelection(selectionOne);
            }
        } else if (ae.getSource() == selectionTwo) {
            if (answer.equals("b")) {
                correctSelection();
            } else {
                wrongSelection(selectionTwo);
            }
        } else if (ae.getSource() == selectionThree) {
            if (answer.equals("c")) {
                correctSelection();
            } else {
                wrongSelection(selectionThree);
            }
        } else if (ae.getSource() == selectionFour) {
            if (answer.equals("d")) {
                correctSelection();
            } else {
                wrongSelection(selectionFour);
            }
        }
        try {
            generateNewQuestions();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
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
