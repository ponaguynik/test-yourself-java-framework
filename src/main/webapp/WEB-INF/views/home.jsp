<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp">
    <jsp:param name="css" value="home.css" />
    <jsp:param name="title" value="Home" />
</jsp:include>
<main class="info">
    <article>
        TestYourself Java is a web application used for testing your knowledge in Java language.
    </article>
</main>
<aside class="user-info">
    <div id="user-box" class="flex-container">
        <c:choose>
            <c:when test="${requestScope.user != null}">
                <span>Hello, <span><c:out value="${requestScope.user.username}" />!</span></span>
                <br>
                <p>Your last result: <c:out value="${requestScope.user.lastResult}%"/></p>
                <p>Your best result: <c:out value="${requestScope.user.bestResult}%"/></p>
                <br>
                <form action="<c:url value="/logout"/>" method="post">
                    <input type="submit" value="Logout">
                </form>
            </c:when>
            <c:otherwise>
                <span>Hello, <span>Guest</span>!</span>
                <br>
                <p>Please, login first to pass the test.</p>
                <br>
                <a href="<c:url value="/login"/>">Login</a>
            </c:otherwise>
        </c:choose>

    </div>
</aside>
<jsp:include page="footer.jsp" />
