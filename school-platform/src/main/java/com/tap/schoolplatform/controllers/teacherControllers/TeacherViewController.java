package com.tap.schoolplatform.controllers.teacherControllers;

import com.tap.schoolplatform.controllers.ViewController;
import com.tap.schoolplatform.models.academic.Group;
import com.tap.schoolplatform.models.academic.Subject;
import com.tap.schoolplatform.services.academic.SubjectService;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class TeacherViewController extends ViewController {
    public Button studentsButton;
    public Button examsButton;
    public Button homeworkButton;
    public Button gradesButton;
    public Button logOutButton;
    public BorderPane borderPane;
    public Label subjectName;

    SubjectService subjectService = new SubjectService();
    public static Group currentGroup;
    private Subject currentSubject;

    public void setCurrentSubject(Subject subject) {
        this.currentSubject = subject;
        subjectName.setText(subject.getName());
    }

    public void setCurrentGroup(Group group) {
        currentGroup = group;
    }

    public void initialize() {
    }

    public void openStudentTab(ActionEvent event) {
        loadPageView("/views/teacher-views/teacher-option-student-list-view.fxml", borderPane);
    }

    public void openExamsTab(ActionEvent event) {
        loadPageView("/views/teacher-views/teacher-option-exam-view.fxml", borderPane);
    }

    public void openHomeworkTab(ActionEvent event) {
        loadPageView("/views/teacher-views/teacher-option-homework-view.fxml", borderPane);
    }

    public void openGradesTab(ActionEvent event) {
        loadPageView("/views/teacher-views/teacher-option-grade-view.fxml", borderPane);
    }

    public void logOut(ActionEvent event) throws IOException {
    }
}