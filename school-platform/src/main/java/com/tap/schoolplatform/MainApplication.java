package com.tap.schoolplatform;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class MainApplication extends Application {
    @Override

    public void start(Stage stage) throws IOException {
        stage.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/views/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Log in");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}