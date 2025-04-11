package com.tap.schoolplatform.models.academic.tasks;

import com.tap.schoolplatform.models.academic.keys.FileKey;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Assignment extends Task {

    private final ObservableMap<FileKey, File> submittedFiles = FXCollections.observableHashMap();

    private String description;

    public Assignment(String title, LocalDateTime deadline) {
        super(title, deadline);
    }

    public File getFile(FileKey key) {return submittedFiles.get(key);}
    public void submitFile(FileKey key, File file) {
        if (!isPDF(file)) {
            throw new IllegalArgumentException("File must be a PDF file");
        }
        this.submittedFiles.put(key, file);
    }

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public boolean isPDF(File file) {
        return file.isFile() && file.getName().endsWith(".pdf");
    }
}