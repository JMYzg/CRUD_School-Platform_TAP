package com.tap.schoolplatform.models.academic;

public class Grade {
    private Double score;

    public Grade(double score) {
        this.score = score;
    }

    public Double getGrade() {
        return score;
    }
    public void setGrade(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return Double.toString(score);
    }

}
