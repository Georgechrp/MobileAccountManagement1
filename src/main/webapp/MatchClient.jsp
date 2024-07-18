<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@ include file="topMenu3.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Match Client to Program</title>
<link href="styles.css" rel="stylesheet" type="text/css">
<style>
    .container {
        display: flex;
        justify-content: space-between;
        max-width: 600px;
        margin: 0 auto;
    }
    .dropdown {
        flex: 1;
        margin: 10px;
    }
</style>
</head>
<body>
    <div class="container">
        <form action="<%=request.getContextPath()%>/SellerServlet" method="post">
            <input type="hidden" name="action" value="matchClientProgram">
            <div class="dropdown">
                <label for="client">Select Client:</label>
                <select name="client" id="client">
                    <c:forEach var="client" items="${clients}">
                        <option value="${client.AFM}">
                            ${client.username} - ${client.phoneNumber.number} - program: ${client.phoneNumber.program.id} (${client.AFM})
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="dropdown">
                <label for="program">Select Program:</label>
                <select name="program" id="program">
                    <c:forEach var="program" items="${programs}">
                        <option value="${program.id}">
                            ${program.id} - ${program.name}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <input type="submit" value="Match">
            </div>
        </form>
    </div>
</body>
</html>
