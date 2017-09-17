package com.ponagayba.projects.model.test;


import java.time.LocalDateTime;
import java.util.List;

public class TestResult {

    private Integer id;
    private Integer userId;
    private LocalDateTime dateTime;
    private Long duration;
    private Integer questionsNum;
    private Integer correctAnswersNum;

    private List<Question> questions;
    private Integer percent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Integer getQuestionsNum() {
        return questionsNum;
    }

    public void setQuestionsNum(Integer questionsNum) {
        this.questionsNum = questionsNum;
    }

    public Integer getCorrectAnswersNum() {
        return correctAnswersNum;
    }

    public void setCorrectAnswersNum(Integer correctAnswersNum) {
        this.correctAnswersNum = correctAnswersNum;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }
}
