package com.ponagayba.projects.dao.test;

import com.ponagayba.projects.dao.AbstractDAO;
import com.ponagayba.projects.model.test.Question;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionDAOImpl extends AbstractDAO implements QuestionDAO {

    @Override
    public List<Question> getAll() {
        String query =
                "SELECT id, question, code, options, option_type, answer " +
                "FROM test_yourself.question;";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Question.class));
    }

    @Override
    public void addQuestion(Question question) {
        String options = toStoringForm(question.getOptions());
        String answer = toStoringForm(question.getCorrectAnswers());
        String query =
                "INSERT INTO test_yourself.question(question, code, options, option_type, answer)" +
                "VALUES(?, ?, ?, ?, ?);";
        jdbcTemplate.update(query, question.getQuestion(), question.getCode(), options, question.getOptionType(),
                answer);
    }

    @Override
    public void delete(int questionId) {
        String query =
                "DELETE FROM test_yourself.question " +
                "WHERE id=?;";
        jdbcTemplate.update(query, questionId);
    }

    @Override
    public Question findById(int questionId) {
        String query =
                "SELECT id, question, code, options, option_type, answer " +
                "FROM test_yourself.question " +
                "WHERE id=?;";
        return jdbcTemplate.queryForObject(query, new Object[] {questionId}, new QuestionRowMapper());
    }

    @Override
    public void update(Question question) {
        String options = toStoringForm(question.getOptions());
        String answer = toStoringForm(question.getCorrectAnswers());
        String query =
                "UPDATE test_yourself.question " +
                "SET question=?, code=?, options=?, option_type=?, answer=? " +
                "WHERE id=?;";
        jdbcTemplate.update(query, question.getQuestion(), question.getCode(), options, question.getOptionType(),
                answer, question.getId());
    }

    private String toStoringForm(List<String> items) {
        if (items.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String item : items) {
            sb.append(item).append("&");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
