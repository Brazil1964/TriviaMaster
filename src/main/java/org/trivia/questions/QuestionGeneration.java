package org.trivia.questions;

import ai.knowly.langtoch.capability.module.openai.unit.SimpleChatCapabilityUnit;
import ai.knowly.langtoch.prompt.template.PromptTemplate;
import com.google.common.collect.ImmutableList;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class QuestionGeneration {

    public static Map<String, String> generateQuestion() throws ExecutionException, InterruptedException {
        // Reading the key from the environment variable under Resource folder(.env file, OPENAI_API_KEY field)
        SimpleChatCapabilityUnit chatBot = SimpleChatCapabilityUnit.create();

        PromptTemplate promptTemplate =
                PromptTemplate.builder()
                        .setExampleHeader("Here's one example:")
                        .setExamples(
                                ImmutableList.of(
                                        "What is 25% of 1400?\n"
                                                + "a) 700\n"
                                                + "b) 350\n"
                                                + "c) 1050\n"
                                                + "d) 1000\n"
                                                + "answer: b"))
                        .setTemplate(
                                "Can you please generate a trivia question by following the response template:\n"
                                        + "{question}\n"
                                        + "{option A}\n"
                                        + "{option B}\n"
                                        + "{option C}\n"
                                        + "{option D}\n"
                                        + "{answer}")
                        .build();
        String results = chatBot.run(promptTemplate.format());
        System.out.println(results);

        String[] lines = results.split("\n");
        Map<String, String> triviaQuestions = new HashMap<>();

        triviaQuestions.put("question", lines[0]);
        triviaQuestions.put("optionA", lines[1]);
        triviaQuestions.put("optionB", lines[2]);
        triviaQuestions.put("optionC", lines[3]);
        triviaQuestions.put("optionD", lines[4]);
        triviaQuestions.put("answer", lines[5]);

        return triviaQuestions;
    }
}
