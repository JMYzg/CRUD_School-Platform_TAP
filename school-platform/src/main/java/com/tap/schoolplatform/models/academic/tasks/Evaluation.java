package com.tap.schoolplatform.models.academic.tasks;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.util.*;

public class Evaluation extends Task {

    private LocalDateTime startDate;
    private Timer timer;

    private final ObservableList<Question> questions = FXCollections.observableArrayList();

    public Evaluation(String title, LocalDateTime deadline) {
        super(title, deadline);
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public Timer getTimer() {
        return timer;
    }
    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public ObservableList<Question> getQuestionList() {
        return FXCollections.unmodifiableObservableList(questions);
    }
    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void removeQuestion(Question question) {
        questions.remove(question);
    }
}
