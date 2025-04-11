package com.tap.schoolplatform.services.academic;

import com.tap.schoolplatform.models.academic.Group;
import com.tap.schoolplatform.models.academic.Subject;
import com.tap.schoolplatform.models.academic.enums.Semester;
import com.tap.schoolplatform.models.academic.tasks.Assignment;
import com.tap.schoolplatform.models.academic.tasks.Evaluation;
import com.tap.schoolplatform.models.academic.tasks.Task;
import com.tap.schoolplatform.utils.dtos.academic.tasks.TaskDTO;

import java.util.List;

public class SubjectService {

    Subject subject;

    public SubjectService(Subject subject) {
        this.subject = subject;
    }

    public SubjectService() {}

    public Subject getSubject() {
        return subject;
    }
    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void createTask(Integer unit, Task task, TaskDTO taskDTO) {

        if (task instanceof Assignment assignment) {
            assignment.setDescription(taskDTO.getDescription().trim());
        }

//        if (task instanceof Evaluation evaluation) {
//            evaluation.setStartDate(taskDTO.getStartDate());
//            evaluation.setTimer(taskDTO.getTimer());
//        }
        subject.addTask(unit, task);
    }

    public Task readTask(Integer unit, String title) {
        for (Task task : subject.getTaskList(unit)) {
            if (task.getTitle().equals(title)) return task;
        }
        return null;
    }

    public void updateTask(Task task, TaskDTO taskDTO) {
        if (taskDTO.getTitle() != null) task.setTitle(taskDTO.getTitle().trim());
        if (taskDTO.getScore() != null) task.setScore(taskDTO.getScore());
        if (taskDTO.getDeadline() != null) task.setDeadline(taskDTO.getDeadline());

        if (task instanceof Assignment assignment) {
            if (taskDTO.getDescription() != null) assignment.setDescription(taskDTO.getDescription().trim());
        }

        if (task instanceof Evaluation evaluation) {
            if (taskDTO.getStartDate() != null) evaluation.setStartDate(taskDTO.getStartDate());
            if (taskDTO.getTimer() != null) evaluation.setTimer(taskDTO.getTimer());
        }
    }

    public void deleteTask(Integer unit, Task task) {
        subject.removeTask(unit, task);
    }

    public List<Group> getGroupList(Semester semester) {
        return subject.getDegree().getGroupList(semester);
    }
}
