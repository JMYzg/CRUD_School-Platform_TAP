package com.tap.schoolplatform.utils;

import com.tap.schoolplatform.models.academic.Degree;
import com.tap.schoolplatform.models.academic.keys.GroupKey;

public class GroupDTO {

    private Degree degree;
    private GroupKey key;

    public Degree getDegree() {
        return degree;
    }
    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public GroupKey getKey() {
        return key;
    }
    public void setKey(GroupKey key) {
        this.key = key;
    }
}
