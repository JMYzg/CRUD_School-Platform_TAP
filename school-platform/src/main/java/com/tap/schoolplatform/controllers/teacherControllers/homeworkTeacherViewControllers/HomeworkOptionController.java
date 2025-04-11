package com.tap.schoolplatform.controllers.teacherControllers.homeworkTeacherViewControllers;

import com.tap.schoolplatform.controllers.ViewController;
import com.tap.schoolplatform.controllers.teacherControllers.examTeacherViewControllers.ExamContainerController;
import com.tap.schoolplatform.models.academic.tasks.Assignment;
import com.tap.schoolplatform.models.academic.tasks.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

import static com.tap.schoolplatform.controllers.teacherControllers.TeacherViewController.currentGroup;
import static com.tap.schoolplatform.controllers.teacherControllers.TeacherViewController.currentSubject;

public class HomeworkOptionController extends ViewController {
    public Button addHomeworkButton;
    public Button editHomework;
    public Button gradeButton;
    public Label groupName;
    public Label semesterName;
    public Label shiftName;
    public VBox homeworkViewsContainer;
    public AnchorPane anchorPaneHomeworkContainer;

    public void initialize() {
        groupName.setText(currentGroup.getID());
        semesterName.setText(currentGroup.getSemester().toString());
        shiftName.setText(currentGroup.getShift().toString());
        initTasks();
    }

    public void initTasks() {
        for (Integer unit : currentSubject.getTaskListMap().keySet()) {
            for (Task task : currentSubject.getTaskListMap().get(unit)) {
                if (task instanceof Assignment assignment) {
                    try {
                        // Load the FXML for each evaluation
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/teacher-views/teacher-option-homework-container-view.fxml"));
                        Parent examView = loader.load();

                        // Get the controller and set the evaluation
                        HomeworkContainerController controller = loader.getController();
                        controller.setAssignment(assignment);

                        // Create a container with a remove button
                        AnchorPane assignmentContainer = new AnchorPane();
                        assignmentContainer.getChildren().add(examView);

//                        Button removeButton = new Button("Remove");
//                        removeButton.setOnAction(e -> homeworkViewsContainer.getChildren().remove(assignmentContainer));
//                        assignmentContainer.getChildren().add(removeButton);
//
//                        AnchorPane.setTopAnchor(removeButton, 5.0);
//                        AnchorPane.setRightAnchor(removeButton, 5.0);

                        // Add to the container
                        homeworkViewsContainer.getChildren().add(assignmentContainer);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void addHomework(ActionEvent event) throws IOException {
        loadNewPageView(event, "/views/teacher-views/teacher-option-homework-new-view.fxml", "Create new homework");
    }

    public void editHomework(ActionEvent event) throws IOException {
        loadNewPageView(event, "/views/teacher-views/teacher-option-homework-new-view.fxml", "Edit homework");
    }

    public void homeworkGradeView(ActionEvent event) throws IOException {
        loadNewPageView(event, "/views/teacher-views/teacher-option-homework-grade-view.fxml", "Homework grades");
    }
}
