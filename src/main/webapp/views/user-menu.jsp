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
				<form action="${pageContext.request.contextPath}/update/user-edit" method="get">
				    <input type="hidden" name="member_id" value="${customer.member_id}">
				    <input type="submit" class="edit" value="修正">
				</form>
				
				<form action="${pageContext.request.contextPath}/update/user-delete" method="post">
					<input type="hidden" name="member_id" value="${customer.member_id}">
				    <input type="submit" class="delete" value="削除" onclick="return confirm('本当に削除しますか？');">
				</form>
			</div>


		</div>

	</div>

	<script src="../js/user-menu.js"></script>
</body>
</html>