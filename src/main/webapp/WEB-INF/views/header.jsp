<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
        <a href="<c:url value="/home"/>"><img src="${pageContext.request.contextPath}/resources/images/testyourself-logo.png" alt="TestYourself-logo"></a>
    </header>
    <nav class="flex-container">
        <a href="<c:url value="/home"/>">Home</a>
        <a href="<c:url value="/test/start"/>">Test</a>
        <a href="<c:url value="/results"/>">My Results</a>
        <a href="<c:url value="/about"/>">About</a>
        <c:choose>
            <c:when test="${requestScope.user != null}">
                <c:if test="${requestScope.user.admin}">
                    <a href="<c:url value="/admin/users"/>">Admin Panel</a>
                </c:if>
                <form class="login-menu-item" action="<c:url value="/logout"/> " method="post">
                    <input type="image" src="${pageContext.request.contextPath}/resources/images/logout.png" alt="Logout">
                </form>
            </c:when>
            <c:otherwise>
                <a href="<c:url value="/login"/>" class="login-menu-item"><img src="${pageContext.request.contextPath}/resources/images/login.png" alt="Login"></a>
            </c:otherwise>
        </c:choose>
    </nav>
    <section class="flex-container">
