package com.ponagayba.projects.service.test;

import com.ponagayba.projects.model.test.Question;
import com.ponagayba.projects.model.test.Test;
import com.ponagayba.projects.model.test.TestResult;

import java.util.List;

public interface TestService {

    TestResult generateTestResult(Test test);

    int numberOfUnansweredQuestions(List<Question> questions);

    int percentageOfCorrectAnswers(List<Question> questions);
}
