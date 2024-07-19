<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="mainpackage.utils.model.Bill" %>
<!DOCTYPE html>
<%@ include file="topMenu2.jsp" %>
<html>
<head>
<title>My Bill</title>
<link href="styles.css" rel="stylesheet" type="text/css">
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
        color: black;
        background: white;
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
    .already:hover {
        background:  #ADD8E6;
    }
    .already:active {
        background: hsl(var(--fgColorH), calc(var(--fgColorS) * 2), calc(var(--fgColorL) * 1.15));
        transition: all 0.125s;
    }
    .myform {
    	background-color: #333;
    }
</style>
</head>
<body>

<% 
    Bill bill = (Bill) request.getAttribute("bill"); 
    if (bill != null) { 
%>
<div class="content">
    <table>
        <tr>
            <th>Bill ID</th>
            <th>Username</th>
            <th>Billing Month</th>
            <th>Number of Calls</th>
        </tr>
        <tr>
            <td><%= bill.getBill_id() %></td>
            <td><%= bill.getUsernameOfClient() %></td>
            <td><%= bill.getBillingMonth() %></td>
            <td><%= bill.getNumberOfCalls() %></td>
        </tr>
    </table>
    <form class="myform" action="ClientServlet" method="post">
        <input type="hidden" name="action" value="payBill">
        <input type="hidden" name="bill_id" value="<%= bill.getBill_id() %>">
        <button type="submit" class="already">Pay Bill</button>
    </form>
<% } else { %>
    <table>
        <tr>
            <th colspan="4">No bill found for this user.</th>
        </tr>
    </table>
</div>
<% } %>

</body>
</html>
