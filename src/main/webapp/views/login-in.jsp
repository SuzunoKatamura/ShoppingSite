<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/login.css">
<title>Login</title>
</head>

<body class = "fade-in">

<div class = "card">
	<h2>Welcome</h2>
	
	<form id = "loginForm" action = "/ShoppingSite/LoginAction/login" method = "post">
		<div>
			<label>ID</label>
			<input type = "text" name = "member_id">
		</div>
		
		<br>
		
		<div>
			<label>パスワード</label>
			<input type = "password" name = "password">
		</div>
		
		<br>
		
		<button class = "primary" type = "submit" onclick = "login()">Login</button>
		<button class = "secondary" type = "button"  onclick="goRegister()">新規会員登録</button>
	</form>
	
</div>

<script src="../js/login.js"></script>
</body>
</html>