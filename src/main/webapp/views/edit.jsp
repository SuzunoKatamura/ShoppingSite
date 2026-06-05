<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/edit.css">
<title>Edit</title>
</head>

<body>


	<div class="container">

		<h2>会員情報編集</h2>

		<form action="${pageContext.request.contextPath}/update/user-update"
			method="post">

			<input type="hidden" name="member_id" value="${customer.member_id}">

		    <p class="password-wrapper">
		    	パスワード*<br>
				<input type="password" id="password" name="password" placeholder="半角英数字で入力してください">
				<button type="button" id="editTogglePassword" class="edit-eye-button">
					<img id="editEyeIcon" src="../img/eye-slash-regular-full.svg"
						alt="password">
				</button>
			</p>

			<p>
				姓<br> <input type="text" name="last_name"
					value="${customer.last_name}">
			</p>

			<p>
				名<br> <input type="text" name="first_name"
					value="${customer.first_name}">
			</p>

			<p>
				住所<br> <input type="text" name="address"
					value="${customer.address}">
			</p>

			<p>
				メールアドレス<br> <input type="email" name="mail_address"
					value="${customer.mail_address}" placeholder="example@email.com">
			</p>

			<div id="errorView" class="error">
				<c:forEach var="err" items="${errors}">
					<div>${err}</div>
				</c:forEach>
			</div>

			<input type="submit" value="更新する">

		</form>

	</div>
	
	<script src="../js/edit.js"></script>
</body>
</html>