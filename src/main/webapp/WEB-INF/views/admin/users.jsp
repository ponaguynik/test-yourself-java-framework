<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    function deleteConf(userId) {
        if (confirm("Do you want to delete the user?")) {
            document.getElementById('deleteUserForm' + userId).submit();
        }
    }
</script>
<table>
    <tr>
        <th>№</th>
        <th>Username</th>
        <th>Email</th>
        <th>Roles</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="user" items="${requestScope.users}" varStatus="userCount">
        <tr>
            <td class="td-num"><c:out value="${userCount.index+1}"/></td>
            <td><c:out value="${user.username}"/></td>
            <td><c:out value="${user.email}"/></td>
            <td>
                <c:forEach var="role" items="${user.roles}">
                    <c:out value="${role.name} "/>
                </c:forEach>
            </td>
            <td class="actions">
                <a class="edit-btn" href="
                    <c:url value="/admin/users/manage-user">
                            <c:param name="userId" value="${user.id}"/>
                    </c:url>">Edit</a>
                <form id="deleteUserForm${user.id}" action="<c:url value="/admin/users/delete-user"/>" method="post">
                    <input type="hidden" name="userId" value="${user.id}">
                </form>
                <button class="delete-btn" name="deleteUser" onclick="deleteConf(${user.id})" value="delete">Delete</button>
            </td>
        </tr>
    </c:forEach>
</table>