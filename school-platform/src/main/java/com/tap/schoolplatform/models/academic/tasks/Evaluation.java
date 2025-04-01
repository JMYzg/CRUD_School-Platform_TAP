package com.tap.schoolplatform.models.academic.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Evaluation extends Task {

    private LocalDateTime startDate;
    private Timer timer;

    private final Set<Question> questions = new HashSet<>();

    public Evaluation(String title, String description, LocalDateTime deadline) {
        super(title, description, deadline);
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

    public Set<Question> getQuestionSet() {
        return questions;
    }
    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void removeQuestion(Question question) {
        questions.remove(question);
    }
}
