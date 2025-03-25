package com.tap.schoolplatform.models.academic;

import com.tap.schoolplatform.models.academic.keys.GroupKey;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Degree {
    private String degreeId;
    private String degreeName;
    private Map<GroupKey, List<Group>> groups = new HashMap<>();

    public Degree(String degreeId, String degreeName) {
        this.degreeId = degreeId;
        this.degreeName = degreeName;
    }

    public String getDegreeId() {
        return degreeId;
    }
    public void setDegreeId(String degreeId) {
        this.degreeId = degreeId;
    }

    public String getDegreeName() {
        return degreeName;
    }
    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    public void addGroup(GroupKey key, Group group) {
        groups.computeIfAbsent(key, k -> new ArrayList<>()).add(group);
    }

    public List<Group> getGroups(GroupKey key) {
        return groups.get(key);
    }
}
