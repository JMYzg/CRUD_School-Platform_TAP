package com.tap.schoolplatform.controllers.teacherControllers.examTeacherViewControllers;

import com.tap.schoolplatform.controllers.ViewController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

public class ExamOptionController extends ViewController {

    public Button createNewExamButton;
    public Button editButton;
    public Button resutlsButton;

    public void createNewExam(ActionEvent event) throws IOException {
        loadNewPageView(event, "teacher-option-exams-new-view", "Create new exam");
    }

    public void editExam(ActionEvent event) throws IOException {
        loadNewPageView(event, "teacher-option-exams-new-view", "Edit exam");
    }

    public void examResults(ActionEvent event) throws IOException {
        loadNewPageView(event, "teacher-option-exam-result-view", "Exams results");
    }
}
