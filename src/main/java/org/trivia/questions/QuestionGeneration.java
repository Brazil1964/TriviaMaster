package org.trivia.questions;

import ai.knowly.langtoch.capability.module.openai.unit.SimpleChatCapabilityUnit;
import ai.knowly.langtoch.prompt.template.PromptTemplate;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

// https://github.com/Tudor44/demo-application-ai-spring-langtorch/tree/master
// https://github.com/Knowly-ai/langtorch
// https://docs.knowly.ai/langtorch/

public class QuestionGeneration {
    public static String triviaType;
    public static List<String> questions = new ArrayList<>();
    public static Map<String, String> generateQuestion() throws ExecutionException, InterruptedException {
        // Reading the key from the environment variable under Resource folder(.env file, OPENAI_API_KEY field)
        SimpleChatCapabilityUnit chatBot = SimpleChatCapabilityUnit.create();

        String questionCollection = questions.stream().reduce("", (a, b) -> a + "\n" + b + "\n");
        PromptTemplate promptTemplate =
                PromptTemplate.builder()
                        .setExampleHeader("Here's one example:")
                        .setExamples(
                                List.of(
                                        "What is 25% of 1400?\n"
                                                + "a) 700\n"
                                                + "b) 350\n"
                                                + "c) 1050\n"
                                                + "d) 1000\n"
                                                + "answer: b"))
                        .setTemplate(
                                "Can you please generate a " + triviaType + " question by following the response template:\n"
                                        + "{question}\n"
                                        + "{option A}\n"
                                        + "{option B}\n"
                                        + "{option C}\n"
                                        + "{option D}\n"
                                        + "{answer}\n "
                                        + ""
                                        + "The followings are already generated questions and please don't generate the same question:\n"
                                        + "{{$generated_questions}}")
                        .addVariableValuePair("generated_questions", questionCollection).build();
        String results = chatBot.run(promptTemplate.format());
        questions.add(results);
        if (questions.size() == 3) {
            questions.remove(0);
        }
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
