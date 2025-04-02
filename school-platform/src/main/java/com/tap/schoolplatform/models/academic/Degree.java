package com.tap.schoolplatform.models.academic;

import com.tap.schoolplatform.models.users.Teacher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Degree {
    private String name;
    private final Map<Integer, ObservableList<Group>> groups = new HashMap<>();
    private final ObservableList<Teacher> teachers = FXCollections.observableArrayList();
    private final Map<Integer, ObservableList<Subject>> subjects = new HashMap<>();

    public Degree(String name) {
        this.name = name;
    }

    public String getName() {return name;}
    public void setName(String name) {
        this.name = name;
    }

    public ObservableList<Group> getGroupList(int semester) {
        return FXCollections.unmodifiableObservableList(groups.get(semester));
    }

    public void addGroup(Group group) {
        groups.computeIfAbsent(group.getSemester(), semester -> FXCollections.observableArrayList()).add(group);
    }
    public void removeGroup(Group group) {
        int semester = group.getSemester();
        groups.get(semester).remove(group);
        if (groups.get(semester).isEmpty()) groups.remove(semester);
    }

    public ObservableList<Teacher> getTeacherList() {
        return FXCollections.unmodifiableObservableList(teachers);
    }

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }
    public void removeTeacher(Teacher teacher) { // check validation
        if (!teachers.contains(teacher)) return;
        teachers.remove(teacher);
    }

    public ObservableList<Subject> getSubjectList(int semester) {
        if (!subjects.containsKey(semester)) return null;
        return FXCollections.unmodifiableObservableList(subjects.get(semester));
    }

    public void addSubject(Subject subject) {
        this.subjects.computeIfAbsent(subject.getSemester(), semester -> FXCollections.observableArrayList()).add(subject);
    }
    public void removeSubject(Subject subject) {
        if (!subjects.containsKey(subject.getSemester())) return;
        subjects.get(subject.getSemester()).remove(subject);
        if (subjects.get(subject.getSemester()).isEmpty()) subjects.remove(subject.getSemester());
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Degree degree = (Degree) object;
        return Objects.equals(name, degree.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}