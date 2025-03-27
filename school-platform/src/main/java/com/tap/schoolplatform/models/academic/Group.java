package com.tap.schoolplatform.models.academic;

import com.tap.schoolplatform.models.academic.enums.Shift;
import com.tap.schoolplatform.models.academic.keys.GroupKey;
import com.tap.schoolplatform.models.users.Student;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private final String groupId;
    private Degree degree;
    private GroupKey key;
    private final List<Student> students = new ArrayList<>();
    private final List<Subject> subjects = new ArrayList<>();

    public Group(int index, Degree degree, GroupKey key) {
        this.degree = degree;
        this.key = key;
        this.groupId = generateId(index, degree, key);
    }

    private String generateId(int index, Degree degree, GroupKey groupKey) {
        StringBuilder degreeInitials = new StringBuilder();
        for (Character character : degree.getName().toCharArray()) {
            if (Character.isUpperCase(character)) {
                degreeInitials.append(character);
            }
        }
        String shift = groupKey.getShift() == Shift.MORNINGS ? "M" : "E";
        return String.format("%d%s-%d%s",
                groupKey.getSemester(),
                degreeInitials,
                index,
                shift
        );
    }

    public String getId() {
        return groupId;
    }

    public Degree getDegree () {
        return degree;
    }
    public void setDegree (Degree degree) {
        this.degree = degree;
    }

    public GroupKey getKey() {
        return key;
    }
    public void setKey(GroupKey key) {
        this.key = key;
    }

    public List<Student> getStudents() {return students;}
    public void addStudents(List<Student> students) {this.students.addAll(students);}

    public Student getStudent(int index) {return students.get(index);}
    public void addStudent(Student student) {
        students.add(student);
    }

    public List<Subject> getSubjects() {return subjects;}
    public void addSubjects(List<Subject> subjects) {this.subjects.addAll(subjects);}

    public Subject getSubject(int index) {return subjects.get(index);}
    public void addSubject(Subject subject) {this.subjects.add(subject);}
}
