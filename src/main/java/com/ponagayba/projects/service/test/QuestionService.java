package com.ponagayba.projects.service.test;

import com.ponagayba.projects.model.test.Question;

import java.sql.SQLException;
import java.util.List;

public interface QuestionService {

    List<Question> getAll() ;

    List<Question> getRandomQuestions(List<Question> questions, int num);

    void processAnswers(Question question, List<String> answers);

    void resetAnswers(Question question);

    void addQuestion(Question question) ;

    void deleteQuestion(int questionId) ;

    Question findById(int questionId) ;

    void updateQuestion(Question question) ;
}
