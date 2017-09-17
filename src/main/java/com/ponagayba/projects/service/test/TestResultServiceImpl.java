package com.ponagayba.projects.service.test;

import com.ponagayba.projects.dao.test.TestResultDAO;
import com.ponagayba.projects.model.test.TestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class TestResultServiceImpl implements TestResultService {

    @Autowired
    private TestResultDAO testResultDAO;

    @Override
    public void addTestResult(TestResult testResult) {
        testResultDAO.create(testResult);
    }

    @Override
    public List<TestResult> getUserResults(Integer id) {
        return testResultDAO.getUserResults(id);
    }

    @Override
    public void deleteUserTestResults(int userId) {
        testResultDAO.deleteUserTestResults(userId);
    }
}
