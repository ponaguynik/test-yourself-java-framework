<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="add-question1">
    <form action="<c:url value="/admin/questions/edit"/>" method="post" class="flex-container config-form1">
        <input type="hidden" name="questionId" value="${requestScope.question.id}">
        <input type="hidden" name="optionsNum" value="${fn:length(requestScope.question.options)}">
        <input type="hidden" name="optionType" value="${requestScope.question.optionType}">
        <label for="question-input">Question:</label>
        <input class="text" id="question-input" type="text" name="question" maxlength="200" value="${requestScope.question.question}" required>
        <c:if test="${requestScope.question.code != null}">
            <label for="code-input">Code:</label>
            <textarea id="code-input" name="code" class="code-field" rows="10" cols="50" maxlength="1000">
                <c:out value="${requestScope.question.code}"/>
            </textarea>
        </c:if>
        <label>Options:</label>
        <c:forEach begin="1" end="${fn:length(requestScope.question.options)}" varStatus="count">
            <div class="hor1">
                <c:choose>
                    <c:when test="${fn:contains(requestScope.question.correctAnswers, count.count)}">
                        <input type="${requestScope.question.optionType}" name="option" value="${count.count}" checked>
                    </c:when>
                    <c:otherwise>
                        <input type="${requestScope.question.optionType}" name="option" value="${count.count}">
                    </c:otherwise>
                </c:choose>
                <input class="text" type="text" name="option${count.count}" value="${requestScope.question.options.get(count.count-1)}" maxlength="200" required>
            </div>
        </c:forEach>
        <input class="btn submit-btn" type="submit" value="Update">
    </form>
</div>