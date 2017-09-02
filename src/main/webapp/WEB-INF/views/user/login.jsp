<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../header.jsp">
    <jsp:param name="css" value="login.css" />
    <jsp:param name="title" value="Login" />
</jsp:include>
<main>
    <h2>Sign In</h2>
    <c:if test="${requestScope.error != null}">
        <br>
        <p style="color: red;"><c:out value="${requestScope.error}" /></p>
    </c:if>
    <form action="<c:url value="/login"/>" method="post">
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
    </form>
</main>
<jsp:include page="../footer.jsp" />