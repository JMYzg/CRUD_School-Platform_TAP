package com.tap.schoolplatform.utils.dtos.academic.tasks;

import com.tap.schoolplatform.models.academic.tasks.enums.TaskType;

import java.time.LocalDateTime;
import java.util.Timer;

public class TaskDTO {

    // Common Task attributes
    private TaskType type;
    private String title;
    private String description;
    private Double score;
    private LocalDateTime deadline;

    // Evaluation attributes

    private LocalDateTime startDate;
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

    public Double getScore() {
        return score;
    }
    public void setScore(Double score) {
        this.score = score;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }
    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    // Getters and setters for Evaluation attributes
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
}
