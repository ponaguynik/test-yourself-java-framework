<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../header.jsp">
    <jsp:param name="css" value="results.css" />
    <jsp:param name="title" value="My Results" />
</jsp:include>
<c:choose>
    <c:when test="${requestScope.results != null}">
        <table class="align-center">
            <thead>
            <tr>
                <th>№</th>
                <th>Date</th>
                <th>Time</th>
                <th>Result</th>
                <th class="last">Duration</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.results}" var="result" varStatus="count">
                <tr>
                    <td>${count.count}</td>
                    <td>${result.date}</td>
                    <td>${result.time}</td>
                    <td>${result.result}</td>
                    <td>${result.duration}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <p class="align-center">No results yet</p>
    </c:otherwise>
</c:choose>
<jsp:include page="../footer.jsp" />
