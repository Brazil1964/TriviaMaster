package org.trivia;

import org.trivia.UI.Trivia;
import org.trivia.questions.QuestionGeneration;

import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Trivia t = new Trivia();
        System.out.print(QuestionGeneration.generateQuestion());
    }
}