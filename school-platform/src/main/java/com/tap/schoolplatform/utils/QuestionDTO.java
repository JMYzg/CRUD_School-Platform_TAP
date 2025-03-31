package com.tap.schoolplatform.utils;

import com.tap.schoolplatform.models.academic.tasks.Answer;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class QuestionDTO {

    private String description;
    private Set<Answer> answers;

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
