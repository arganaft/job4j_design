package ru.job4j.kiss.fool;

import java.util.LinkedList;
import java.util.List;

public class IOList implements IOInterface {
    private final List<String> messages;
    private final List<String> answers = new LinkedList<>();
    int position = 0;

    public List<String> getAnswers() {
        return answers;
    }

    public IOList(List<String> messages) {
        this.messages = messages;
    }

    @Override
    public String input() {
        return messages.size() > position ? playerAnswer() : "Finish";
    }

    private String playerAnswer() {
        String playerAnswer = messages.get(position++);
        answers.add(playerAnswer);
        return playerAnswer;
    }

    @Override
    public void output(String msg) {
        answers.add(msg);
    }


}
