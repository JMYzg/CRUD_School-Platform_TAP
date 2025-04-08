package com.tap.schoolplatform.services.academic;

import com.tap.schoolplatform.models.academic.*;
import com.tap.schoolplatform.models.academic.enums.Semester;
import com.tap.schoolplatform.models.academic.enums.Shift;
import com.tap.schoolplatform.models.users.Teacher;
import com.tap.schoolplatform.services.Service;
import com.tap.schoolplatform.utils.dtos.UserDTO;
import com.tap.schoolplatform.utils.dtos.academic.GroupDTO;
import com.tap.schoolplatform.utils.dtos.academic.SubjectDTO;

public class DegreeService extends Service {

    private Degree degree;

    public DegreeService(Degree degree) {
        this.degree = degree;
    }

    public DegreeService() {}

    public Degree getDegree() {
        return degree;
    }
    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public void createGroup(Semester semester, Shift shift) {
        new Group(degree, semester, shift);
    }

    public Group readGroup(Semester semester, String ID) { // discuss this bullshit with Gary and Brisa
        for (Group group : degree.getGroupList(semester)) {
            if (group.getID().equals(ID)) {
                return group;
            }
        }
        return null;
    }

    public void updateGroup(Group group, GroupDTO groupDTO) {
        if (groupDTO.getDegree() != null) group.setDegree(groupDTO.getDegree());
        if (groupDTO.getSemester() != null) group.setSemester(groupDTO.getSemester());
        if (groupDTO.getShift() != null) group.setShift(groupDTO.getShift());
    }

    public void deleteGroup(Group group) {
        this.degree.removeGroup(group);
    }

    // Teacher Management
    public void createTeacher(Teacher teacher, UserDTO userDTO) {
        teacher.setLicense(userDTO.getLicense().trim());
        teacher.setDegree(degree);
        teacher.setSpecialization(userDTO.getSpecialization().trim());
    }

    public void createTeacher(Teacher teacher, String license, String specialization) {
        teacher.setLicense(license.trim());
        teacher.setSpecialization(specialization.trim());
//        this.degree.addTeacher(teacher);
        teacher.setDegree(degree);
        sharedData.getTeachers().remove(teacher);
        sharedData.getTeachers().add(teacher);
    }

    public Teacher readTeacher(String license) { // discuss this bullshit with Gary and Brisa
        if (license == null) throw new IllegalArgumentException("License is required");
        for (Teacher teacher : degree.getTeacherList()) {
            if (teacher.getLicense().equals(license.trim())) {
                return teacher;
            }
        }
        return null;
    }

    public void updateTeacher(Teacher teacher, UserDTO userDTO) {
        if (userDTO.getLicense() != null && !teacher.getLicense().equals(userDTO.getLicense())) teacher.setLicense(userDTO.getLicense().trim());
        if (userDTO.getDegree() != null && !teacher.getDegree().equals(userDTO.getDegree())) teacher.setDegree(userDTO.getDegree());
        if (userDTO.getSpecialization() != null && !teacher.getSpecialization().equals(userDTO.getSpecialization())) teacher.setSpecialization(userDTO.getSpecialization().trim());
        sharedData.getTeachers().remove(teacher);
        sharedData.getTeachers().add(teacher);
    }

    public void deleteTeacher(Teacher teacher) {
        degree.removeTeacher(teacher);
    }

    // Subject management
    public void createSubject(Semester semester, String name) {
        new Subject(degree, semester, name.trim());
    }

    public  Subject readSubject(Semester semester, String name) {
        for (Subject subject : degree.getSubjectList(semester)) {
            if (subject.getName().equals(name.trim())) {
                return subject;
            }
        }
        return null;
    }

    public void updateSubject(Subject subject, SubjectDTO subjectDTO) {
        if (subjectDTO.getName() != null) subject.setName(subjectDTO.getName().trim());
        if (subjectDTO.getDegree() != null) subject.setDegree(subjectDTO.getDegree());
        if (subjectDTO.getSemester() != null) subject.setSemester(subjectDTO.getSemester());
        if (subjectDTO.getTeacher() != null) subject.setTeacher(subjectDTO.getTeacher());
    }

    public void deleteSubject(Subject subject) {
        degree.removeSubject(subject);
    }
}
