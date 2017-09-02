<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="add-question1">
    <form action="<c:url value="/admin/questions/add"/>" method="post" class="flex-container config-form1">
        <input type="hidden" name="optionType" value="${requestScope.optionType}">
        <input type="hidden" name="optionsNum" value="${requestScope.optionsNum}">
        <input type="hidden" name="configured" value="true">
        <label for="question-input">Question:</label>
        <input class="text" id="question-input" type="text" name="question" maxlength="200" required>
        <c:if test="${requestScope.withCode}">
            <label for="code-input">Code:</label>
            <textarea id="code-input" name="code" class="code-field" rows="10" cols="50" maxlength="1000"></textarea>
        </c:if>
        <label>Options:</label>
        <c:forEach begin="1" end="${requestScope.optionsNum}" varStatus="count">
            <div class="hor1">
                <input type="${requestScope.optionType}" name="option" value="${count.count}">
                <input class="text" type="text" name="option${count.count}" maxlength="200" required>
            </div>
        </c:forEach>
        <input class="btn submit-btn" type="submit" value="Submit">
    </form>
</div>