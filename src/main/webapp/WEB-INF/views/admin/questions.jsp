<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../header.jsp">
    <jsp:param name="css" value="admin/questions.css" />
    <jsp:param name="title" value="Questions" />
</jsp:include>
<jsp:include page="adminMenu.jsp"/>
<script>
    function deleteConf(qnId) {
        if (confirm("Do you want to delete the question?")) {
            document.getElementById('deleteQuestionForm' + qnId).submit();
        }
    }
</script>
<main class="flex-container">
<a class="add-qn-btn" href="<c:url value="/admin/questions/add"/>">Add New Question</a>
    <c:if test="${message != null}">
        <p class="message">${message}</p>
    </c:if>
    <c:if test="${error != null}">
        <p class="error">${error}</p>
    </c:if>
<table>
    <tr>
        <th class="th-num">№</th>
        <th class="th-question">Question</th>
        <th class="th-options">Options</th>
        <th class="th-answers">Answer</th>
        <th class="th-actions">Actions</th>
    </tr>
    <c:forEach var="question" items="${questions}" varStatus="qnCount">
        <tr>
            <td class="td-num">${qnCount.index+1}</td>
            <td>${question.question}</td>
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
                <springForm:form id="deleteQuestionForm${question.id}" action="/admin/questions/delete" method="post">
                    <input type="hidden" name="id" value="${question.id}">
                </springForm:form>
                <button class="delete-btn" name="deleteQuestion" onclick="deleteConf(${question.id})" value="delete">Delete</button>
            </td>
        </tr>
    </c:forEach>
</table>
</main>
<jsp:include page="../footer.jsp" />