package com.tap.schoolplatform.models.academic;

import com.tap.schoolplatform.models.academic.tasks.Task;
import com.tap.schoolplatform.models.users.Teacher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;

import java.util.*;

public class Subject {

    private String name;
    private Degree degree;
    private int semester;
    private Teacher teacher;
    private String description;
    private final Map<Integer, ObservableSet<Task>> taskSets = new HashMap<>();
    private final Map<Integer, ObservableList<Task>> taskLists = new HashMap<>();

    public Subject(Degree degree, int semester, String name, String description) {
        this.degree = degree;
        this.name = name;
        this.semester = semester;
        this.description = description;
    }

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public Degree getDegree() {
        return degree;
    }
    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public int getSemester() {
        return semester;
    }
    public void setSemester(int semester) {
        this.semester = semester;
    }

    public Teacher getTeacher() {
        return teacher;
    }
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
    
    public ObservableList<Task> getTaskList(int unit) {
        return FXCollections.unmodifiableObservableList(taskLists.get(unit));
    }

    public void addTask(int unit, Task task) {
        ObservableSet<Task> taskSet = taskSets.get(unit);
        if (taskSet == null) {
            taskSet = FXCollections.observableSet(new TreeSet<>(Comparator.comparing(Task::getCreationDate)));
            ObservableList<Task> taskList = FXCollections.observableArrayList(taskSet);
            taskSets.put(unit, taskSet);
            taskLists.put(unit, taskList);
            taskSet.addListener((SetChangeListener.Change<? extends Task> change) -> {
                if (change.wasAdded()) {
                    taskList.add(change.getElementAdded());
                } else if (change.wasRemoved()) {
                    taskList.remove(change.getElementRemoved());
                }
            });
        }
        taskSet.add(task);
    }
    public void removeTask(int unit, Task task) {
        taskSets.get(unit).remove(task);
        if (taskSets.get(unit).isEmpty()) {
            taskLists.remove(unit);
        }
    }

    public ObservableList<Group> getGroupList() { // I think we could delete shift from GroupKey
        return degree.getGroupList(semester);
    }
}
