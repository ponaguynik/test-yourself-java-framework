<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/${param.css}">
    <c:if test="${param.js == 'highlighter'}">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/highlighter/default.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/highlighter/github-gist.css">
        <script src="${pageContext.request.contextPath}/resources/js/highlight.pack.js"></script>
        <script>hljs.initHighlightingOnLoad();</script>
    </c:if>
    <title>${param.title}</title>
</head>
<body>
<div id="content">
    <header class="flex-container">
        <a href="<c:url value="/"/>"><img src="${pageContext.request.contextPath}/resources/images/testyourself-logo.png" alt="TestYourself-logo"></a>
    </header>
    <nav class="flex-container">
        <a href="<c:url value="/"/>">Home</a>
        <a href="<c:url value="/test"/>">Test</a>
        <a href="<c:url value="/user/results"/>">My Results</a>
        <a href="<c:url value="/about"/>">About</a>
        <security:authorize access="hasRole('ROLE_ADMIN')">
            <a href="<c:url value="/admin"/>">Admin Panel</a>
        </security:authorize>
        <security:authorize access="isAuthenticated()">
            <springForm:form class="login-menu-item" action="/j_spring_security_logout" method="post">
                <input type="image" src="${pageContext.request.contextPath}/resources/images/logout.png" alt="Logout">
                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}" />
            </springForm:form>
        </security:authorize>
        <security:authorize access="!isAuthenticated()">
            <a href="<c:url value="/login"/>" class="login-menu-item"><img src="${pageContext.request.contextPath}/resources/images/login.png" alt="Login"></a>
        </security:authorize>
    </nav>
    <section class="flex-container">
