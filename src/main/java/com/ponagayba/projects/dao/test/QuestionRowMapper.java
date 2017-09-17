package com.ponagayba.projects.dao.test;

import com.ponagayba.projects.model.test.Question;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class QuestionRowMapper implements RowMapper<Question> {

    @Override
    public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
        Question result = new Question();
        List<String> options = Arrays.asList(rs.getString("options").split("&"));
        List<String> answer = Arrays.asList(rs.getString("answer").split("&"));
        result.setId(rs.getInt("id"));
        result.setQuestion(rs.getString("question"));
        result.setCode(rs.getString("code"));
        result.setOptions(options);
        result.setAnswers(answer);
        return result;
    }
}
