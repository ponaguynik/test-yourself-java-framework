package com.ponagayba.projects.service.test;

import com.ponagayba.projects.model.test.Answer;
import com.ponagayba.projects.model.test.Question;
import com.ponagayba.projects.model.test.Test;
import com.ponagayba.projects.model.test.TestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private QuestionService questionService;

    //User id problem
    @Override
    public TestResult generateTestResult(Test test) {
        TestResult result = new TestResult();
        List<Question> questions = test.getQuestions();
        checkAnswers(questions);
        result.setQuestions(questions);
        LocalDateTime dateTime = LocalDateTime.now();
        result.setDateTime(dateTime);
        result.setDuration(Duration.between(test.getStartDateTime(), dateTime).getSeconds());
        int correctAnswers = countCorrectAnswers(questions);
        result.setCorrectAnswersNum(correctAnswers);
        result.setQuestionsNum(questions.size());
        int percent = Math.round(correctAnswers / (float) questions.size() * 100);
        result.setPercent(percent);
        return result;
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

    @Override
    public Test prepareTest() {
        Test result = new Test();
        List<Question> randomQuestions = questionService.getRandomQuestions(questionService.getAll(), 10);
        result.setQuestions(randomQuestions);
        result.setCurrentQn(randomQuestions.get(0));
        result.setStartDateTime(LocalDateTime.now());
        return result;
    }

    @Override
    public void answerQuestion(Question question, Answer answer) {
        question.setAnswers(answer.getChosenOptions());
        question.setAnswered(true);
    }

    @Override
    public void resetAnswer(Question question) {
        question.setAnswers(new ArrayList<>());
        question.setAnswered(false);
    }

    private int countCorrectAnswers(List<Question> questions) {
        int count = 0;
        for (Question question : questions) {
            if (question.isCorrect()) {
                count++;
            }
        }
        return count;
    }

    private void checkAnswers(List<Question> questions) {
        for (Question question : questions) {
            if (question.getAnswers().equals(question.getCorrectAnswers())) {
                question.setCorrect(true);
            } else {
                question.setCorrect(false);
            }
        }
    }
}
