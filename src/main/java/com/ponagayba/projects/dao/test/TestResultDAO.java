package com.ponagayba.projects.dao.test;

import com.ponagayba.projects.model.test.TestResult;

import java.sql.SQLException;
import java.util.List;

public interface TestResultDAO {

    void create(TestResult testResult);

    List<TestResult> getUserResults(Integer userId);

    void deleteUserTestResults(int userId);
}
