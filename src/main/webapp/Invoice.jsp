<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@ include file="topMenu3.jsp" %>
<html>
<head>
<title>Invoices</title>
<link href="styles.css" rel="stylesheet" type="text/css">
</head>
<body>
    <h2>Invoice for Customer</h2>
    <table border="1">
        <tr>
            <th>Bill ID</th>
            <th>Username</th>
            <th>Billing Month</th>
            <th>Number of Calls</th>
            <th>Paid</th>
        </tr>
        <c:forEach var="bill" items="${bills}">
            <tr>
                <td>${bill.bill_id}</td>
                <td>${bill.usernameOfClient}</td>
                <td>${bill.billingMonth}</td>
                <td>${bill.numberOfCalls}</td>
                <td>${bill.paid ? 'Yes' : 'No'}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
