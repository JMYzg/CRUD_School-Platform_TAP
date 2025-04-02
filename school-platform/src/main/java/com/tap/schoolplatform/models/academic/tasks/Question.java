package com.tap.schoolplatform.models.academic.tasks;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;

import java.util.*;

public class Question {

    private String description;
    private final ObservableSet<Answer> answerSet = FXCollections.observableSet(new TreeSet<>(Comparator.comparing(Answer::getIndex)));

    public Question(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Answer getAnswer(int index) {
        return answerSet.stream()
                .filter(answer -> answer.getIndex() == index)
                .findFirst().orElse(null);
    }

    public void addAnswer(Answer answer) {
        answerSet.add(answer);
        sortIndexes();
    }
    public void removeAnswer(Answer answer) {
        answerSet.remove(answer);
    }

    private void sortIndexes() {
        int index = 1;
        for (Answer answer : answerSet) {
            if (answer.getIndex() != index) {
                answer.setIndex(index);
            }
            index++;
        }
    }
}
