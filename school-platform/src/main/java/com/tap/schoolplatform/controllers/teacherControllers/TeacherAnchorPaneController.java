package com.tap.schoolplatform.controllers.teacherControllers;

import com.tap.schoolplatform.auth.LoginService;
import com.tap.schoolplatform.controllers.ViewController;
import com.tap.schoolplatform.models.academic.Group;
import com.tap.schoolplatform.models.academic.enums.Semester;
import com.tap.schoolplatform.models.academic.tasks.Task;
import com.tap.schoolplatform.models.users.Teacher;
import com.tap.schoolplatform.models.academic.Subject;
import com.tap.schoolplatform.services.academic.DegreeService;
import com.tap.schoolplatform.services.users.TeacherService;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.Objects;
import java.util.logging.Logger;

public class TeacherAnchorPaneController extends ViewController {

    public Label teacherName;
    public Button logOutButton;
    public Tab groupTab;
    public VBox vBox;
    public BorderPane borderPane = new BorderPane();
    public AnchorPane anchorPane;

    TeacherService teacherService;
    public Map<Subject, Button> buttonMap;
    public Map<Button, TabPane> tabPaneMap;
    public Map<Tab, Group> tabMap;

    private static final Logger logger = Logger.getLogger(TeacherAnchorPaneController.class.getName());

    Teacher currentTeacher = (Teacher) LoginService.getCurrentUser();

    public void initialize() throws IOException {
        teacherService = new TeacherService();
        teacherName.setText(currentTeacher.getName() + " " + currentTeacher.getLastName());
        teacherService.setTeacher(currentTeacher);
        buttonMap = new HashMap<>();
        tabPaneMap = new HashMap<>();
        tabMap = new HashMap<>();
        for (Semester semester : teacherService.getTeacher().getSubjectLists().keySet()) {
            for (Subject subject : teacherService.getTeacher().getAssignedSubjectList(semester)) {
                Button button = new Button(subject.getName());
                TabPane tabPane = new TabPane();
                tabPane.setUserData(subject);
                vBox.getChildren().add(button);
                buttonMap.put(subject, button);
                tabPaneMap.put(button, tabPane);
                button.setOnAction(event -> {
                    borderPane.setCenter(tabPane);

                    // Obtener el Subject asociado al TabPane
                    Subject currentSubject = (Subject) tabPane.getUserData();

                    // Verificar si hay un tab seleccionado
                    Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
                    if (selectedTab != null) {
                        Node content = selectedTab.getContent();
                        if (content != null) {
                            // Obtener el controlador desde el UserData del contenido
                            TeacherViewController controller = (TeacherViewController) content.getUserData();
                            if (controller != null) {
                                controller.setCurrentSubject(currentSubject);
                            } else {
                                loadTabContent(selectedTab); // Recargar si no hay controlador
                            }
                        } else {
                            loadTabContent(selectedTab); // Cargar contenido si no está presente
                        }
                    }
                });
                for (Group group : subject.getGroupList()) {
                    Tab tab = new Tab(group.getID());
                    tabPane.getTabs().add(tab);
                    tabMap.put(tab, group);
                    tab.setOnSelectionChanged(event -> {
                        if (tab.isSelected()) {
                            loadTabContent(tab);
                        }
                    });
                    if (tab.isSelected()) {
                        loadTabContent(tab);
                    }
                }
            }
        }
    }

    public void logOut(ActionEvent actionEvent) throws IOException {
        logOutFunction(logOutButton);
    }

    private void loadTabContent(Tab tab) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/teacher-views/teacher-option-view.fxml"));
            Node content = loader.load();
            TeacherViewController controller = loader.getController();

            // Obtener el Subject del TabPane
            Subject subject = (Subject) tab.getTabPane().getUserData();
            controller.setCurrentSubject(subject); // Actualiza el Subject en el controlador

            // Si necesitas el Group:
            Group group = tabMap.get(tab);
            controller.setCurrentGroup(group);

            tab.setContent(content);
        } catch (IOException e) {
            logger.severe("Error al cargar teacher-option-view: " + e.getMessage());
        }
    }
}
