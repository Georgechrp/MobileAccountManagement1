<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@ include file="topMenu3.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Seller Page</title>
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
<%@ include file="topMenu3.jsp" %>
</head>
<body>
<div class="content">
    <h2>Program List</h2>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Minutes</th>
                <th>Base Charge</th>
                <th>Additional Charge</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="program" items="${programs}">
			    <tr>
			        <td>${program.id}</td>
			        <td>${program.name}</td>
			        <td>${program.minutes}</td>
			        <td>${program.baseCharge}</td>
			        <td>${program.additionalCharge}</td>
			    </tr>
			</c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>