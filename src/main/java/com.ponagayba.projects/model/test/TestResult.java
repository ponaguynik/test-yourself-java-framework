package com.ponagayba.projects.model.test;


import java.util.List;

public class TestResult {

    private Integer userId;
    private List<Question> questions;
    private String result;
    private String unansweredNum;
    private String date;
    private String time;
    private String duration;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUnansweredNum() {
        return unansweredNum;
    }

    public void setUnansweredNum(String unansweredNum) {
        this.unansweredNum = unansweredNum;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
