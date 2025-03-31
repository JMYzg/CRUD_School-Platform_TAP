package com.tap.schoolplatform.utils;

import com.tap.schoolplatform.models.academic.tasks.enums.TaskType;

import java.time.LocalDate;
import java.util.Timer;

public class TaskDTO {

    // Common Task attributes
    private TaskType type;
    private String title;
    private String description;
    private double score;
    private LocalDate deadline;

    // Evaluation attributes

    private LocalDate startDate;
    private Timer timer;

    // Getters and setters for common Task attributes
    public TaskType getType() {
        return type;
    }
    public void setType(TaskType type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public double getScore() {
        return score;
    }
    public void setScore(double score) {
        this.score = score;
    }

    public LocalDate getDeadline() {
        return deadline;
    }
    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    // Getters and setters for Evaluation attributes
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
}
