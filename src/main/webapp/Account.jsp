<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="mainpackage.utils.model.Bill" %>
<!DOCTYPE html>
<%@ include file="topMenu2.jsp" %>
<html>
<head>
<title>My Bill</title>
<link href="styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<h1>Account Details</h1>
<% 
    Bill bill = (Bill) request.getAttribute("bill"); 
    if (bill != null) { 
%>
    <h2>Bill Details</h2>
    <table>
        <tr>
            <th>Bill ID</th>
            <td><%= bill.getBill_id() %></td>
        </tr>
        <tr>
            <th>Username</th>
            <td><%= bill.getUsernameOfClient() %></td>
        </tr>
        <tr>
            <th>Billing Month</th>
            <td><%= bill.getBillingMonth() %></td>
        </tr>
        <tr>
            <th>Number of Calls</th>
            <td><%= bill.getNumberOfCalls() %></td>
        </tr>
    </table>
    <form action="ClientServlet" method="post">
        <input type="hidden" name="action" value="payBill">
        <input type="hidden" name="bill_id" value="<%= bill.getBill_id() %>">
        <button type="submit">Pay Bill</button>
    </form>
<% } else { %>
    <h2>Bill Details</h2>
    <table>
        <tr>
            <th colspan="2">No bill found for this user.</th>
        </tr>
    </table>
<% } %>
<a href="index.jsp">Back to Home</a>
</body>
</html>
