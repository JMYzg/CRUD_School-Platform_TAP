package com.tap.schoolplatform.models.academic;

public class Grade {
    private double score;

    public Grade(double score) {
        this.score = score;
    }

    public double getGrade() {
        return score;
    }
    public void setGrade(double score) {
        this.score = score;
    }
}
