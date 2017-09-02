<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="add-question">
    <h2>Question Configuration</h2>
    <form class="flex-container config-form" id="form" action="<c:url value="/admin/questions/add"/>" method="post">
        <div class="hor">
            <label for="code-cb">Field for code:</label>
            <input id="code-cb" type="checkbox" name="withCode" value="true">
        </div>
        <div class="hor">
            <label for="type-select">Type:</label>
            <select id="type-select" name="optionType">
                <option value="radio" selected>radio</option>
                <option value="checkbox">checkbox</option>
            </select>
        </div>
        <div class="hor">
            <label for="num-select">Number of choices:</label>
            <select id="num-select" name="optionsNum">
                <option value="2" selected>2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
            </select>
        </div>
    </form>
    <input class="btn next-btn" form="form" type="submit" value="Next >>">
</div>