package com.tap.schoolplatform.models.academic;

import com.tap.schoolplatform.models.academic.keys.GroupKey;
import com.tap.schoolplatform.models.users.Teacher;

import java.util.*;

public class Degree {
    private String name;
    private final Map<GroupKey, List<Group>> groups = new HashMap<>();
    private final List<Teacher> teachers = new ArrayList<>();
    private final Map<Integer, List<Subject>> subjects = new HashMap<>();

    public Degree(String name) {
        this.name = name;
    }

    public String getName() {return name;}
    public void setName(String name) {
        this.name = name;
    }

    public void removeGroup(Group group) {
        GroupKey oldKey = group.getKey();
        List<Group> oldGroups = groups.get(oldKey);
        if (oldGroups != null) {
            oldGroups.remove(group);
            if (oldGroups.isEmpty()) {
                groups.remove(oldKey);
            }
        }
    }

    public List<Group> getGroupList(GroupKey key) {
        return groups.get(key);
    }
    public void addGroup(Group group) {
        GroupKey key = group.getKey();
        groups.computeIfAbsent(key, k -> new ArrayList<>()).add(group);
    }

    public List<Teacher> getTeacherList() {return Collections.unmodifiableList(teachers);}
    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }
    public void removeTeacher(Teacher teacher) {
        teachers.remove(teacher);
    }

    public List<Subject> getSubjectList(int semester) {
        return Collections.unmodifiableList(subjects.get(semester));
    }
    public void addSubject(Subject subject) {
        int semester = subject.getSemester();
        this.subjects.computeIfAbsent(semester, sem -> new ArrayList<>()).add(subject);
    }
    public void removeSubject(Subject subject) {
        int oldSemester = subject.getSemester();
        List<Subject> oldSubjects = subjects.get(oldSemester);
        if (oldSubjects != null) {
            oldSubjects.remove(subject);
            if (oldSubjects.isEmpty()) {
                subjects.remove(oldSemester);
            }
        }
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