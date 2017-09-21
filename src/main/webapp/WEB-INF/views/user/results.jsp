<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://my.custom/functions" prefix="format" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../header.jsp">
    <jsp:param name="css" value="results.css" />
    <jsp:param name="title" value="My Results" />
</jsp:include>
<c:choose>
    <c:when test="${results != null and not empty results}">
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
            <c:forEach items="${results}" var="result" varStatus="count">
                <tr>
                    <td>${count.count}</td>
                    <td>${format:formatLocalDateTime(result.dateTime, 'dd.MM.yyyy')}</td>
                    <td>${format:formatLocalDateTime(result.dateTime, 'HH:mm:ss')}</td>
                    <td>${result.answeredNum}/${result.questionsNum}</td>
                    <td>${format:formatDuration(result.duration)}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <p class="align-center"><spring:message code="results.no.results"/></p>
    </c:otherwise>
</c:choose>
<jsp:include page="../footer.jsp" />
