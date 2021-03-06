<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp">
    <jsp:param name="css" value="error.css" />
    <jsp:param name="title" value="Error" />
</jsp:include>
    <div class="error-container">
        <p class="error-code">${errorCode}</p>
        <c:if test="${message != null}">
            <p class="error-msg">${message}</p>
        </c:if>
    </div>
<jsp:include page="footer.jsp" />
