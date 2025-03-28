package com.tap.schoolplatform.services.academic;

import com.tap.schoolplatform.models.academic.Degree;
import com.tap.schoolplatform.models.academic.Group;
import com.tap.schoolplatform.models.academic.enums.Shift;
import com.tap.schoolplatform.models.academic.keys.GroupKey;
import com.tap.schoolplatform.models.users.Teacher;
import com.tap.schoolplatform.models.users.User;
import com.tap.schoolplatform.services.Service;
import com.tap.schoolplatform.utils.UserDTO;

import java.util.List;

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
        GroupKey groupKey = new GroupKey(shift, semester);
        Group group = new Group(degree, groupKey);
        degree.addGroup(group);
    }

    public Group readGroup(GroupKey key, String id) {
        for (Group group : degree.getGroupList(key)) {
            if (group.getId().equals(id)) {
                return group;
            }
        }
        return null;
    }

    public List<Group> readGroupList(GroupKey key) {
        return degree.getGroupList(key);
    }

    public void updateGroup(Group group, GroupKey key) {
        degree.updateGroupKey(group, key);
    }

    public void updateGroup(Group group, Degree degree) {
        this.degree.removeGroup(group);
        degree.addGroup(group);
        group.setDegree(degree);
    }

    public void deleteGroup(Group group) {
        this.degree.removeGroup(group);
    }

    // Teacher Management
    public void createTeacher(User user, String license, String specialization) {
        Teacher teacher = (Teacher) user;
        teacher.setLicense(license);
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

    public List<Teacher> readTeacherList(Teacher teacher) {
        return degree.getTeacherList();
    }

    public void updateTeacher(Teacher teacher, UserDTO userDTO) {
        if (userDTO.getLicense() != null) teacher.setLicense(teacher.getLicense());
        if (userDTO.getDegree() != null) teacher.setDegree(teacher.getDegree());
        if (userDTO.getSpecialization() != null) teacher.setSpecialization(teacher.getSpecialization());
    }

    public void deleteTeacher(Teacher teacher) {
        degree.removeTeacher(teacher);
    }
}
