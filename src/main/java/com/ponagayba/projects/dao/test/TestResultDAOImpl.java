package com.ponagayba.projects.dao.test;

import com.ponagayba.projects.dao.AbstractDAO;
import com.ponagayba.projects.model.test.TestResult;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestResultDAOImpl extends AbstractDAO implements TestResultDAO {

    @Override
    public void create(TestResult testResult) {
        String query =
                "INSERT INTO test_yourself.test_result(user_id, date_time, questions_num, answered_num, duration) " +
                "VALUES(?, ?, ?, ?, ?);";
        jdbcTemplate.update(query, testResult.getUserId(), testResult.getDateTime(), testResult.getQuestionsNum(),
                testResult.getAnsweredNum(), testResult.getDuration());
    }

    @Override
    public List<TestResult> getUserResults(Integer userId) {
        String query =
                "SELECT id, user_id, date_time, questions_num, answered_num, duration " +
                "FROM test_yourself.test_result " +
                "WHERE user_id=?;";
        return jdbcTemplate.query(query, new Object[] {userId}, new BeanPropertyRowMapper<>(TestResult.class));
    }

    @Override
    public void deleteUserTestResults(int userId) {
        String query =
                "DELETE FROM test_yourself.test_result " +
                "WHERE user_id=?;";
        jdbcTemplate.update(query, userId);
    }
}
