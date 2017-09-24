<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../header.jsp">
    <jsp:param name="css" value="admin/users.css" />
    <jsp:param name="title" value="Users" />
</jsp:include>
<jsp:include page="adminMenu.jsp"/>
<script>
    function deleteConf(userId) {
        if (confirm("Do you want to delete the user?")) {
            document.getElementById('deleteUserForm' + userId).submit();
        }
    }
</script>
<main class="flex-container">
<table>
    <tr>
        <th>№</th>
        <th>Username</th>
        <th>Email</th>
        <th>Roles</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="user" items="${users}" varStatus="userCount">
        <tr>
            <td class="td-num">${userCount.index+1}</td>
            <td>${user.username}</td>
            <td>${user.email}</td>
            <td>
                <c:forEach var="role" items="${user.roles}">
                    <c:out value="${role.name} "/>
                </c:forEach>
            </td>
            <td class="actions">
                <a class="edit-btn" href="
                    <c:url value="/admin/users/manage">
                            <c:param name="userId" value="${user.id}"/>
                    </c:url>">Edit</a>
                <springForm:form id="deleteUserForm${user.id}" action="/admin/users/delete" method="post">
                    <input type="hidden" name="userId" value="${user.id}">
                </springForm:form>
                <button class="delete-btn" name="deleteUser" onclick="deleteConf(${user.id})" value="delete">Delete</button>
            </td>
        </tr>
    </c:forEach>
</table>
</main>
<jsp:include page="../footer.jsp" />