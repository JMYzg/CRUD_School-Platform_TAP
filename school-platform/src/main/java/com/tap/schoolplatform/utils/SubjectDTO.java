package com.tap.schoolplatform.utils;

import com.tap.schoolplatform.models.academic.Degree;

public class SubjectDTO {

    private Degree degree;
    private int semester;
    private String name;
    private String description;

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

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
}
