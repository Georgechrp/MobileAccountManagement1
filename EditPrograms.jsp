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
    <h2>Edit Program</h2>
     <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Minutes</th>
                <th>Base Charge</th>
                <th>Additional Charge</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="program" items="${programs}">
			    <tr>
			        <td>${program.id}</td>
			        <td>${program.name}</td>
			        <td>${program.minutes}</td>
			        <td>${program.baseCharge}</td>
			        <td>${program.additionalCharge}</td>
			    </tr>
			</c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
