package com.tap.schoolplatform.models.academic;

import com.tap.schoolplatform.models.academic.keys.GroupKey;
import com.tap.schoolplatform.models.users.Teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Degree {
    private String name;
    private final Map<GroupKey, List<Group>> groups = new HashMap<>();
    private final List<Teacher> teachers = new ArrayList<>();

    public Degree(String name) {
        this.name = name;
    }

    public String getName() {return name;}
    public void setName(String name) {
        this.name = name;
    }

    public List<Group> getGroups(GroupKey key) {
        return groups.get(key);
    }
    public void addGroup(GroupKey key, Group group) {
        groups.computeIfAbsent(key, k -> new ArrayList<>()).add(group);
    }

    public List<Teacher> getTeachers() {return teachers;}
    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }
}
