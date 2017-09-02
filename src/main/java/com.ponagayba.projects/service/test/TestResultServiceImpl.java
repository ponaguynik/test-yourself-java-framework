package com.ponagayba.projects.service.test;

import com.ponagayba.projects.dao.test.TestResultDAO;
import com.ponagayba.projects.model.test.TestResult;

import java.sql.SQLException;
import java.util.List;

public class TestResultServiceImpl implements TestResultService {

    private final TestResultDAO testResultDAO;

    public TestResultServiceImpl(TestResultDAO testResultDAO) {
        this.testResultDAO = testResultDAO;
    }

    @Override
    public void addTestResult(TestResult testResult) throws SQLException {
        testResultDAO.addTestResult(testResult);
    }

    @Override
    public List<TestResult> getUserResults(Integer id) throws SQLException {
        return testResultDAO.getUserResults(id);
    }

    @Override
    public void deleteUserTestResults(int userId) throws SQLException {
        testResultDAO.deleteUserTestResults(userId);
    }
}
