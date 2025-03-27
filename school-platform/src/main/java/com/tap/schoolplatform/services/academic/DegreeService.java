package com.tap.schoolplatform.services.academic;

import com.tap.schoolplatform.models.academic.Degree;
import com.tap.schoolplatform.models.academic.Group;
import com.tap.schoolplatform.models.academic.enums.Shift;
import com.tap.schoolplatform.models.academic.keys.GroupKey;
import com.tap.schoolplatform.models.enums.Gender;
import com.tap.schoolplatform.models.shared.Address;
import com.tap.schoolplatform.models.shared.BirthDate;
import com.tap.schoolplatform.models.users.Teacher;
import com.tap.schoolplatform.models.users.User;
import com.tap.schoolplatform.services.Service;

import java.util.List;

public class DegreeService extends Service {

    private Degree degree;

    DegreeService() {
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
    public void createTeacher(Degree degree, String license, String specialization, String name, String lastName, BirthDate birthDate, String email, String phone, Address address, Gender gender) {
        Teacher teacher = new Teacher(degree, license, specialization, name, lastName, birthDate, email, phone, address, gender);
    }
}
