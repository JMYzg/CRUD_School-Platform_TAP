package com.tap.schoolplatform.controllers.teacherControllers.examTeacherViewControllers;

import com.tap.schoolplatform.controllers.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.io.IOException;


import static com.tap.schoolplatform.controllers.teacherControllers.TeacherViewController.currentGroup;
public class ExamOptionController extends ViewController {

    public Button createNewExamButton;
    public AnchorPane anchorPaneExamContainer;
    public Button editButton;
    public VBox examViewsContainer;
    public Label groupNameLabel;
    public Label semesterLabel;
    public Label shiftLabel;
    public ScrollPane scrollPane;


    private ToggleGroup radioGroup;

    public void initialize() {
        groupNameLabel.setText(currentGroup.getID());
        semesterLabel.setText(currentGroup.getSemester().toString());
        shiftLabel.setText(currentGroup.getShift().toString());
        examViewsContainer = new VBox(10);
        examViewsContainer.setPrefWidth(anchorPaneExamContainer.getPrefWidth());
        anchorPaneExamContainer.getChildren().add(examViewsContainer);
        AnchorPane.setTopAnchor(examViewsContainer, 0.0);
        AnchorPane.setLeftAnchor(examViewsContainer, 0.0);
        AnchorPane.setRightAnchor(examViewsContainer, 0.0);
        AnchorPane.setBottomAnchor(examViewsContainer, 0.0);

    }

    public void createNewExamn(ActionEvent event) throws IOException {
        loadNewPageView(event, "/views/teacher-views/teacher-option-exam-new-view.fxml", "Create new exam");
    }

    public void addNewExam(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/views/teacher-views/teacher-option-exam-container-view.fxml"));
            Parent examView = loader.load();

            // Create a container for this exam view with a remove button
            AnchorPane examContainer = new AnchorPane();
            examContainer.getChildren().add(examView);

            // Add a remove button to the container
            Button removeButton = new Button("Remove");
            removeButton.setOnAction(e -> examViewsContainer.getChildren().remove(examContainer));
            examContainer.getChildren().add(removeButton);

            // Position the remove button at the top right
            AnchorPane.setTopAnchor(removeButton, 5.0);
            AnchorPane.setRightAnchor(removeButton, 5.0);

            // Add the container to the VBox
            examViewsContainer.getChildren().add(examContainer);

        } catch (Exception e) {
             e.printStackTrace();
         }
    }
}
