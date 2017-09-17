<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../header.jsp">
    <jsp:param name="css" value="test.css" />
    <jsp:param name="js" value="highlighter" />
    <jsp:param name="title" value="Test" />
</jsp:include>
<script type="text/javascript">
    function finishTest() {
        if (confirm("<spring:message code="confirm.finish.test"/>")) {
            document.getElementById("finishTestForm").submit();
        }
    }
</script>
<aside class="questions">
    <springForm:form action="/test/question" method="get">
        <c:forEach items="${test.questions}" var="question">
            <c:choose>
                <c:when test="${question.active}">
                    <input class="btn active" type="submit" name="num" value="${question.num}"/>
                </c:when>
                <c:otherwise>
                    <input class="btn" type="submit" name="num" value="${question.num}"/>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </springForm:form>
    <springForm:form id="finishTestForm" action="/test/finish" method="get"/>
    <button class="btn finish-btn" onclick="finishTest()">Finish</button>
</aside>
<main class="question-main">
    <div class="question">
        <p><c:out value="${test.currentQn.question}"/></p>
        <c:if test="${test.currentQn.code != null}">
            <br>
            <pre><code class="java">${test.currentQn.code}</code></pre>
        </c:if>
    </div>
    <br>
    <div class="answer">
        <c:if test="${answerError != null}">
            <p class="error">${answerError}</p>
            <br>
        </c:if>
        <c:choose>
            <c:when test="${!test.currentQn.answered}">
                <springForm:form action="/test/question/answer" method="post" modelAttribute="answer">
                    <springForm:checkboxes path="chosenOptions" items="${test.currentQn.options}" element="div"/>
                    <input class="btn answer-btn" type="submit" value="Answer">
                </springForm:form>
            </c:when>
            <c:otherwise>
                <springForm:form action="/test/question/cancel" method="get" modelAttribute="test.currentQn">
                    <springForm:checkboxes path="answers" items="${test.currentQn.options}" disabled="true" element="div"/>
                    <input class="btn cancel-btn" type="submit" value="Cancel">
                </springForm:form>
            </c:otherwise>
        </c:choose>
    </div>
</main>
<jsp:include page="../footer.jsp" />
