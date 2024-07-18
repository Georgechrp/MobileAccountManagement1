<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@ include file="topMenu4.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Create Programs Page</title>
<link href="styles.css" rel="stylesheet" type="text/css">
<%@ include file="topMenu4.jsp" %>
</head>
<body>
<div class="content">

    <h2>Create New Program</h2>
   
<div class="contentRegister">
<form id="register-form" class="register-form" autocomplete="off" action="<%=request.getContextPath()%>/AdminServlet" method="post">
<input type="hidden" name="action" value="register" />
  
  <div>
    <label class="name">
      <input type="text" class="text" name="name" placeholder="Program Name" tabindex="4" required />
      <span class="required">Program Name</span>
    </label>
  </div>
  <div>
    <label class="id">
      <input type="number" class="text" name="id" placeholder="Program ID" tabindex="4" required />
      <span class="required">Program ID</span>
    </label>
  </div>
  <div>
    <label class="minutes">
      <input type="number" class="text" name="minutes" placeholder="Minutes" tabindex="4" required />
      <span class="required">Minutes</span>
    </label>
  </div>
  <div>
    <label class="baseCharge">
      <input type="number" class="text" name="baseCharge" placeholder="Base Charge" tabindex="5" required />
      <span class="required">Base Charge</span>
    </label>
  </div>
  <div>
    <label class="additionalCharge">
      <input type="number" class="text" name="additionalCharge"placeholder="Additional Charge" tabindex="1" required />
      <span class="required">Additional Charge:</span>
    </label>
  </div> 
  
   	
  <input type="submit" value="Create Program" />
  
  
  
  </form>
  

  <br>
  </div>
</body>
</html>