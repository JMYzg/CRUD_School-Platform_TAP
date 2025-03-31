package com.tap.schoolplatform.models.academic.tasks;

import java.util.*;

public class Question {

    private String description;
    private final Set<Answer> answers = new TreeSet<>(Comparator.comparing(Answer::getIndex));

    public Question(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Answer> getAnswerSet() {
        return answers;
    }

    public Answer getAnswer(int index) {
        return answers.stream()
                .filter(answer -> answer.getIndex() == index)
                .findFirst().orElse(null);
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
        sortIndexes();
    }

    public void removeAnswer(Answer answer) {
        answers.remove(answer);
    }

    private void sortIndexes() {
        int index = 1;
        for (Answer answer : answers) {
            if (answer.getIndex() != index) {
                answer.setIndex(index);
            }
            index++;
        }
    }
}
