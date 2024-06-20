<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ include file="topMenu3.jsp" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Seller Page</title>
<link href="styles.css" rel="stylesheet" type="text/css" >
</head>
<body>
<div class="content">
        <h2>5x5 Table</h2>
    <table>
        <%
            int rows = 5;
            int columns = 5;
            for(int i = 0; i < rows; i++) {
                out.println("<tr>");
                for(int j = 0; j < columns; j++) {
                    out.println("<td>Cell " + (i+1) + "," + (j+1) + "</td>");
                }
                out.println("</tr>");
            }
        %>
    </table>


</body>
</html>