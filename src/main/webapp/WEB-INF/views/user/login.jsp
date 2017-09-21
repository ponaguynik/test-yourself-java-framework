<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="../header.jsp">
    <jsp:param name="css" value="login.css" />
    <jsp:param name="title" value="Login" />
</jsp:include>
<main>
    <h2>Sign In</h2>
    <c:if test="${not empty error}">
        <br>
        <p style="color: red;">${error}</p>
    </c:if>
    <springForm:form action="/j_spring_security_check" method="post">
        <div class="form-container flex-container">
            <label for="username-input">Username:</label>
            <input id="username-input" type="text" name="username" maxlength="15" required>
            <br>
            <label for="password-input">Password:</label>
            <input id="password-input" type="password" name="password" maxlength="15" required>
            <br>
            <input class="btn" type="submit" value="Login">
            <a href="<c:url value="/signup"/>" class="sign-up">Sign Up</a>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    </springForm:form>
</main>
<jsp:include page="../footer.jsp" />