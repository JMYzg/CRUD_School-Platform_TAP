package com.tap.schoolplatform.services.academic;

import com.tap.schoolplatform.models.academic.tasks.Answer;
import com.tap.schoolplatform.models.academic.tasks.Evaluation;
import com.tap.schoolplatform.models.academic.tasks.Question;
import com.tap.schoolplatform.utils.dtos.academic.tasks.QuestionDTO;

import java.util.List;

public class EvaluationService {

    private Evaluation evaluation;

    public EvaluationService(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }
    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    public void createQuestion(Question question, List<Answer> answers) {
        evaluation.addQuestion(question);
        for (Answer answer : answers) {
            question.addAnswer(answer);
        }
    }

    public Question readQuestion(int index) {
        return List.copyOf(evaluation.getQuestionSet()).get(index);
    }

    public void updateQuestion(Question question, QuestionDTO questionDTO) {
        if (questionDTO.getDescription() != null) question.setDescription(questionDTO.getDescription());
        if (questionDTO.getAnswerSet() != null) {
            for (Answer answer : questionDTO.getAnswerSet()) {
                question.addAnswer(answer);
            }
        }
    }

    public void deleteQuestion(Question question) {
        evaluation.removeQuestion(question);
    }
}
