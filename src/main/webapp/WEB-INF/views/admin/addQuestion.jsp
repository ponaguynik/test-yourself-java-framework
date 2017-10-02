<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<jsp:include page="../header.jsp">
    <jsp:param name="css" value="admin/addQuestion.css" />
    <jsp:param name="title" value="Add Question" />
</jsp:include>
<jsp:include page="adminMenu.jsp"/>
<main class="flex-container">
<c:if test="${message != null}">
    <p style="color: green;">${message}</p>
</c:if>
<div class="add-question">
    <h2><spring:message code="label.question"/>:</h2>
    <div class="hor">
        <label for="code-cb"><spring:message code="label.code.field"/>:</label>
        <input id="code-cb" type="checkbox">
    </div>
    <div class="hor">
        <label for="num-select"><spring:message code="label.num.options"/>:</label>
        <select id="num-select">
            <option value="2" selected>2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
        </select>
    </div>
    <springForm:form action="/admin/questions/add" method="post" class="flex-container config-form" modelAttribute="question">
        <label for="question" class="margin-top margin-bottom">Question:</label>
        <springForm:input path="question" class="text" type="text" maxlength="200" required="true"/>
        <div id="code-container">
            <label for="code"><spring:message code="label.code"/>:</label>
            <springForm:textarea path="code" class="code-field" rows="10" cols="50" maxlength="1000"/>
        </div>
        <label id="options-label" class="margin-top"><spring:message code="label.options"/>:</label>
        <div id="options">

        </div>
        <input class="btn submit-btn" type="submit" value="<spring:message code="label.save"/>">
    </springForm:form>
</div>
</main>
<script src="${pageContext.request.contextPath}/resources/js/script.js"></script>
<jsp:include page="../footer.jsp" />