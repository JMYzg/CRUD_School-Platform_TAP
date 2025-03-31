package com.tap.schoolplatform.models.academic.tasks;

import com.tap.schoolplatform.models.academic.tasks.enums.TaskType;

import java.time.LocalDate;
import java.util.*;

public class Evaluation extends Task {

    private LocalDate startDate;
    private Timer timer;

    private final Set<Question> questions = new HashSet<>();

    public Evaluation(String title, String description, LocalDate deadline) {
        super(TaskType.EVALUATION, title, description, deadline);
    }

    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
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
