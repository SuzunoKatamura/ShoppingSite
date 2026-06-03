<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/register.css">
<title>Register</title>
</head>

<body>


	<div class="container">

		<h2>新規会員登録</h2>

		<div class="stepper">

			<div class="step active">
				<div class="icon">
					<img src="../img/pencil.svg" alt="入力">
				</div>
				<div class="label">入力</div>
			</div>

			<div class="line active"></div>

			<div class="step">
				<div class="icon">
					<img src="../img/display.svg" alt="確認">
				</div>
				<div class="label">確認</div>
			</div>

			<div class="line"></div>

			<div class="step">
				<div class="icon">
					<img src="../img/check.svg" alt="完了">
				</div>
				<div class="label">完了</div>
			</div>

		</div>

		<form action="/ShoppingSite/confirm/user-confirm" method="post">

			<p>
				メンバーID*<br> <input type="text" name="member_id"  value="${customer.member_id}" required>
			</p>

			<p>
				パスワード*<br> <input type="password" name="password" required>
			</p>

			<p>
				姓*<br> <input type="text" name="last_name"  value="${customer.last_name}" required>
			</p>

			<p>
				名*<br> <input type="text" name="first_name"  value="${customer.first_name}" required>
			</p>

			<p>
				住所<br> <input type="text" name="address" value="${customer.address}">
			</p>

			<p>
				メールアドレス*<br> <input type="email" name="mail_address" value="${customer.mail_address}" required>
			</p>
			
			<div class="error">${error}</div>

			<p>
				<input type="submit" value="新規会員登録する">
			</p>

		</form>

	</div>

</body>
</html>
