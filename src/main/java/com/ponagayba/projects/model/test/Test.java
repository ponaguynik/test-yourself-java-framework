package com.ponagayba.projects.model.test;


import java.util.ArrayList;
import java.util.List;

public class Test {

    private Integer userId;
    private List<Question> questions = new ArrayList<>();
    private Question currentQn;
    private long startTime;

    public List<Question> getQuestions() {
        return questions;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Question getCurrentQn() {
        return currentQn;
    }

    public void setCurrentQn(Question currentQn) {
        this.currentQn = currentQn;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
}
