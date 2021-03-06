package com.ponagayba.projects.service.test;

import com.ponagayba.projects.dao.test.QuestionDAO;
import com.ponagayba.projects.model.test.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


@Transactional
@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionDAO questionDAO;

    @Override
    public List<Question> getAll()  {
        return questionDAO.getAll();
    }

    @Override
    public List<Question> getRandomQuestions(List<Question> questions, int num) {
        List<Question> copy = new ArrayList<>(questions);
        List<Question> result = new ArrayList<>();
        for (int i = 0, random; i < num; i++) {
            if (copy.isEmpty()) {
                break;
            }
            random = ThreadLocalRandom.current().nextInt(0, copy.size());
            Question qsn = copy.get(random);
            qsn.setNum(i + 1);
            if (qsn.getNum() == 1) {
                qsn.setActive(true);
            }
            result.add(qsn);
            copy.remove(random);
        }
        return result;
    }

    @Override
    public void processAnswers(Question question, List<String> answers) {
        question.setAnswers(answers);
        question.setAnswered(true);
        if (answers.equals(question.getCorrectAnswers())) {
            question.setCorrect(true);
        } else {
            question.setCorrect(false);
        }
    }

    @Override
    public void resetAnswers(Question question) {
        question.setAnswered(false);
        question.setAnswers(null);
        question.setCorrect(false);
    }

    @Override
    public void addQuestion(Question question)  {
        questionDAO.create(question);
    }

    @Override
    public void deleteQuestion(Question question)  {
        questionDAO.delete(question);
    }

    @Override
    public Question getById(int questionId)  {
        return questionDAO.getById(questionId);
    }

    @Override
    public void updateQuestion(Question question)  {
        questionDAO.update(question);
    }
}
