<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script>
    function deleteConf(qnId) {
        if (confirm("Do you want to delete the question?")) {
            document.getElementById('deleteQuestionForm' + qnId).submit();
        }
    }
</script>
<a class="add-qn-btn" href="<c:url value="/admin/questions/add"/>">Add New Question</a>
<table>
    <tr>
        <th class="th-num">№</th>
        <th class="th-question">Question</th>
        <th class="th-options">Options</th>
        <th class="th-answers">Answer</th>
        <th class="th-actions">Actions</th>
    </tr>
    <c:forEach var="question" items="${requestScope.questions}" varStatus="qnCount">
        <tr>
            <td class="td-num"><c:out value="${qnCount.index+1}"/></td>
            <td><c:out value="${question.question}"/></td>
            <td>
                <c:forEach var="option" items="${question.options}" varStatus="count">
                    <c:choose>
                        <c:when test="${count.index == fn:length(question.options)-1}">
                            <c:out value="${option}"/>
                        </c:when>
                        <c:otherwise>
                            <c:out value="${option} | "/>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </td>
            <td>
                <c:forEach var="answer" items="${question.correctAnswers}" varStatus="count">
                    <c:choose>
                        <c:when test="${count.index == fn:length(question.correctAnswers)-1}">
                            <c:out value="${answer}"/>
                        </c:when>
                        <c:otherwise>
                            <c:out value="${answer}, "/>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </td>
            <td class="actions">
                <a class="edit-btn" href="
                    <c:url value="/admin/questions/edit">
                            <c:param name="questionId" value="${question.id}"/>
                    </c:url>">Edit</a>
                <form id="deleteQuestionForm${question.id}" action="<c:url value="/admin/questions/delete"/>" method="post">
                    <input type="hidden" name="questionId" value="${question.id}">
                </form>
                <button class="delete-btn" name="deleteQuestion" onclick="deleteConf(${question.id})" value="delete">Delete</button>
            </td>
        </tr>
    </c:forEach>
</table>