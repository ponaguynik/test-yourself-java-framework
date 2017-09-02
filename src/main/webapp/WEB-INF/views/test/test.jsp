<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../header.jsp">
    <jsp:param name="css" value="test.css" />
    <jsp:param name="js" value="highlighter" />
    <jsp:param name="title" value="Test" />
</jsp:include>
<script type="text/javascript">
    function startTest() {
        return confirm("Do you want to start the test?");
    }

    function finishTest() {
        if (confirm("Do you want to finish the test?")) {
            document.getElementById("finishTestForm").submit();
        }
    }

    function finishTestConfirm() {
        if (confirm("${requestScope.finishMessage}")) {
            document.getElementById("finishTestAnyway").submit();
        }
    }
</script>
<c:if test="${sessionScope.test.questions == null}">
    <form id="testForm" action="<c:url value="/test"/>" method="get">
    </form>
    <form id="homeForm" action="<c:url value="/home"/>" method="get">
    </form>
    <script type="text/javascript">
        if (startTest()) {
            document.getElementById("testForm").submit();
        } else {
            document.getElementById("homeForm").submit();
        }
    </script>
</c:if>
<aside class="questions">
    <form action="<c:url value="/test"/>" method="post">
        <c:forEach items="${sessionScope.test.questions}" var="question">
            <c:choose>
                <c:when test="${question.active}">
                    <input class="btn active" type="submit" name="qnNum" value="${question.num}">
                </c:when>
                <c:otherwise>
                    <input class="btn" type="submit" name="qnNum" value="${question.num}">
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </form>
    <form id="finishTestForm" action="<c:url value="/test/finish"/>" method="post"></form>
    <button class="btn finish-btn" onclick="finishTest()" value="finish">Finish</button>
    <c:if test="${requestScope.finishMessage != null}">
        <form id="finishTestAnyway" action="<c:url value="/test/finish"/>" method="post">
            <input type="hidden" name="finishTestAnyway" value="true">
        </form>
        <script type="text/javascript">
            finishTestConfirm();
        </script>
    </c:if>
</aside>
<main class="question-main">
    <div class="question">
        <p><c:out value="${sessionScope.test.currentQn.question}"/></p>
        <c:if test="${sessionScope.test.currentQn.code != null}">
            <br>
            <pre><code class="java"><c:out value="${sessionScope.test.currentQn.code}"/></code></pre>
        </c:if>
    </div>
    <br>
    <div class="answer">
        <c:if test="${requestScope.message != null}">
            <p style="color: red"><c:out value="${requestScope.message}"/></p>
            <br>
        </c:if>
        <c:choose>
            <c:when test="${!sessionScope.test.currentQn.answered}">
                <form action="<c:url value="/test/answer"/>" method="post">
                    <c:forEach items="${sessionScope.test.currentQn.options}" var="item" varStatus="count">
                        <input id="opt${count.index+1}" type="${sessionScope.test.currentQn.optionType}" name="answer" value="${count.index+1}">
                        <label for="opt${count.index+1}">${item}</label>
                        <br>
                    </c:forEach>
                    <br>
                    <input class="btn answer-btn" type="submit" value="Answer">
                </form>
            </c:when>
            <c:otherwise>
                <form action="<c:url value="/test/cancel"/>" method="post">
                    <c:forEach items="${sessionScope.test.currentQn.options}" var="item" varStatus="count">
                        <c:set var="contains" value="false" />
                        <c:forEach var="itm" items="${sessionScope.test.currentQn.answers}">
                            <c:if test="${count.index+1 eq itm}">
                                <c:set var="contains" value="true"/>
                            </c:if>
                        </c:forEach>
                        <c:choose>
                            <c:when test="${contains}">
                                <input id="opt${count.index+1}" type="${sessionScope.test.currentQn.optionType}" name="answer" value="${count.index+1}" checked disabled>
                            </c:when>
                            <c:otherwise>
                                <input id="opt${count.index+1}" type="${sessionScope.test.currentQn.optionType}" name="answer" value="${count.index+1}" disabled>
                            </c:otherwise>
                        </c:choose>
                        <label for="opt${count.index+1}">${item}</label>
                        <br>
                    </c:forEach>
                    <br>
                    <input class="btn answer-btn" type="submit" value="Cancel">
                </form>
            </c:otherwise>
        </c:choose>
    </div>
</main>
<jsp:include page="../footer.jsp" />
