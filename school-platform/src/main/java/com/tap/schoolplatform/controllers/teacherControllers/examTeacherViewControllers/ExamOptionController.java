package com.tap.schoolplatform.controllers.teacherControllers.examTeacherViewControllers;

import com.tap.schoolplatform.controllers.ViewController;
import com.tap.schoolplatform.controllers.teacherControllers.TeacherViewController;
import com.tap.schoolplatform.models.academic.Subject;
import com.tap.schoolplatform.models.academic.tasks.Evaluation;
import com.tap.schoolplatform.models.academic.tasks.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

import static com.tap.schoolplatform.controllers.teacherControllers.TeacherViewController.currentGroup;
import static com.tap.schoolplatform.controllers.teacherControllers.TeacherViewController.currentSubject;

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
        initTasks();
    }

    public void initTasks() {
        for (Integer unit : currentSubject.getTaskListMap().keySet()) {
            for (Task task : currentSubject.getTaskListMap().get(unit)) {
                if (task instanceof Evaluation evaluation) {
                    try {
                        // Load the FXML for each evaluation
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/teacher-views/teacher-option-exam-container-view.fxml"));
                        Parent examView = loader.load();

                        // Get the controller and set the evaluation
                        ExamContainerController controller = loader.getController();
                        controller.setEvaluation(evaluation);

                        // Create a container with a remove button
                        AnchorPane examContainer = new AnchorPane();
                        examContainer.getChildren().add(examView);

//                        Button removeButton = new Button("Remove");
//                        removeButton.setOnAction(e -> examViewsContainer.getChildren().remove(examContainer));
//                        examContainer.getChildren().add(removeButton);
//
//                        AnchorPane.setTopAnchor(removeButton, 5.0);
//                        AnchorPane.setRightAnchor(removeButton, 5.0);

                        // Add to the container
                        examViewsContainer.getChildren().add(examContainer);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }

    public void createNewExamn(ActionEvent event) throws IOException {
        loadNewPageView(event, "/views/teacher-views/teacher-option-exam-new-view.fxml", "Create new exam");
    }

    public void addNewExam(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/teacher-views/teacher-option-exam-container-view.fxml"));
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
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/teacher-views/teacher-option-exam-container-view.fxml"));
//            Parent examView = loader.load();
//
//            // Get the controller and set the evaluation
//            ExamContainerController controller = loader.getController();
//
//            // You can pass different Evaluation objects here for each instance
//            // For example, get it from a list or create a new one
//            Evaluation evaluation = getEvaluationForNewExam(); // Implement this method
//            controller.setEvaluation(evaluation);
//
//            // Create a container for this exam view with a remove button
//            AnchorPane examContainer = new AnchorPane();
//            examContainer.getChildren().add(examView);
//
//            // Add a remove button to the container
//            Button removeButton = new Button("Remove");
//            removeButton.setOnAction(e -> examViewsContainer.getChildren().remove(examContainer));
//            examContainer.getChildren().add(removeButton);
//
//            // Position the remove button at the top right
//            AnchorPane.setTopAnchor(removeButton, 5.0);
//            AnchorPane.setRightAnchor(removeButton, 5.0);
//
//            // Add the container to the VBox
//            examViewsContainer.getChildren().add(examContainer);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

//    private Evaluation getEvaluationForNewExam() {
//        // This could retrieve from a database, create new ones, or get from a list
//        // For example:
//        Evaluation newEvaluation = new Evaluation();
//        newEvaluation.setTitle("New Exam " + (examViewsContainer.getChildren().size() + 1));
//        // Set other properties of the evaluation
//        return newEvaluation;
//    }

}
