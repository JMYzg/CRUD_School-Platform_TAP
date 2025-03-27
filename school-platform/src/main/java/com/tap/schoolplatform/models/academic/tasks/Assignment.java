package com.tap.schoolplatform.models.academic.tasks;

import com.tap.schoolplatform.models.academic.tasks.enums.TaskType;

import java.io.File;
import java.time.LocalDate;

public class Assignment extends Task {

    private File file;

    public Assignment(String title, String description, double score, LocalDate deadline) {
        super(TaskType.ASSIGNMENT, title, description, score, deadline);
    }

    public File getFile() {return file;}
    public void setFile(File file) {
        if (!file.exists()) {
            throw new IllegalArgumentException("File does not exist");
        }
        this.file = file;
    }
}
