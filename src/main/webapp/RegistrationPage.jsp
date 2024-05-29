<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration Page</title>
<link href="styles.css" rel="stylesheet" type="text/css" >
</head>
<body>

<br><br>
<div>
<form id="register-form" class="register-form" autocomplete="off">
<h1 class="a11y-hidden">Registration Form</h1>
  <div>
    <label class="label-username">
      <input type="text" class="text" name="username" placeholder="Username" tabindex="1" required />
      <span class="required">Username</span>
    </label>
  </div>
  <input type="checkbox" name="show-password" class="show-password a11y-hidden" id="show-password" tabindex="2" />
  <label class="label-show-password" for="show-password">
    <span>Show Password</span>
  </label>
  <div>
    <label class="label-password">
      <input type="text" class="text" name="password" placeholder="Password" tabindex="3" required />
      <span class="required">Password</span>
    </label>
  </div>
  <div>
    <label class="label-fname">
      <input type="text" class="text" name="fname" placeholder="fname" tabindex="4" required />
      <span class="required">First Name</span>
    </label>
  </div>
  <div>
    <label class="label-lname">
      <input type="text" class="text" name="lname" placeholder="lname" tabindex="5" required />
      <span class="required">Last Name</span>
    </label>
  </div>
  <div>
  <div>
    <label class="label-radio1">
      <span class="required">Role</span>
    </label>
  </div>
  <br>
  <div>
    <label class="label-radio2">
      <select id="selectid" tabindex="6">
		  <option value="">--Please choose an option--</option>
		  <option value="2">Client</option>
		  <option value="3">Seller</option>
	  </select>
     
      
    </label>
  </div>
  
  <input type="submit" value="Register" />
  </form>
</div>
</body>
</html>