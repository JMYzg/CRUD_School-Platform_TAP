package com.tap.schoolplatform.models.academic;

import com.tap.schoolplatform.models.academic.enums.Shift;
import com.tap.schoolplatform.models.academic.keys.GroupKey;
import com.tap.schoolplatform.models.users.Student;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private final String groupId;
    private final List<Student> students = new ArrayList<>();
    private final List<Subject> subjects = new ArrayList<>();

    public Group(int index, Degree degree, GroupKey groupKey) {
        this.groupId = generateId(index, degree, groupKey);
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

    public String getGroupId () {
        return groupId;
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
