package com.tap.schoolplatform.services.academic;

import com.tap.schoolplatform.models.academic.tasks.Answer;
import com.tap.schoolplatform.models.academic.tasks.Question;
import com.tap.schoolplatform.models.academic.tasks.enums.AnswerType;
import com.tap.schoolplatform.utils.AnswerDTO;

public class QuestionService {

    private Question question;

    public QuestionService(Question question) {
        this.question = question;
    }

    public Question getQuestion() {
        return question;
    }
    public void setQuestion(Question question) {
        this.question = question;
    }

    public void createAnswer(AnswerType type, String text) {
        Answer answer = new Answer(type, text);
        question.addAnswer(answer);
    }

    public Answer readAnswer(int index) {
       return question.getAnswer(index);
    }

    public void updateAnswer(Answer answer, AnswerDTO answerDTO) {
        if (answerDTO.getType() != null) answer.setType(answerDTO.getType());
        if (answerDTO.getText() != null) answer.setText(answerDTO.getText());
    }

    public void deleteAnswer(Answer answer) {
        question.removeAnswer(answer);
    }
}
