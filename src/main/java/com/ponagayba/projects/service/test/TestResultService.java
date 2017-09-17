package com.ponagayba.projects.service.test;

import com.ponagayba.projects.model.test.TestResult;

import java.sql.SQLException;
import java.util.List;

public interface TestResultService {

    void addTestResult(TestResult testResult);

    List<TestResult> getUserResults(Integer id);

    void deleteUserTestResults(int userId);
}
