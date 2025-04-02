package com.tap.schoolplatform.controllers;

import com.tap.schoolplatform.models.shared.Address;
import com.tap.schoolplatform.models.shared.BirthDate;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Objects;

public abstract class ViewController {

    public void alert(String alertTitle, String alertMsg, Alert.AlertType alertType) {
        Alert alertWindow = new Alert(alertType);
        alertWindow.setTitle(alertTitle);
        alertWindow.setContentText(alertMsg);
        alertWindow.showAndWait();
    }

    public BirthDate createBrithDate (TextField dayTF, TextField monthTF, TextField yearTF) {
        int d = Integer.parseInt(dayTF.getText());
        int m = Integer.parseInt(monthTF.getText());
        int y = Integer.parseInt(yearTF.getText());

        return new BirthDate(d, m, y);
    }

    public Address createAddress(TextField street, TextField PC, TextField colony, TextField city, TextField state, TextField country){
        int pc = Integer.parseInt(PC.getText());
        return new Address(street.getSelectedText(), pc, colony.getSelectedText(), city.getSelectedText(), state.getSelectedText(), country.getSelectedText());
    }

    public void loadPageView(String pageName, BorderPane borderPane) {
        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(pageName)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        borderPane.setCenter(root);
    }

}
