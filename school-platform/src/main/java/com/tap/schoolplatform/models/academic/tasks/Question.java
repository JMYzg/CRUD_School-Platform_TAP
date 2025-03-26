package com.tap.schoolplatform.models.academic.tasks;

import com.tap.schoolplatform.models.academic.tasks.enums.QuestionType;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private QuestionType questionType;
    private double value;
    private String description;
    private final List<Answer> answers = new ArrayList<>();

    Question(QuestionType questionType, double value, String description) {
        this.questionType = questionType;
        this.value = value;
        this.description = description;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }
    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public double getValue() {
        return value;
    }
    public void setValue(double value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public Answer getAnswer(int index) {
        return answers.get(index);
    }
    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }
}
