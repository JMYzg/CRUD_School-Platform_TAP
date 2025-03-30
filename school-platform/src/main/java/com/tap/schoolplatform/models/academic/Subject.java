package com.tap.schoolplatform.models.academic;

import com.tap.schoolplatform.models.academic.tasks.Task;

import java.util.*;

public class Subject {

    private Degree degree;
    private int semester;
    private String name;
    private String description;
    private final Map<Integer, List<Task>> tasks = new HashMap<>();

    public Subject(Degree degree, int semester, String name, String description) {
        this.degree = degree;
        this.name = name;
        this.semester = semester;
        this.description = description;
    }

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

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
    
    public List<Task> getTaskList(int unit) {return tasks.get(unit);}
    public Task getTask(int unit, UUID taskId) {
        for (Task task : getTaskList(unit)) {
            if (task.getTaskId().equals(taskId)) {
                return task;
            }
        }
        return null;
    }
    public void addTask(int unit, Task task) {
        this.tasks.computeIfAbsent(unit, u -> new ArrayList<>()).add(task);
    }
}
