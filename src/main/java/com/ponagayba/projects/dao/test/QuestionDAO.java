package com.ponagayba.projects.dao.test;

import com.ponagayba.projects.model.test.Question;

import java.sql.SQLException;
import java.util.List;

public interface QuestionDAO {

    List<Question> getAll();

    void create(Question question);

    void delete(Question question);

    Question getById(int id);

    void update(Question question);
}
