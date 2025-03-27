package com.tap.schoolplatform.models.academic.tasks;

import com.tap.schoolplatform.models.academic.tasks.enums.TaskType;

import java.time.LocalDate;
import java.util.UUID;

public abstract class Task {
    public static final double MAX_SCORE = 10;
    private final UUID taskId;
    private TaskType type;
    private final LocalDate creationDate;
    private String title;
    private String description;
    private double score;
    private LocalDate deadline;

    public Task(TaskType type, String title, String description, double score, LocalDate deadline) {
        this.taskId = UUID.randomUUID();
        this.type = type;
        creationDate = LocalDate.now();
        this.title = title;
        this.description = description;
        this.score = score;
        this.deadline = deadline;
    }

    public UUID getTaskId() {
        return taskId;
    }

    public TaskType getType() {
        return type;
    }
    public void setType(TaskType type) {
        this.type = type;
    }

    public LocalDate getCreationDate() {
        return creationDate;
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
}
