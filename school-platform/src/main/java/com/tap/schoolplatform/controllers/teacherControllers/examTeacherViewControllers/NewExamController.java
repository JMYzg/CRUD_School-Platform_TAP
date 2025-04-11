package com.tap.schoolplatform.controllers.teacherControllers.examTeacherViewControllers;

import com.tap.schoolplatform.auth.LoginService;
import com.tap.schoolplatform.controllers.ViewController;
import com.tap.schoolplatform.controllers.teacherControllers.TeacherViewController;
import com.tap.schoolplatform.models.academic.tasks.Answer;
import com.tap.schoolplatform.models.academic.tasks.Evaluation;
import com.tap.schoolplatform.models.academic.tasks.Question;
import com.tap.schoolplatform.models.academic.tasks.enums.AnswerType;
import com.tap.schoolplatform.models.users.Teacher;
import com.tap.schoolplatform.services.users.TeacherService;
import com.tap.schoolplatform.utils.SharedData;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class NewExamController extends ViewController {
    public Button submitExamButton;
    public TextField titleQuestion;
    public TextField examOption1TF;
    public TextField examOption2TF;
    public TextField examOption3TF;
    public TextField examOption4TF;
    public ToggleGroup radioGroup;
    public RadioButton rightAnswer1RadioButton;
    public RadioButton rightAnswer2RadioButton;
    public RadioButton rightAnswer3RadioButton;
    public RadioButton rightAnswer4RadioButton;
    public Button addQuestionButton;
    public TableColumn<Question, Integer> questionNumberTableColumn;
    public TableColumn<Question, String>  questionTableColumn;
    public TableView<Question> tableQuestions;
    public TeacherService teacherService;

    private boolean isEditing = true;
    public DatePicker datePicker;
    public Button cancelButton;
    public Button acceptExam;
    public TextField newExamUnit;

    //Brisa here
    private final SharedData currentExam = SharedData.getInstance();

    @FXML private Spinner<Integer> spinnerHourDte;
    @FXML private Spinner<Integer> spinnerMinuteDte;
    @FXML private Button btnSetTittleExam;
    @FXML private TextField titleTextField;
    @FXML private Label titleLabel;

    int currentHour;
    private Evaluation evaluation;
    private int currentUnit;

    public void initialize() {
        spinnerConfiguration(spinnerHourDte, 0, 23);
        spinnerConfiguration(spinnerMinuteDte, 0, 59);

        // initializeServices();
        teacherService = new TeacherService((Teacher) LoginService.getCurrentUser());

        titleTextField.setVisible(true);
        titleLabel.setVisible(false);

        rightAnswer1RadioButton.setUserData("1");
        rightAnswer2RadioButton.setUserData("2");
        rightAnswer3RadioButton.setUserData("3");
        rightAnswer4RadioButton.setUserData("4");

//        for(Question question : evaluation.getQuestionSet()){
//            int i = 1;
//            i++;
//        }
        initializeQuestionTable();
    }

    @FXML
    private void setExamTitle(){
        if (isEditing) {
            // Save the title and switch to display mode
            titleTextField.setVisible(false);
            titleLabel.setText(titleTextField.getText());
            titleLabel.setVisible(true);
            btnSetTittleExam.setText("EDIT");
        } else {
            // Switch back to edit mode
            titleTextField.setVisible(true);
            titleLabel.setVisible(false);
            btnSetTittleExam.setText("OK");
        }
        isEditing = !isEditing; // Toggle state
    }


    private void spinnerConfiguration(Spinner<Integer> spinner, int min, int max) {
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(min, max);
        valueFactory.setValue(min);

        valueFactory.setConverter(new StringConverter<Integer>() {

                public String toString(Integer value) {
                    return String.format("%02d", value); // Formato de dos dígitos
                }

                public Integer fromString(String string) {
                    return Integer.parseInt(string);
                }
        });

        spinner.setValueFactory(valueFactory);
    }

    @FXML
    private void handleSubmitExam() {

    }

    //:D

    public void cleanAllButton(ActionEvent event) {
        titleQuestion.clear();
        examOption1TF.clear();
        examOption2TF.clear();
        examOption3TF.clear();
        examOption4TF.clear();
        rightAnswer1RadioButton.setSelected(false);
        rightAnswer2RadioButton.setSelected(false);
        rightAnswer3RadioButton.setSelected(false);
        rightAnswer4RadioButton.setSelected(false);
    }

    public void cancelExam(ActionEvent event) {
        confirmationAlertCloseWindow("All questions and options will be lost","Are you sure you want to cancel the new exam?", cancelButton);
    }

    public void submitExam(ActionEvent event) {
        TeacherViewController.currentSubject.addTask(currentUnit, evaluation);
        confirmationAlertCloseWindow("You'll be able to edit the details from this homework later","Are you sure you want to create the new exam?", submitExamButton);
        Stage stage = (Stage) submitExamButton.getScene().getWindow();
        stage.close();
    }

    public void addQuestion(ActionEvent event) throws IOException {
        // Aquí obtienes los valores cuando el usuario haga clic

        int hoursDte = spinnerHourDte.getValue();
        int minutesDte = spinnerMinuteDte.getValue();
        String title = titleTextField.getText();
        currentUnit = Integer.parseInt(newExamUnit.getText());
        String questionTitle = titleQuestion.getText();
        String examOption1 = examOption1TF.getText();
        String examOption2 = examOption2TF.getText();
        String examOption3 = examOption3TF.getText();
        String examOption4 = examOption4TF.getText();

        //Obtiene el radiu button seleccionado
        Toggle toggle = radioGroup.getSelectedToggle();
        RadioButton selectedRadioButton = (RadioButton) toggle;
        int selectedRadioButtonId = Integer.parseInt(selectedRadioButton.getUserData().toString());

        String[] answers = {examOption1, examOption2, examOption3, examOption4};

        LocalDate deadline = datePicker.getValue();
        LocalTime deadlineTime = LocalTime.of(hoursDte, minutesDte);
        LocalDateTime examDateTime = LocalDateTime.of(deadline, deadlineTime);

        evaluation = new Evaluation(title, examDateTime);
        Question question = new Question(questionTitle);

        for (int i = 0; i < answers.length; i++) {
            Answer answer;
            if (i == selectedRadioButtonId) answer = new Answer(AnswerType.CORRECT, answers[i]);
            else answer = new Answer(AnswerType.INCORRECT, answers[i]);
            question.addAnswer(answer);
        }
        evaluation.addQuestion(question);
        alertInfo("", "Exam question added correctly", "");
        cleanAllButton(event);
    }

    public void setExamName(ActionEvent actionEvent) {
    }
    private void initializeQuestionTable() {
//        questionNumberTableColumn.setCellValueFactory(new PropertyValueFactory<>("No."));
        questionTableColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        tableQuestions.setItems(evaluation.getQuestionList());
    }

}
