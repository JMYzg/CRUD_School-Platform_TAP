package com.tap.schoolplatform.models.academic.tasks;

import com.tap.schoolplatform.models.academic.tasks.enums.AnswerType;

public class Answer {
    AnswerType type;
    String text;

    Answer(AnswerType type, String text) {
        this.type = type;
        this.text = text;
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
