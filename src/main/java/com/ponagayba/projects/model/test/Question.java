package com.ponagayba.projects.model.test;

import com.ponagayba.projects.model.converter.QuestionConverter;

import javax.persistence.*;
import java.util.List;

@Table(name = "question", schema = "test_yourself")
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "question", columnDefinition = "clob")
    private String question;

    @Column(name = "code", columnDefinition = "clob")
    private String code;

    @Convert(converter = QuestionConverter.class)
    @Column(name = "options", columnDefinition = "clob")
    private List<String> options;

    @Convert(converter = QuestionConverter.class)
    @Column(name = "answer")
    private List<String> correctAnswers;

    @Transient
    private int num;

    @Transient
    private List<String> answers;

    @Transient
    private boolean correct;

    @Transient
    private boolean answered;

    @Transient
    private boolean active;

    public Question() {
    }

    public Question(String question, String code, List<String> options, List<String> correctAnswers) {
        this.question = question;
        this.code = code;
        this.options = options;
        this.correctAnswers = correctAnswers;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public List<String> getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(List<String> correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public boolean isActive() {
        return active;
    }
}
