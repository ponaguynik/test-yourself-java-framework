<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../header.jsp">
    <jsp:param name="css" value="admin/manageUser.css" />
    <jsp:param name="title" value="Manage User" />
</jsp:include>
<jsp:include page="adminMenu.jsp"/>
<main class="flex-container">
        <springForm:form cssClass="flex-container" action="/admin/users/manage/update" method="post" modelAttribute="updatedUser">
            <div class="flex-container user-content">
                <div class="flex-container user-form">
                    <c:if test="${error != null}">
                        <p style="color: red;">${error}</p>
                        <br>
                    </c:if>
                    <springForm:label path="username" for="username-input"><spring:message code="label.username"/>:</springForm:label>
                    <springForm:input path="username" id="username-input" type="text" value="${user.username}" maxlength="15" required="true"/>
                    <br>
                    <springForm:label path="email" for="email-input"><spring:message code="label.email"/>:</springForm:label>
                    <springForm:input path="email" id="email-input" type="email" value="${user.email}" maxlength="30" required="true"/>
                </div>
                <div class="flex-container role-container">
                    <div class="roles">
                        <springForm:checkboxes path="roles" items="${roles}" itemLabel="name" itemValue="name" element="div" cssClass="role-item"/>
                    </div>
                </div>
            </div>
            <input type="hidden" name="userId" value="${user.id}">
            <input class="update-btn" type="submit" value="Update">
        </springForm:form>
    <springForm:form cssClass="flex-container change-password-form" action="/admin/users/manage/password" method="post">
        <h2><spring:message code="change.password"/></h2>
        <c:if test="${passwordError != null}">
            <p style="color: red;">${passwordError}</p>
            <br>
        </c:if>
        <label for="password"><spring:message code="label.password"/>:</label>
        <input type="password" id="password" name="password">
        <label for="new-password"><spring:message code="label.new.password"/>:</label>
        <input type="password" id="new-password" name="newPassword">
        <input class="change-btn" type="submit" value="<spring:message code="change"/>">
        <input type="hidden" name="userId" value="${user.id}">
    </springForm:form>
</main>
<jsp:include page="../footer.jsp" />