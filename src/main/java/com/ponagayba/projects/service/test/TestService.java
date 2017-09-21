package com.ponagayba.projects.service.test;

import com.ponagayba.projects.model.User;
import com.ponagayba.projects.model.test.Answer;
import com.ponagayba.projects.model.test.Question;
import com.ponagayba.projects.model.test.Test;
import com.ponagayba.projects.model.test.TestResult;

import java.util.List;

public interface TestService {

    TestResult generateTestResult(Test test);

    int percentageOfCorrectAnswers(List<Question> questions);

    Test prepareTest(User user);

    void answerQuestion(Question question, Answer answer);

    void resetAnswer(Question question);

    void addTestResult(TestResult testResult);
}
