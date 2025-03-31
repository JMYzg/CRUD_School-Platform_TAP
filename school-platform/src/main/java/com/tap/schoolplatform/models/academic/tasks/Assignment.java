package com.tap.schoolplatform.models.academic.tasks;

import com.tap.schoolplatform.models.academic.tasks.enums.TaskType;

import java.io.File;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Assignment extends Task {

    private final Map<String, File> completed = new HashMap<>();

    public Assignment(String title, String description, LocalDate deadline) {
        super(TaskType.ASSIGNMENT, title, description, deadline);
    }

    public File getFile(String studentId) {return completed.get(studentId);}
    public void setFile(String studentId, File file) {
        if (!file.exists()) {
            throw new IllegalArgumentException("File does not exist");
        }
        this.completed.put(studentId, file);
    }
}