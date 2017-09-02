<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../header.jsp">
    <jsp:param name="css" value="admin.css" />
    <jsp:param name="title" value="Admin" />
</jsp:include>
<aside class="flex-container">
    <h2>Manage</h2>
    <ul>
        <li><a href="<c:url value="/admin/users"/>">Users</a></li>
        <li><a href="<c:url value="/admin/questions"/>">Questions</a></li>
    </ul>
</aside>
<main class="flex-container">
    <jsp:include page="${requestScope.page}.jsp"/>
</main>
<jsp:include page="../footer.jsp" />
