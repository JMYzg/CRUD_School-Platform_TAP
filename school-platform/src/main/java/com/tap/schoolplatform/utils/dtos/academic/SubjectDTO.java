package com.tap.schoolplatform.utils.dtos.academic;

import com.tap.schoolplatform.models.academic.Degree;
import com.tap.schoolplatform.models.users.Teacher;

public class SubjectDTO {

    private String name;
    private Degree degree;
    private Integer semester;
    private Teacher teacher;
    private String description;

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

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

    public Teacher getTeacher() {
        return teacher;
    }
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
}
