<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="flex-container user-content">
    <form class="flex-container user-form" action="<c:url value="/admin/users/manage-user/update"/>" method="post">
        <c:if test="${requestScope.error != null}">
            <p style="color: red;"><c:out value="${requestScope.error}"/></p>
            <br>
        </c:if>
        <label for="username-input">Username:</label>
        <input id="username-input" type="text" name="username" value="${requestScope.managedUser.username}" maxlength="15" required>
        <br>
        <label for="email-input">Email:</label>
        <input id="email-input" type="email" name="email" value="${requestScope.managedUser.email}" maxlength="30" required>
        <input type="hidden" name="userId" value="${requestScope.managedUser.id}">
        <input class="update-btn" type="submit" value="Update">
    </form>
    <form class="flex-container role-form" action="<c:url value="/admin/users/manage-user/update-roles"/>" method="post">
        <c:forEach var="role" items="${requestScope.roles}" varStatus="count">
            <div class="flex-container role-item">
                <c:set var="contains" value="false"/>
                <c:forEach var="r" items="${requestScope.managedUser.roles}">
                    <c:if test="${r eq role}">
                        <c:set var="contains" value="true"/>
                    </c:if>
                </c:forEach>
                <c:choose>
                    <c:when test="${contains eq true}">
                        <input id="role${count.index}" type="checkbox" name="role" value="${role.id}" checked>
                    </c:when>
                    <c:otherwise>
                        <input id="role${count.index}" type="checkbox" name="role" value="${role.id}">
                    </c:otherwise>
                </c:choose>
                <label for="role${count.index}"><c:out value="${role.name}"/></label>
            </div>
        </c:forEach>
        <input type="hidden" name="userId" value="${requestScope.managedUser.id}">
        <input class="update-btn" type="submit" value="Update Roles">
    </form>
</div>