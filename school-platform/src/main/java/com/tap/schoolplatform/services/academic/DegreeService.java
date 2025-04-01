package com.tap.schoolplatform.services.academic;

import com.tap.schoolplatform.models.academic.*;
import com.tap.schoolplatform.models.academic.enums.Shift;
import com.tap.schoolplatform.models.academic.keys.GroupKey;
import com.tap.schoolplatform.models.users.Teacher;
import com.tap.schoolplatform.services.Service;
import com.tap.schoolplatform.utils.*;

public class DegreeService extends Service {

    private Degree degree;

    public DegreeService(Degree degree) {
        this.degree = degree;
    }

    public Degree getDegree() {
        return degree;
    }
    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public void createGroup(int semester, Shift shift) {
        if (semester == 0 || shift == null) throw new IllegalArgumentException("Semester and Shift are required");
        Group group = new Group(degree, semester, shift);
        degree.addGroup(group);
    }

    public Group readGroup(int semester, String id) {
        for (Group group : degree.getGroupList(semester)) {
            if (group.getId().equals(id)) {
                return group;
            }
        }
        return null;
    }

    public void updateGroup(Group group, GroupDTO groupDTO) {
        if (groupDTO.getDegree() != null) {
            group.setDegree(groupDTO.getDegree());
            degree.removeGroup(group.getSemester(), group);
            group.getDegree().addGroup(group);
        }
        if (groupDTO.getKey() != null) {
            degree.removeGroup(group);
            group.setKey(groupDTO.getKey());
            degree.addGroup(group);
        }
        if (groupDTO.getShift() != null) {
            group.setShift(groupDTO.getShift());
        }
    }

    public void deleteGroup(Group group) {
        this.degree.removeGroup(group);
    }

    // Teacher Management
    public void createTeacher(Teacher teacher, String license, String specialization) {
        teacher.setLicense(license);
        teacher.setDegree(degree);
        teacher.setSpecialization(specialization);
        degree.addTeacher(teacher);
    }

    public Teacher readTeacher(String license) {
        for (Teacher teacher : degree.getTeacherList()) {
            if (teacher.getLicense().equals(license)) {
                return teacher;
            }
        }
        return null;
    }

    public void updateTeacher(Teacher teacher, UserDTO userDTO) {
        if (userDTO.getLicense() != null) teacher.setLicense(userDTO.getLicense());
        if (userDTO.getDegree() != null) {
            teacher.setDegree(userDTO.getDegree());
            degree.removeTeacher(teacher);
            teacher.getDegree().addTeacher(teacher);
        }
        if (userDTO.getSpecialization() != null) {
            teacher.setSpecialization(userDTO.getSpecialization());
        }
    }

    public void deleteTeacher(Teacher teacher) {
        degree.removeTeacher(teacher);
    }

    // Subject management
    public void createSubject(int semester, String name, String description) {
        Subject subject = new Subject(degree, semester, name, description);
        degree.addSubject(subject);
    }

    public  Subject readSubject(int semester, String name) {
        for (Subject subject : degree.getSubjectList(semester)) {
            if (subject.getName().equals(name)) {
                return subject;
            }
        }
        return null;
    }

    public void updateSubject(Subject subject, SubjectDTO subjectDTO) {
        if (subjectDTO.getDegree() != null) {
            subject.setDegree(subjectDTO.getDegree());
            degree.removeSubject(subject);
            subject.getDegree().addSubject(subject);
    }
        if (subjectDTO.getSemester() != 0) {
            degree.removeSubject(subject);
            subject.setSemester(subjectDTO.getSemester());
            degree.addSubject(subject);
        }
        if (subjectDTO.getName() != null) subject.setName(subjectDTO.getName());
        if (subjectDTO.getDescription() != null) subject.setDescription(subjectDTO.getDescription());
    }

    public void deleteSubject(Subject subject) {
        degree.removeSubject(subject);
    }
}
