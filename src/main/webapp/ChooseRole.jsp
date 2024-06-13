<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ include file="topMenu.jsp" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Choose Role</title>
<link href="styles.css" rel="stylesheet" type="text/css" >
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
            border: 1px solid #000;
            padding: 10px;
            text-align: left;
            
        }
        button {
            padding: 5px 10px;
            cursor: pointer;
        }
</style>
</head>
<body>
<table>
    <thead>
        <tr>
            <th>What type of user you want to register as</th>
            
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>
                <p>Client. Select the button bellow to be registered as a client</p>
                <a href="RegistrationClient.jsp">
				   <input type="button" value="Register as a client" />
				</a>
            </td>
           
            </td>
        </tr>
        <tr>
            <td>
                <p>Seller. Select the button bellow to be registered as a seller</p>
                <a href="RegistrationSeller.jsp">
				   <input type="button" value="Register as a seller" />
				</a>
            </td>
            
            
        </tr>
    </tbody>
</table>

</body>
</html>