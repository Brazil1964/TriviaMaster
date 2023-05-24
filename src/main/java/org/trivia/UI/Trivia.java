package org.trivia.UI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
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
    JLabel questionLabel, scoreLabel, timeLabel;
    int scoreCounter;
    int highScore = 0;
    String triviaType;
    Timer timer;
    int countdown;

    public Trivia(String triviaType) throws ExecutionException, InterruptedException{
        scoreCounter = 0;
        this.triviaType = triviaType;

        frame = new JFrame("Trivia Master");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Border emptyBorder = new EmptyBorder(10, 10, 10, 10);

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

        frame.setSize(800, 200);
        frame.setVisible(true);

        QuestionGeneration.triviaType = triviaType;
        triviaQuestions = QuestionGeneration.generateQuestion();

        // Begin at index 8 because I have the "answer: " prefix
        answer = triviaQuestions.get("answer").substring(8);

        // pull generated question from QuestionGeneration
        questionLabel = new JLabel(triviaQuestions.get("question"));
        questionLabel.setBorder(emptyBorder);
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
        scoreLabel.setBorder(emptyBorder);
        frame.add(scoreLabel, BorderLayout.CENTER);

        timer = new Timer(10000, this);
        timeLabel = new JLabel();
        timeLabel.setBorder(emptyBorder);
        frame.add(timeLabel, BorderLayout.EAST);
        startTimer();
    }

    public void startTimer() {
        countdown = 10;
        timeLabel.setText("Time: " + countdown);
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (countdown > 0) {
                    countdown--;
                    timeLabel.setText("Time: " + countdown);
                } else {
                    timer.stop();
                    wrongSelection(null);
                    try {
                        generateNewQuestions();
                    } catch (ExecutionException | InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        timer.start();
    }

    public void generateNewQuestions() throws ExecutionException, InterruptedException {
        QuestionGeneration.triviaType = triviaType;
        triviaQuestions = QuestionGeneration.generateQuestion();

        scoreLabel.setText("Score: " + scoreCounter);

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

        startTimer();
    }

    public void correctSelection(JButton selection) {
        selection.setBackground(Color.GREEN);
        timer.stop();
        scoreCounter += 1;
    }

    public void wrongSelection(JButton selection) {
        timer.stop();
        highScore = Math.max(highScore, scoreCounter);
        scoreCounter = 0;
        if (selection != null) {
            selection.setBackground(Color.PINK);
        }
        showMessageDialog(null, "The correct answer is: " + answer + "\n" + "High Score: " + highScore);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == selectionOne) {
            if (answer.equals("a")){
                correctSelection(selectionOne);
            } else {
                wrongSelection(selectionOne);
            }
        } else if (ae.getSource() == selectionTwo) {
            if (answer.equals("b")) {
                correctSelection(selectionTwo);
            } else {
                wrongSelection(selectionTwo);
            }
        } else if (ae.getSource() == selectionThree) {
            if (answer.equals("c")) {
                correctSelection(selectionThree);
            } else {
                wrongSelection(selectionThree);
            }
        } else if (ae.getSource() == selectionFour) {
            if (answer.equals("d")) {
                correctSelection(selectionFour);
            } else {
                wrongSelection(selectionFour);
            }
        }

        try {
            generateNewQuestions();
        } catch (ExecutionException | InterruptedException ex) {
            throw new RuntimeException(ex);
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
