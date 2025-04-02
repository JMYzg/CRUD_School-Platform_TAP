package com.tap.schoolplatform.services.academic;

import com.tap.schoolplatform.models.academic.Subject;
import com.tap.schoolplatform.models.academic.tasks.Evaluation;
import com.tap.schoolplatform.models.academic.tasks.Task;
import com.tap.schoolplatform.utils.dtos.academic.tasks.TaskDTO;

public class SubjectService {

    Subject subject;

    public SubjectService(Subject subject) {
        this.subject = subject;
    }

    public Subject getSubject() {
        return subject;
    }
    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void createTask(Integer unit, Task task, TaskDTO taskDTO) {
        if (task instanceof Evaluation evaluation) {
            evaluation.setStartDate(taskDTO.getStartDate());
            evaluation.setTimer(taskDTO.getTimer());
        }
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
        if (taskDTO.getDescription() != null) task.setDescription(taskDTO.getDescription().trim());
        if (taskDTO.getScore() != null) task.setScore(taskDTO.getScore());
        if (taskDTO.getDeadline() != null) task.setDeadline(taskDTO.getDeadline());

        if (task instanceof Evaluation evaluation) {
            if (taskDTO.getStartDate() != null) evaluation.setStartDate(taskDTO.getStartDate());
            if (taskDTO.getTimer() != null) evaluation.setTimer(taskDTO.getTimer());
        }
    }

    public void deleteTask(Integer unit, Task task) {
        subject.removeTask(unit, task);
    }
}
