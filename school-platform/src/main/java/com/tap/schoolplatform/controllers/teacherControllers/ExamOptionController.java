package com.tap.schoolplatform.controllers.teacherControllers;

import com.tap.schoolplatform.controllers.ViewController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

public class ExamOptionController extends ViewController {

    public Button createNewExamButton;

    public void createNewExamn(ActionEvent event) throws IOException {
        loadNewPageView(event, "teacher-option-exams-new-view", "Create new exam");
    }
}
