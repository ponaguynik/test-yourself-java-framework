<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../header.jsp">
    <jsp:param name="css" value="signUp.css" />
    <jsp:param name="title" value="Sign Up" />
</jsp:include>
<div id="login-form">
    <h2><spring:message code="sign.up" /></h2>
        <c:if test="${requestScope.error != null}">
        <br>
        <p style="color: red;"><c:out value="${requestScope.error}"/></p>
    </c:if>
    <springForm:form method="post" action="signup" modelAttribute="user">
        <div id="form-container" class="flex-container">
            <springForm:errors path="username" cssClass="error"/>
            <springForm:label path="username" for="username-input"><spring:message code="label.username"/>:</springForm:label>
            <springForm:input path="username" type="text" id="username-input" cssClass="input-field" required="true"/>
            <br>
            <springForm:errors path="email" cssClass="error"/>
            <springForm:label path="email" for="email-input"><spring:message code="label.email"/>:</springForm:label>
            <springForm:input path="email" type="email" id="email-input" cssClass="input-field" required="true"/>
            <br>
            <springForm:errors path="password" cssClass="error"/>
            <springForm:label path="password" for="password-input"><spring:message code="label.password"/>:</springForm:label>
            <springForm:input path="password" type="password" id="password-input" cssClass="input-field" required="true"/>
            <br>
            <springForm:errors cssClass="error"/>
            <springForm:label path="confPassword" for="conf-password-input"><spring:message code="label.confirm.password"/>:</springForm:label>
            <springForm:input path="confPassword" type="password" id="conf-password-input" cssClass="input-field" required="true"/>
            <br>
            <input id="submit" type="submit" value="<spring:message code="button.confirm"/>">
            <a href="<c:url value="/login"/>" id="login"><spring:message code="login"/></a>
        </div>
    </springForm:form>
</div>
<jsp:include page="../footer.jsp" />
