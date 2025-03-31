package com.tap.schoolplatform.services.users;

import com.tap.schoolplatform.models.academic.Subject;
import com.tap.schoolplatform.models.academic.keys.TaskKey;
import com.tap.schoolplatform.models.academic.tasks.Assignment;
import com.tap.schoolplatform.models.academic.tasks.Evaluation;
import com.tap.schoolplatform.models.academic.tasks.Task;
import com.tap.schoolplatform.models.academic.tasks.enums.TaskType;
import com.tap.schoolplatform.models.users.Teacher;
import com.tap.schoolplatform.utils.TaskDTO;

import java.time.LocalDate;

public class TeacherService {

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

    public void gradeAssignment(int unit, Assignment assignment, String studentId, double score) {

    }
}
