<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@ include file="topMenu4.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Create Programs Page</title>
<link href="styles.css" rel="stylesheet" type="text/css">
<%@ include file="topMenu4.jsp" %>
</head>
<body>
<div class="content">
    <h2>Program List</h2>

    <h2>Create New Program</h2>
    <form action="<%=request.getContextPath()%>/ProgramServlet" method="post">
        <label for="name">Program Name:</label>
        <input type="text" id="name" name="name" required>
        <br>
        <label for="minutes">Minutes:</label>
        <input type="number" id="minutes" name="minutes" required>
        <br>
        <label for="baseCharge">Base Charge:</label>
        <input type="number" step="0.01" id="baseCharge" name="baseCharge" required>
        <br>
        <label for="additionalCharge">Additional Charge:</label>
        <input type="number" step="0.01" id="additionalCharge" name="additionalCharge" required>
        <br>
        <input type="submit" value="Create Program">
    </form>
</div>
</body>
</html>