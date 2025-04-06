package com.tap.schoolplatform.models.academic;

import com.tap.schoolplatform.models.academic.enums.Semester;
import com.tap.schoolplatform.models.users.Teacher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Degree {
    private String name;
    private final Map<Semester, ObservableList<Group>> groupLists = new HashMap<>();
    private final ObservableList<Teacher> teacherList = FXCollections.observableArrayList();
    private final Map<Semester, ObservableList<Subject>> subjectLists = new HashMap<>();

    public Degree(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ObservableList<Group> getGroupList(Semester semester) {
        if (!groupLists.containsKey(semester)) throw new IllegalArgumentException("Semester " + semester + " not found");
        return FXCollections.unmodifiableObservableList(groupLists.get(semester));
    }

    public void addGroup(Group group) {
        ObservableList<Group> groupList =
                groupLists.computeIfAbsent(group.getSemester(), semester ->
                FXCollections.observableArrayList());
        if (!groupList.contains(group)) groupList.add(group);
        else throw new IllegalArgumentException("Group already exists");
    }
    public void removeGroup(Group group) {
        Semester semester = group.getSemester();
        if (!groupLists.containsKey(semester)) return;
        if (groupLists.get(semester).contains(group)) {
            groupLists.get(semester).remove(group);
            if (groupLists.get(semester).isEmpty()) groupLists.remove(semester);
        }
    }

    public ObservableList<Teacher> getTeacherList() {
        return FXCollections.unmodifiableObservableList(teacherList);
    }

    public void addTeacher(Teacher teacher) {
        if (!teacherList.contains(teacher)) teacherList.add(teacher);
        else throw new IllegalArgumentException("Teacher already exists");
    }

    public void removeTeacher(Teacher teacher) { // check validation
        if (!teacherList.contains(teacher)) return;
        teacherList.remove(teacher);
    }

    public ObservableList<Subject> getSubjectList(Semester semester) {
        if (!subjectLists.containsKey(semester)) throw new IllegalArgumentException("Semester" + semester + " not found");
        return FXCollections.unmodifiableObservableList(subjectLists.get(semester));
    }

    public void addSubject(Subject subject) {
        ObservableList<Subject> subjectList =
                subjectLists.computeIfAbsent(subject.getSemester(), semester ->
                FXCollections.observableArrayList());
        if (!subjectList.contains(subject)) subjectList.add(subject);
        else throw new IllegalArgumentException("Subject already exists");
    }

    public void removeSubject(Subject subject) {
        Semester semester = subject.getSemester();
        if (!subjectLists.containsKey(semester)) return;
        if (subjectLists.get(semester).contains(subject)) {
            subjectLists.get(semester).remove(subject);
            if (subjectLists.get(semester).isEmpty()) subjectLists.remove(subject.getSemester());
        }
    }

    @Override
    public String toString() {
        return name;
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