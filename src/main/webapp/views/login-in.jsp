<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="jakarta.servlet.http.Cookie" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/login.css">
<title>Login</title>
</head>

<body class="fade-in">

	<div class="card">
		<h2>Welcome</h2>

		<form id="loginForm" action="/ShoppingSite/LoginAction/login" method="post" onsubmit="return validateForm()">
			
			<%
			String savedId = "";

			Cookie[] cookies = request.getCookies();

			if (cookies != null) {
				for (Cookie c : cookies) {
					if ("member_id".equals(c.getName())) {
				savedId = c.getValue();
					}
				}
			}
			%>
			
			<div>
				<label>メンバーID</label> 
				<input type="text" name="member_id" value="<%=savedId%>">
			</div>

			<br>

			<div class="password-wrapper">
				<label>パスワード</label> <input type="password" id="password" name="password">

				<p id="error-message"></p>

				<button type="button" id="togglePassword" class="eye-button">
					<img id="eyeIcon" src="../img/eye-slash-regular-full.svg" alt="password">
				</button>
			</div>

			<div class="remember">
				<label> <input type="checkbox" name="remember">
					メンバーIDを保持する
				</label>
			</div>

			<br>

			<button class="primary" type="submit">Login</button>
			<button class="secondary" type="button">新規会員登録</button>
		</form>

	</div>

	<script src="../js/login.js"></script>
</body>
</html>