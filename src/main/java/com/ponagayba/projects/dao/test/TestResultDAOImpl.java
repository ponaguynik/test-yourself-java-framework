package com.ponagayba.projects.dao.test;

import com.ponagayba.projects.dao.AbstractDAO;
import com.ponagayba.projects.model.test.TestResult;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TestResultDAOImpl extends AbstractDAO implements TestResultDAO {

    @Override
    public void addTestResult(TestResult testResult) throws SQLException {
        String query =
                "INSERT INTO test_yourself.test_result(user_id, date, time, result, duration) " +
                "VALUES(?, ?, ?, ?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, testResult.getUserId());
        preparedStatement.setString(2, testResult.getDate());
        preparedStatement.setString(3, testResult.getTime());
        preparedStatement.setString(4, testResult.getResult());
        preparedStatement.setString(5, testResult.getDuration());
        preparedStatement.execute();
    }

    @Override
    public List<TestResult> getUserResults(Integer userId) throws SQLException {
        List<TestResult> result = new ArrayList<>();
        String query =
                "SELECT id, date, time, result, duration " +
                "FROM test_yourself.test_result " +
                "WHERE user_id=?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            TestResult testResult = new TestResult();
            testResult.setUserId(userId);
            testResult.setDate(resultSet.getString("date"));
            testResult.setTime(resultSet.getString("time"));
            testResult.setDuration(resultSet.getString("duration"));
            testResult.setResult(resultSet.getString("result"));
            result.add(testResult);
        }
        return result;
    }

    @Override
    public void deleteUserTestResults(int userId) throws SQLException {
        String query =
                "DELETE FROM test_yourself.test_result " +
                "WHERE user_id=?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, userId);
        preparedStatement.execute();
    }
}
