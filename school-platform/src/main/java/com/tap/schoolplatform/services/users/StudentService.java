package com.tap.schoolplatform.services.users;

import com.tap.schoolplatform.models.academic.Degree;

public class StudentService {

    private Degree degree;

    public StudentService(Degree degree) {
        this.degree = degree;
    }

    public Degree getDegree() {
        return degree;
    }
    public void setDegree(Degree degree) {
        this.degree = degree;
    }
}
