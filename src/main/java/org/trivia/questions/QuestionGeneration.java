package org.trivia.questions;

import ai.knowly.langtoch.capability.module.openai.unit.SimpleChatCapabilityUnit;
import ai.knowly.langtoch.prompt.template.PromptTemplate;
import com.google.common.collect.ImmutableList;

import java.util.concurrent.ExecutionException;

public class QuestionGeneration {

    public static String generateQuestion() throws ExecutionException, InterruptedException {
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
        return chatBot.run(promptTemplate.format());
    }
}
