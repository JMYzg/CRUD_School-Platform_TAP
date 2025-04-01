package com.tap.schoolplatform.models.academic.tasks;

import com.tap.schoolplatform.models.enums.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public abstract class Task {
    public static final double MAX_SCORE = 10;
    private final UUID id;
    private final LocalDate creationDate;
    private String title;
    private String description;
    private double score;
    private LocalDateTime deadline;
    private Status status;

    public Task(String title, String description, LocalDateTime deadline) {
        this.id = UUID.randomUUID(); // Maybe the title could work as id
        creationDate = LocalDate.now();
        this.title = title;
        this.description = description;
        this.score = 0;
        this.deadline = deadline;
        updateStatus();
    }

    public UUID getId() {
        return id;
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

    public LocalDateTime getDeadline() {
        return deadline;
    }
    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
        updateStatus();
    }

    public Status getStatus() {
        updateStatus();
        return status;
    }

    public void updateStatus() {
        if (LocalDateTime.now().isAfter(deadline)) {
            this.status = Status.INACTIVE;
        } else {
            this.status = Status.ACTIVE;
        }
    }
}
