<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@ include file="topMenu4.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Programs</title>
<link href="styles.css" rel="stylesheet" type="text/css">
<%@ include file="topMenu4.jsp" %>
<style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th {
            background-color: black;
            color: white;
        }
        td {
            background-color: #333;
            color: white;
        }
        th, td {
            border: 1px solid white;
            padding: 10px;
            text-align: left;
        }
        button {
            padding: 5px 10px;
            cursor: pointer;
        }
        .already {
            color: hsl(var(--bgColorH), var(--bgColorS), var(--bgColorL));
            font-size: 0.80rem;
            font-weight: bold;
            margin-top: 0.625rem;
            order: 4;
            outline: 1px dashed transparent;
            outline-offset: 2px;
            padding-left: 0;
            text-transform: uppercase;
        }
        
        .already:focus {
            outline: 1px dashed hsl(var(--fgColorH), calc(var(--fgColorS) * 2), calc(var(--fgColorL) * 1.15));
            outline-offset: 2px;
        }
        
        .already:focus {
            background: hsl(var(--fgColorH), var(--fgColorS), calc(var(--fgColorL) * 0.85));
        }
        
        .already:hover {
            background: hsl(var(--fgColorH), var(--fgColorS), calc(var(--fgColorL) * 0.85));
        }
        
        .already:active {
            background: hsl(var(--fgColorH), calc(var(--fgColorS) * 2), calc(var(--fgColorL) * 1.15));
            transition: all 0.125s;
        }
</style>
</head>
<body>
<div class="content">
    <h2>Edit Programs</h2>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Minutes</th>
                <th>Base Charge</th>
                <th>Additional Charge</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="program" items="${programs}">
                <tr>
                    <form id="edit-form-${program.id}" action="<%=request.getContextPath()%>/AdminServlet" method="post">
                        <input type="hidden" name="action" value="edit_programs" />
                        <td><input type="number" name="id" value="${program.id}" readonly></td>
                        <td><input type="text" name="name" value="${program.name}"></td>
                        <td><input type="number" name="minutes" value="${program.minutes}"></td>
                        <td><input type="number" name="baseCharge" value="${program.baseCharge}"></td>
                        <td><input type="number" name="additionalCharge" value="${program.additionalCharge}"></td>
                        <td>
                            <button type="submit">Save</button>
                        </td>
                    </form>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>

