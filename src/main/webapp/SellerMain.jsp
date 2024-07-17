<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ include file="topMenu3.jsp" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Client Page</title>
<link href="styles.css" rel="stylesheet" type="text/css" >
</head>
<body>
<div class="content">
        <%
        String username = (String) session.getAttribute("username");

        if ( username != null) {
    %>
    <h1>Welcome, <%= username %>!</h1>
    <%
        } else {
    %>
    <p>Please <a href="login.jsp">login</a>.</p>
    <%
        }
    %>
        </div>


</body>
</html>