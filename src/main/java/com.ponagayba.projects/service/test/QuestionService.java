package com.ponagayba.projects.service.test;

import com.ponagayba.projects.model.test.Question;

import java.sql.SQLException;
import java.util.List;

public interface QuestionService {

    List<Question> getAll() throws SQLException;

    List<Question> getRandomQuestions(List<Question> questions, int num);

    void processAnswers(Question question, List<String> answers);

    void resetAnswers(Question question);

    void addQuestion(Question question) throws SQLException;

    void deleteQuestion(int questionId) throws SQLException;

    Question findById(int questionId) throws SQLException;

    void updateQuestion(Question question) throws SQLException;
}
