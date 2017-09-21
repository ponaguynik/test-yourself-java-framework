package com.ponagayba.projects.model.test;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Test {

    private Integer userId;
    private List<Question> questions = new ArrayList<>();
    private Question currentQn;
    private Answer answer;
    private LocalDateTime startDateTime;

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

    public Question getCurrentQn() {
        return currentQn;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public void setCurrentQn(Question currentQn) {
        this.currentQn = currentQn;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }
}
