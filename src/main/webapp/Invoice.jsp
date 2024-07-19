<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@ include file="topMenu3.jsp" %>
<html>
<head>
<title>Invoices</title>
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
