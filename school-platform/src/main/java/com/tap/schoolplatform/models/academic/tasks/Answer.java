package com.tap.schoolplatform.models.academic.tasks;

import com.tap.schoolplatform.models.academic.tasks.enums.AnswerType;

public class Answer {

    private int index;
    private AnswerType type;
    private String text;

    public Answer(AnswerType type, String text) {
        this.type = type;
        this.text = text;
    }

    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }

    public AnswerType getType() {
        return type;
    }
    public void setType(AnswerType type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
}
