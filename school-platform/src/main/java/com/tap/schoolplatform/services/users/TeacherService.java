package com.tap.schoolplatform.services.users;

import com.tap.schoolplatform.models.academic.Grade;
import com.tap.schoolplatform.models.academic.Group;
import com.tap.schoolplatform.models.academic.Subject;
import com.tap.schoolplatform.models.academic.keys.FileKey;
import com.tap.schoolplatform.models.academic.keys.GradeKey;
import com.tap.schoolplatform.models.academic.tasks.Assignment;
import com.tap.schoolplatform.models.academic.tasks.Evaluation;
import com.tap.schoolplatform.models.academic.tasks.Task;
import com.tap.schoolplatform.models.users.Student;
import com.tap.schoolplatform.models.users.Teacher;
import com.tap.schoolplatform.services.academic.SubjectService;
import com.tap.schoolplatform.utils.dtos.academic.tasks.TaskDTO;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.logging.Logger;

public class TeacherService {

    private static final Logger logger = Logger.getLogger(TeacherService.class.getName());

    private Teacher teacher;

    public TeacherService(Teacher teacher) {
        this.teacher = teacher;
    }

    public Teacher getTeacher() {
        return teacher;
    }
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void createTask(Subject subject, Integer unit, Task task, TaskDTO taskDTO) {
        SubjectService subjectService = new SubjectService(subject);
        subjectService.createTask(unit, task, taskDTO);
    }

    public Task readTask(Subject subject, Integer unit, String title) {
        SubjectService subjectService = new SubjectService(subject);
        return subjectService.readTask(unit, title);
    }

    public void updateTask(Subject subject, Task task, TaskDTO taskDTO) {
        SubjectService subjectService = new SubjectService(subject);
        subjectService.updateTask(task, taskDTO);
    }

    public void deleteTask(Subject subject, Integer unit, Task task) {
        SubjectService subjectService = new SubjectService(subject);
        subjectService.deleteTask(unit, task);
    }

    public void downloadFile(Stage stage, Group group, Student student, Assignment assignment) {
        FileKey key = new FileKey(group, student);
        File file = assignment.getFile(key);
        if (file == null) return;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save PDF");
        fileChooser.setInitialFileName(file.getName());
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF", "*.pdf"));
        File location = fileChooser.showSaveDialog(stage);
        if (location != null) {
            try {
                Files.copy(file.toPath(), location.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException exception) {
                logger.severe("Error downloading file: " + location);
            }
        }
    }

    public void gradeAssignment(Student student, Subject subject, int unit, double score, Assignment assignment) {
        Grade grade = new Grade(score);
        GradeKey key = new GradeKey(subject, unit, assignment);
        student.addGrade(key, grade);
    }
}
