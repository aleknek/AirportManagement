<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<link href="/resources/css/style.css" type="text/css" rel="stylesheet"/>
<script src="/resources/js/airport.js"></script>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>

<!DOCTYPE html>
<head>
    <title>Airport management</title>
</head>

<div id="sidebarL">
    <p>
        <c:if test="${currentPage != 'login.jsp' and currentPage != 'home.jsp'}">
            <a class="refHome" href="${CURRENT_MAPPING}/home">Home</a>&nbsp;&nbsp;
        </c:if>
        <c:if test="${currentPage != 'flights.jsp' and currentPage != 'priceList.jsp' and currentPage != 'search.jsp'
         and currentPage != 'login.jsp' and currentPage != 'home.jsp'}">
            <a class="refBack" href="" onclick="history.back();">Back</a>
        </c:if>
    </p>
</div>

<div id="sidebarR">
    <c:if test="${currentPage != 'login.jsp'}">
        <p>
            <a class="refLogout" href="/logout">logout</a>
        </p>
    </c:if>
</div>

<div id="wrapper">
    <div id="content">
        <div class="header">Airport management</div>
        <jsp:include page="${currentPage}" flush="true"></jsp:include>
    </div>
</div>
</html>

