package com.tap.schoolplatform.utils;

import com.tap.schoolplatform.models.academic.tasks.enums.AnswerType;

public class AnswerDTO {

    private AnswerType type;
    private String text;

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
