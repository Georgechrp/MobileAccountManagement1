<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="mainpackage.utils.model.Call" %>
<!DOCTYPE html>
<%@ include file="topMenu2.jsp" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Call History</title>
<link href="styles.css" rel="stylesheet" type="text/css">
</head>
<body>
    <%
        
        String username = (String) session.getAttribute("username");

        if (username != null) {
    %>
    <div class="content">
        <h1>Call History for <%= username %></h1>
        <table>
            <tr>
                <th>Call ID</th>
                <th>Caller</th>
                <th>Receiver</th>
                <th>Start Time</th>
                <th>End Time</th>
            </tr>
            <%
                List<Call> callHistory = (List<Call>) request.getAttribute("callHistory");
                if (callHistory != null && !callHistory.isEmpty()) {
                    for (Call call : callHistory) {
            %>
            <tr>
                <td><%= call.getCallId() %></td>
                <td><%= call.getCallerPhoneNumber() %></td>
                <td><%= call.getReceiverPhoneNumber() %></td>
                <td><%= call.getStartTime() %></td>
                <td><%= call.getEndTime() %></td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="5">No call history found.</td>
            </tr>
            <%
                }
            %>
        </table>
    </div>
    <%
        } else {
    %>
    <p>Please <a href="login.jsp">login</a>.</p>
    <%
        }
    %>
</body>
</html>
