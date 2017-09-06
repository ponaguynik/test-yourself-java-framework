package com.ponagayba.projects.service.test;

import com.ponagayba.projects.model.test.Question;
import com.ponagayba.projects.model.test.Test;
import com.ponagayba.projects.model.test.TestResult;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

@Service
public class TestServiceImpl implements TestService {

    @Override
    public TestResult generateTestResult(Test test) {
        TestResult result = new TestResult();
        result.setUserId(test.getUserId());
        result.setQuestions(test.getQuestions());
        result.setResult(calculateResult(test.getQuestions()));
        result.setUnansweredNum(String.valueOf(numberOfUnansweredQuestions(test.getQuestions())));
        result.setDate(calculateTestFinishDate());
        result.setTime(calculateTestFinishTime());
        result.setDuration(calculateTestDuration(test.getStartTime()));
        return result;
    }

    private String calculateResult(List<Question> questions) {
        int questionsTotal = questions.size();
        int correctAnswers = 0;
        for (Question qn : questions) {
            if (qn.isCorrect()) {
                correctAnswers++;
            }
        }
        int percent = Math.round(correctAnswers / (float) questionsTotal * 100);
        return String.format("%d/%d (%d%%)", correctAnswers, questionsTotal, percent);
    }

    private String calculateTestFinishTime() {
        Date currentDate = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("HH:mm:ss");
        return ft.format(currentDate);
    }

    private String calculateTestFinishDate() {
        Date currentDate = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy");
        return ft.format(currentDate);
    }

    private String calculateTestDuration(long startTime) {
        long currentTime = System.nanoTime();
        long diff = currentTime - startTime;
        Date duration = new Date(TimeUnit.NANOSECONDS.toMillis(diff));
        SimpleDateFormat ft = new SimpleDateFormat("HH:mm:ss");
        ft.setTimeZone(TimeZone.getTimeZone("GMT"));
        return ft.format(duration);
    }

    @Override
    public int percentageOfCorrectAnswers(List<Question> questions) {
        int questionsTotal = questions.size();
        int correctAnswers = 0;
        for (Question qn : questions) {
            if (qn.isCorrect()) {
                correctAnswers++;
            }
        }
        return Math.round(correctAnswers / (float) questionsTotal * 100);
    }

    @Override
    public int numberOfUnansweredQuestions(List<Question> questions) {
        int unansweredCount = 0;
        for (Question question : questions) {
            if (!question.isAnswered()) {
                ++unansweredCount;
            }
        }
        return unansweredCount;
    }
}
