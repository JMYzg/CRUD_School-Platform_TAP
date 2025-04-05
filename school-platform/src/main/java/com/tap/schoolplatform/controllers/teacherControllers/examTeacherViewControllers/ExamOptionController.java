package com.tap.schoolplatform.controllers.teacherControllers.examTeacherViewControllers;

import com.tap.schoolplatform.controllers.ViewController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

public class ExamOptionController extends ViewController {

    public Button createNewExamButton;

    public void createNewExamn(ActionEvent event) throws IOException {
        loadNewPageView(event, "/views/teacher-views/teacher-option-exams-new-view.fxml", "Create new exam");
    }
}
