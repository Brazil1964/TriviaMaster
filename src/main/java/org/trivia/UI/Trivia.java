package org.trivia.UI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.trivia.questions.QuestionGeneration;

import static javax.swing.JOptionPane.showMessageDialog;

public class Trivia implements ActionListener, MouseListener {
    private static final Logger LOGGER = Logger.getLogger(Trivia.class.getName());
    private static final int TIMER_DELAY = 1000;
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 500;
    private static final int BUTTON_PREFERRED_SIZE = 50;
    private static final int COUNTDOWN_SECONDS = 15;

    private JFrame frame;
    private JPanel selections;
    private String answer;
    private JButton optionButtonOne, optionButtonTwo, optionButtonFour, optionButtonFive;
    private Map<String, String> triviaQuestions;
    private JLabel questionLabel, scoreLabel, timeLabel;
    private int scoreCounter;
    private int highScore = 0;
    private String triviaType;
    private Timer timer;
    private int countdown;

    public Trivia(String triviaType) throws ExecutionException, InterruptedException {
        scoreCounter = 0;
        this.triviaType = triviaType;

        setupFrame();
        setupButtons();
        setupTriviaQuestions();
        setupLabels();
        startTimer();
    }

    private void setupFrame() {
        frame = new JFrame("Trivia Master");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setVisible(true);
    }

    private void setupButtons() {
        Border emptyBorder = new EmptyBorder(10, 10, 10, 10);

        selections = new JPanel();
        selections.setLayout(new GridLayout(2, 2));

        optionButtonOne = createButton("Selection One");
        optionButtonTwo = createButton("Selection Two");
        optionButtonFour = createButton("Selection Three");
        optionButtonFive = createButton("Selection Four");

        frame.add(selections, BorderLayout.SOUTH);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.addActionListener(this);
        button.setPreferredSize(new Dimension(BUTTON_PREFERRED_SIZE, BUTTON_PREFERRED_SIZE));
        selections.add(button);
        return button;
    }

    private void setupTriviaQuestions() throws ExecutionException, InterruptedException {
        QuestionGeneration.triviaType = triviaType;
        triviaQuestions = QuestionGeneration.generateQuestion();

        // Begin at index 8 because I have the "answer: " prefix
        answer = triviaQuestions.get("answer").substring(8);
    }

    private void setupLabels() {
        Border emptyBorder = new EmptyBorder(10, 10, 10, 10);

        // pull generated question from QuestionGeneration
        questionLabel = new JLabel(triviaQuestions.get("question"));
        questionLabel.setBorder(emptyBorder);
        frame.add(questionLabel, BorderLayout.NORTH);

        updateButtonLabels();

        scoreLabel = new JLabel("Score: " + scoreCounter);
        scoreLabel.setBorder(emptyBorder);
        frame.add(scoreLabel, BorderLayout.CENTER);

        timeLabel = new JLabel();
        timeLabel.setBorder(emptyBorder);
        frame.add(timeLabel, BorderLayout.EAST);
    }

    public void startTimer() {
        countdown = COUNTDOWN_SECONDS;
        timeLabel.setText("Time: " + countdown);
        timer = new Timer(TIMER_DELAY, new ActionListener() {
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
                        LOGGER.log(Level.SEVERE, "Failed to generate new questions", ex);
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

        resetButtonBackgrounds();

        updateButtonLabels();

        answer = triviaQuestions.get("answer").substring(8);

        questionLabel.setText(triviaQuestions.get("question"));

        startTimer();
    }

    private void resetButtonBackgrounds() {
        optionButtonOne.setBackground(Color.WHITE);
        optionButtonTwo.setBackground(Color.WHITE);
        optionButtonFour.setBackground(Color.WHITE);
        optionButtonFive.setBackground(Color.WHITE);
    }

    private void updateButtonLabels() {
        optionButtonOne.setText(triviaQuestions.get("optionA"));
        optionButtonTwo.setText(triviaQuestions.get("optionB"));
        optionButtonFour.setText(triviaQuestions.get("optionC"));
        optionButtonFive.setText(triviaQuestions.get("optionD"));
    }

    public void correctSelection(JButton selection) {
//        selection.setBackground(Color.GREEN); // ISSUE: Color not updating
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
        if (ae.getSource() == optionButtonOne) {
            if (answer.equals("a")){
                correctSelection(optionButtonOne);
            } else {
                wrongSelection(optionButtonOne);
            }
        } else if (ae.getSource() == optionButtonTwo) {
            if (answer.equals("b")) {
                correctSelection(optionButtonTwo);
            } else {
                wrongSelection(optionButtonTwo);
            }
        } else if (ae.getSource() == optionButtonFour) {
            if (answer.equals("c")) {
                correctSelection(optionButtonFour);
            } else {
                wrongSelection(optionButtonFour);
            }
        } else if (ae.getSource() == optionButtonFive) {
            if (answer.equals("d")) {
                correctSelection(optionButtonFive);
            } else {
                wrongSelection(optionButtonFive);
            }
        }

        try {
            generateNewQuestions();
        } catch (ExecutionException | InterruptedException ex) {
            LOGGER.log(Level.SEVERE, "Failed to generate new questions", ex);
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
