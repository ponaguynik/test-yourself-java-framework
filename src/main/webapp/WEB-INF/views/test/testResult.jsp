<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../header.jsp">
    <jsp:param name="css" value="testResult.css" />
    <jsp:param name="js" value="highlighter" />
    <jsp:param name="title" value="Test Result"/>
</jsp:include>
<main class="flex-container">
    <div class="results">
        <table>
            <thead>
            <tr>
                <th colspan="2">Result: ${testResult.answeredNum}/${testResult.questionsNum}</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>Duration:</td>
                <td class="align-center">${testResult.duration}</td>
            </tr>
            <tr>
                <td>Date:</td>
                <td class="align-center">${testResult.dateTime}</td>
            </tr>
            <tr>
                <td>Time:</td>
                <td class="align-center">${testResult.dateTime}</td>
            </tr>
            </tbody>
        </table>
    </div>
    <c:forEach items="${testResult.questions}" var="question">
        <div class="item">
            <div class="question">
                <div class="question-text flex-container">
                    <p>${question.num}. ${question.question}</p>
                    <c:choose>
                        <c:when test="${question.correct}">
                            <img src="${pageContext.request.contextPath}/resources/images/correct.png" alt="correct">
                        </c:when>
                        <c:otherwise>
                            <img src="${pageContext.request.contextPath}/resources/images/incorrect.png" alt="incorrect">
                        </c:otherwise>
                    </c:choose>
                </div>
                <c:if test="${question.code != null}">
                    <br>
                    <pre><code class="java">${question.code}</code></pre>
                </c:if>
            </div>
            <br>
            <div class="answer">
                <form>
                    <c:set var="idNum" value="1" />
                    <c:forEach items="${question.options}" var="item" varStatus="count">
                        <c:set var="checked" value="false" />
                        <c:set var="correct" value="false" />
                        <c:if test="${question.correctAnswers.contains(item)}">
                            <c:set var="correct" value="true"/>
                        </c:if>
                        <c:if test="${question.answers.contains(item)}">
                            <c:set var="checked" value="true"/>
                        </c:if>
                        <c:choose>
                            <c:when test="${correct}">
                                <div class="image">
                                    <img src="${pageContext.request.contextPath}/resources/images/checked.png">
                                </div>
                            </c:when>
                            <c:when test="${checked and not correct}">
                                <div class="image">
                                    <img src="${pageContext.request.contextPath}/resources/images/cross.png">
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="image">

                                </div>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${checked}">
                                <input id="opt${idNum}" type="checkbox" name="answer" value="${count.index+1}" checked disabled>
                            </c:when>
                            <c:otherwise>
                                <input id="opt${idNum}" type="checkbox" name="answer" value="${count.index+1}" disabled>
                            </c:otherwise>
                        </c:choose>
                        <label for="opt${idNum}">${item}</label>
                        <c:set var="idNum" value="${idNum+1}" />
                        <br>
                    </c:forEach>
                </form>
            </div>
        </div>
    </c:forEach>
    <br>
    <a class="btn" href="/test">Try Again</a>
</main>
<jsp:include page="../footer.jsp" />
