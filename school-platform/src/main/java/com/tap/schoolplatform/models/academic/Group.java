package com.tap.schoolplatform.models.academic;

import com.tap.schoolplatform.models.academic.enums.Shift;
import com.tap.schoolplatform.models.academic.keys.GroupKey;
import com.tap.schoolplatform.models.users.Student;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private String groupId;
    private Degree degree;
    private GroupKey key;
    private final List<Student> students = new ArrayList<>(); // Convert to a set

    public Group(Degree degree, GroupKey key) { // Check to add instantaneously to the degree when create group
        this.degree = degree;
        this.key = key;
        generateId();
    }

    private void generateId() {
        int index = degree.getGroupList(key).size(); // Check index
        StringBuilder degreeInitials = new StringBuilder();
        for (Character character : degree.getName().toCharArray()) {
            if (Character.isUpperCase(character)) {
                degreeInitials.append(character);
            }
        }
        String shift = key.shift() == Shift.MORNINGS ? "M" : "E";
        groupId = String.format("%d%s-%d%s",
                key.semester(),
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
        generateId();
    }

    public GroupKey getKey() {
        return key;
    }
    public void setKey(GroupKey key) {
        this.key = key;
        generateId();
    }

    public List<Student> getStudentList() {return students;}
    public void addStudents(List<Student> students) {this.students.addAll(students);}

    public Student getStudent(int index) {return students.get(index);}
    public void addStudent(Student student) {
        students.add(student);
    }

    public List<Subject> getSubjectList() {return degree.getSubjectList(key.semester());}

    public Subject getSubject(int index) {
        return degree.getSubjectList(key.semester()).get(index);
    }
}
