package com.ponagayba.projects.dao.test;

import com.ponagayba.projects.model.test.Question;

import java.sql.SQLException;
import java.util.List;

public interface QuestionDAO {

    List<Question> getAll() throws SQLException;

    void addQuestion(Question question) throws SQLException;

    void delete(int questionId) throws SQLException;

    Question findById(int questionId) throws SQLException;

    void update(Question question) throws SQLException;
}
