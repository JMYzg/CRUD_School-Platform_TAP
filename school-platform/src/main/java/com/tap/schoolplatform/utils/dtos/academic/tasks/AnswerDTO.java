package com.tap.schoolplatform.utils.dtos.academic.tasks;

import com.tap.schoolplatform.models.academic.tasks.enums.AnswerType;

public class AnswerDTO {

    private Integer index;
    private AnswerType type;
    private String text;

    public Integer getIndex() {
        return index;
    }
    public void setIndex(Integer index) {
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
