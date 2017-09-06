package com.ponagayba.projects.dao.test;

import com.ponagayba.projects.dao.AbstractDAO;
import com.ponagayba.projects.model.test.Question;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class QuestionDAOImpl extends AbstractDAO implements QuestionDAO {

    @Override
    public List<Question> getAll() throws SQLException {
        String query =
                "SELECT id, question, code, options, option_type, answer " +
                "FROM test_yourself.question;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        List<Question> result = new ArrayList<>();
        while (resultSet.next()) {
            Question question = new Question();
            question.setId(resultSet.getInt("id"));
            question.setQuestion(resultSet.getString("question"));
            question.setCode(resultSet.getString("code"));
            question.setOptions(Arrays.asList(resultSet.getString("options").split("&")));
            question.setOptionType(resultSet.getString("option_type"));
            question.setCorrectAnswers(Arrays.asList(resultSet.getString("answer").split("&")));
            result.add(question);
        }
        return result;
    }

    @Override
    public void addQuestion(Question question) throws SQLException {
        String options = toStoringForm(question.getOptions());
        String answer = toStoringForm(question.getCorrectAnswers());
        String query =
                "INSERT INTO test_yourself.question(question, code, options, option_type, answer)" +
                "VALUES(?, ?, ?, ?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, question.getQuestion());
        preparedStatement.setString(2, question.getCode());
        preparedStatement.setString(3, options);
        preparedStatement.setString(4, question.getOptionType());
        preparedStatement.setString(5, answer);
        preparedStatement.execute();
    }

    @Override
    public void delete(int questionId) throws SQLException {
        String query =
                "DELETE FROM test_yourself.question " +
                "WHERE id=?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, questionId);
        preparedStatement.execute();
    }

    @Override
    public Question findById(int questionId) throws SQLException {
        String query =
                "SELECT id, question, code, options, option_type, answer " +
                "FROM test_yourself.question " +
                "WHERE id=?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, questionId);
        ResultSet resultSet = preparedStatement.executeQuery();
        Question result = null;
        if (resultSet.next()) {
            result = new Question();
            result.setId(resultSet.getInt("id"));
            result.setQuestion(resultSet.getString("question"));
            result.setCode(resultSet.getString("code"));
            result.setOptions(Arrays.asList(resultSet.getString("options").split("&")));
            result.setOptionType(resultSet.getString("option_type"));
            result.setCorrectAnswers(Arrays.asList(resultSet.getString("answer").split("&")));
        }
        return result;
    }

    @Override
    public void update(Question question) throws SQLException {
        String options = toStoringForm(question.getOptions());
        String answer = toStoringForm(question.getCorrectAnswers());
        String query =
                "UPDATE test_yourself.question " +
                "SET question=?, code=?, options=?, option_type=?, answer=? " +
                "WHERE id=?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, question.getQuestion());
        preparedStatement.setString(2, question.getCode());
        preparedStatement.setString(3, options);
        preparedStatement.setString(4, question.getOptionType());
        preparedStatement.setString(5, answer);
        preparedStatement.setInt(6, question.getId());
        preparedStatement.executeUpdate();
    }

    private String toStoringForm(List<String> items) {
        if (items.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String item : items) {
            sb.append(item).append("&");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
