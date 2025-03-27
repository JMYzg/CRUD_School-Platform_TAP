package com.tap.schoolplatform.models.academic;

import com.tap.schoolplatform.models.academic.tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Subject {

    private String name;
    private String description;
    private final Map<Integer, List<Task>> tasks = new HashMap<>();

    public Subject(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
    
    public List<Task> getTasks(Integer unit) {return tasks.get(unit);}
    public void addTask(Integer unit, Task task) {
        this.tasks.computeIfAbsent(unit, u -> new ArrayList<>()).add(task);
    }
}
