package com.ponagayba.projects.model.test;


import com.ponagayba.projects.model.User;
import com.ponagayba.projects.model.converter.DateTimeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "test_result", schema = "test_yourself")
@Entity
public class TestResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Convert(converter = DateTimeConverter.class)
    @Column(name = "date_time", columnDefinition = "varchar")
    private LocalDateTime dateTime;

    @Column(name = "duration", columnDefinition = "varchar")
    private Long duration;

    @Column(name = "questions_num", columnDefinition = "varchar")
    private Integer questionsNum;

    @Column(name = "answered_num", columnDefinition = "varchar")
    private Integer answeredNum;

    @Transient
    private List<Question> questions;

    @Transient
    private Integer percent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Integer getAnsweredNum() {
        return answeredNum;
    }

    public void setAnsweredNum(Integer answeredNum) {
        this.answeredNum = answeredNum;
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
