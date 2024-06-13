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
           
            
        </tr>
        <tr>
            <td>
                <p>Seller. Select the button bellow to be registered as a seller</p>
                <a href="RegistrationSeller.jsp">
				   <input type="button" value="Register as a seller" />
				</a>
            </td>
            
            
        </tr>
        <tr>
            <td>
                
                <a class="already" href="LoginPage.jsp">Already Registered?Click Here to Login</a>
            </td>    
            
            
        </tr>
    </tbody>
</table>

</body>
</html>