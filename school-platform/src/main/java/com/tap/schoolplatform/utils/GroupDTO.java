package com.tap.schoolplatform.utils;

import com.tap.schoolplatform.models.academic.Degree;
import com.tap.schoolplatform.models.academic.enums.Shift;
import com.tap.schoolplatform.models.academic.keys.GroupKey;

public class GroupDTO {

    private Degree degree;
    private int semester;
    private Shift shift;

    public Degree getDegree() {
        return degree;
    }
    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public int getSemester() {
        return semester;
    }
    public void setSemester(int semester) {
        this.semester = semester;
    }

    public Shift getShift() {
        return shift;
    }
    public void setShift(Shift shift) {
        this.shift = shift;
    }
}
