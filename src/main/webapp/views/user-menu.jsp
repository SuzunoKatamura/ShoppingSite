<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/user-menu.css">
<title>User menu</title>
</head>

<body class="fade-in">

	<div class="header">
		<button class="logout" type="button"
			onclick="location.href='/ShoppingSite/LoginAction/logout'">Logout</button>
	</div>

	<p class="msg">ようこそ、${customer.last_name}さん！</p>

	<div class="container">

		<div class="card">

			<div class="action">
				<button class="edit" type="submit">修正</button>
				<button class="delete" type="submit">削除</button>
			</div>


		</div>

	</div>

	<script src="../js/user-menu.js"></script>
</body>
</html>