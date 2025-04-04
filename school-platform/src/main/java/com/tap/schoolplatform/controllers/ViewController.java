package com.tap.schoolplatform.controllers;

import com.tap.schoolplatform.models.shared.Address;
import com.tap.schoolplatform.models.shared.BirthDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

public abstract class ViewController {

    public void alert(String alertTitle, String alertMsg, Alert.AlertType alertType) {
        Alert alertWindow = new Alert(alertType);
        alertWindow.setTitle(alertTitle);
        alertWindow.setContentText(alertMsg);
        alertWindow.showAndWait();
    }

    public void confirmationAlertCloseWindow(String contentText, String headerText, Button button) {
        Alert alertWindow = new Alert(Alert.AlertType.CONFIRMATION);
        alertWindow.initModality(Modality.APPLICATION_MODAL);
        alertWindow.setContentText(contentText);
        alertWindow.setTitle("");
        alertWindow.setHeaderText(headerText);
        Optional<ButtonType> result = alertWindow.showAndWait();
        if (result.get() == ButtonType.OK) {
            Stage stage = (Stage) button.getScene().getWindow();
            stage.close();
        }
    }

    public BirthDate createBrithDate (DatePicker datePicker) {
        LocalDate date = datePicker.getValue();
        return new BirthDate(date.getDayOfMonth(), date.getMonthValue(), date.getYear());
    }

    public Address createAddress(TextField street, TextField PC, TextField colony, TextField city, TextField state, TextField country){
        int pc = Integer.parseInt(PC.getText());
        return new Address(street.getSelectedText(), pc, colony.getSelectedText(), city.getSelectedText(), state.getSelectedText(), country.getSelectedText());
    }

    public void loadPageView(String pageName, BorderPane borderPane) {
        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/" + pageName + ".fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        borderPane.setCenter(root);
    }

    public void loadNewPageView (ActionEvent event, String viewName, String viewTitle) throws IOException {
        Stage ownerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.WINDOW_MODAL);
        primaryStage.initOwner(ownerStage);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/" + viewName + ".fxml")));
        primaryStage.setTitle(viewTitle);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    public void logOutFunction (Button logOutButton) throws IOException {
        Stage stage = (Stage) logOutButton.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/login-view.fxml")));
        primaryStage.setTitle("Log in");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

}
