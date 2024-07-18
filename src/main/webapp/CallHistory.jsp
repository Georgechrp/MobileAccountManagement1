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
<style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th {
        
        	background-color:black;
            color:white;
            
        }
        td {
        
        	background-color:#333;
            color:white;
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
