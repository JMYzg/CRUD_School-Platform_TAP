package com.tap.schoolplatform.models.academic.tasks;

import com.tap.schoolplatform.models.academic.tasks.enums.QuestionType;
import com.tap.schoolplatform.models.academic.tasks.enums.TaskType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Evaluation extends Task {

    private final Map<QuestionType, List<Question>> questions = new HashMap<>();

    public Evaluation(String title, String description, double score, LocalDate deadline) {
        super(TaskType.EVALUATION, title, description, score, deadline);
    }

    public List<Question> getQuestions(QuestionType type) {
        return questions.get(type);
    }
    public void addQuestion(QuestionType type, Question question) {
        questions.computeIfAbsent(type, t -> new ArrayList<>()).add(question);
    }
}
