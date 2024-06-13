<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ include file="topMenu.jsp" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration Page</title>
<link href="styles.css" rel="stylesheet" type="text/css" >
</head>
<body>


<div class="contentLogin">
<form id="register-form" class="register-form" autocomplete="off" action="<%=request.getContextPath()%>/user" method="post">
<input type="hidden" name="action" value="loginUser" />
<h1 class="a11y-hidden">Login Form</h1>
  <input type="checkbox" name="show-password" class="show-password a11y-hidden" id="show-password" tabindex="2" />
  <label class="label-show-password" for="show-password">
    <span>Show Password</span>
  </label>
  <div>
    <label class="label-username">
      <input type="text" class="text" name="username" placeholder="Username" tabindex="1" required />
      <span class="required">Username</span>
    </label>
  </div> 
  <div>
    <label class="label-password">
      <input type="text" class="text" name="password" placeholder="Password" tabindex="3" required />
      <span class="required">Password</span>
    </label>
  </div>
   	
  <input type="submit" value="Login"/>
  
  
  
  </form>

  <br>
</body>
</html>