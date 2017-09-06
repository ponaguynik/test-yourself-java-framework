package com.ponagayba.projects.dao.test;

import com.ponagayba.projects.model.test.Question;

import java.sql.SQLException;
import java.util.List;

public interface QuestionDAO {

    List<Question> getAll();

    void addQuestion(Question question);

    void delete(int questionId);

    Question findById(int questionId);

    void update(Question question);
}
