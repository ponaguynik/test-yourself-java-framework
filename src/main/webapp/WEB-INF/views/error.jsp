<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp">
    <jsp:param name="css" value="error.css" />
    <jsp:param name="title" value="Error" />
</jsp:include>
    <div class="error-container">
        <p class="error">${statusCode}</p>
        <c:choose>
        <c:when test="${statusCode == 404}">
            <p class="desc">Page not found.</p>
        </c:when>
        <c:when test="${statusCode == 403}">
            <p class="desc">Access denied.</p>
        </c:when>
        <c:otherwise>
            <p class="desc">Oops! Something went wrong.</p>
        </c:otherwise>
    </c:choose>
    </div>
<jsp:include page="footer.jsp" />
