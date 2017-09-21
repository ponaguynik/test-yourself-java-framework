<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
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
            <c:when test="${user != null}">
                <span>Hello, <span><c:out value="${user.username}" />!</span></span>
                <br>
                <p>Your last result: <c:out value="${user.lastResult}%"/></p>
                <p>Your best result: <c:out value="${user.bestResult}%"/></p>
                <br>
                <springForm:form action="/j_spring_security_logout" method="post">
                    <input type="submit" value="Logout">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                </springForm:form>
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
