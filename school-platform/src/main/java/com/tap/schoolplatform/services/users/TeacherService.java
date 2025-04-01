package com.tap.schoolplatform.services.users;

import com.tap.schoolplatform.models.academic.Grade;
import com.tap.schoolplatform.models.academic.Group;
import com.tap.schoolplatform.models.academic.Subject;
import com.tap.schoolplatform.models.academic.keys.FileKey;
import com.tap.schoolplatform.models.academic.keys.GradeKey;
import com.tap.schoolplatform.models.academic.keys.TaskKey;
import com.tap.schoolplatform.models.academic.tasks.Assignment;
import com.tap.schoolplatform.models.academic.tasks.Evaluation;
import com.tap.schoolplatform.models.academic.tasks.Task;
import com.tap.schoolplatform.models.academic.tasks.enums.TaskType;
import com.tap.schoolplatform.models.users.Student;
import com.tap.schoolplatform.models.users.Teacher;
import com.tap.schoolplatform.utils.TaskDTO;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.logging.Logger;

public class TeacherService {

    private static final Logger logger = Logger.getLogger(TeacherService.class.getName());

    private Subject subject;
    private Teacher teacher;

    public TeacherService(Teacher teacher, Subject subject) {
        this.teacher = teacher;
        this.subject = subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Subject getSubject() {
        return subject;
    }
    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void createTask(int unit, Task task, TaskDTO taskDTO) {
        if (task instanceof Evaluation evaluation) {
            evaluation.setStartDate(taskDTO.getStartDate());
            evaluation.setTimer(taskDTO.getTimer());
        }
        subject.addTask(unit, task);
    }

    public void updateTask(Task task, TaskDTO taskDTO) {
        if (taskDTO.getTitle() != null) task.setTitle(taskDTO.getTitle());
        if (taskDTO.getDescription() != null) task.setDescription(taskDTO.getDescription());
        if (taskDTO.getScore() != 0) task.setScore(taskDTO.getScore());
        if (taskDTO.getDeadline() != null) task.setDeadline(taskDTO.getDeadline());

        if (task instanceof Evaluation evaluation) {
            if (taskDTO.getStartDate() != null) evaluation.setStartDate(taskDTO.getStartDate());
            if (taskDTO.getTimer() != null) evaluation.setTimer(taskDTO.getTimer());
        }
    }

    public void deleteTask(int unit, Task task) {
        subject.removeTask(unit, task);
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
