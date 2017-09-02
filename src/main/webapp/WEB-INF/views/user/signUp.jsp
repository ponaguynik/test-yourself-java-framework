<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../header.jsp">
    <jsp:param name="css" value="signUp.css" />
    <jsp:param name="title" value="Sign Up" />
</jsp:include>
<div id="login-form">
    <h2>Sign Up</h2>
        <c:if test="${requestScope.error != null}">
        <br>
        <p style="color: red;"><c:out value="${requestScope.error}"/></p>
    </c:if>
    <form action="<c:url value="/signup"/>" method="post">
        <div id="form-container" class="flex-container">
            <label for="username-input">Username:</label>
            <input id="username-input" type="text" name="username" maxlength="15" required>
            <br>
            <label for="email-input">Email:</label>
            <input id="email-input" type="email" name="email" maxlength="30" required>
            <br>
            <label for="password-input">Password:</label>
            <input id="password-input" type="password" name="password" maxlength="15" required>
            <br>
            <label for="conf-password-input">Confirm Password:</label>
            <input id="conf-password-input" type="password" name="confPassword" maxlength="15" required>
            <br>
            <input id="submit" type="submit" value="Confirm">
            <a href="<c:url value="/login"/>" id="sign-in">Sign In</a>
        </div>
    </form>
</div>
<jsp:include page="../footer.jsp" />
