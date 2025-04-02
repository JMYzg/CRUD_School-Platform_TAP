package com.tap.schoolplatform.utils.dtos.academic;

import com.tap.schoolplatform.models.academic.Degree;
import com.tap.schoolplatform.models.academic.enums.Shift;

public class GroupDTO {

    private Degree degree;
    private Integer semester;
    private Shift shift;

    public Degree getDegree() {
        return degree;
    }
    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public Integer getSemester() {
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

