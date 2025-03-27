package com.tap.schoolplatform.services.academic;

import com.tap.schoolplatform.models.academic.Degree;
import com.tap.schoolplatform.models.academic.Group;
import com.tap.schoolplatform.models.academic.enums.Shift;
import com.tap.schoolplatform.models.academic.keys.GroupKey;
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
        Group group = new Group(degree.getGroupList(groupKey).size(), degree, groupKey);
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

    public void updateGroup(Group group, int semester) {

    }
}
