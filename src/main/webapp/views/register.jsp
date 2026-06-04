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

		<form id="registerForm" action="/ShoppingSite/confirm/user-confirm" method="post" onsubmit="return validateForm()" >
		
		    <p>
		        <span class="label-group">
		            <label>メンバーID*</label>
		            <span id="id-check-msg" class="id-check-text"></span>
		        </span>
		        <input type="text" id="member_id" name="member_id" value="${customer.member_id}" onblur="checkIdDuplicate()">
		    </p>
		
		    <p>
		        パスワード*<br> <input type="password" id="password" name="password">
		    </p>
		
		    <p>
		        姓*<br> <input type="text" id="last_name" name="last_name" value="${customer.last_name}">
		    </p>
		
		    <p>
		        名*<br> <input type="text" id="first_name" name="first_name" value="${customer.first_name}">
		    </p>
		
		    <p>
		        住所<br> <input type="text" name="address" value="${customer.address}">
		    </p>
		
		    <p>
		        メールアドレス*<br> <input type="text" id="mail_address" name="mail_address" value="${customer.mail_address}">
		    </p>
		    
		    <div id="errorView" class="error">
		        <c:forEach var="err" items="${errors}">
		            <div>${err}</div>
		        </c:forEach>
		    </div>
		
		    <p>
		        <input type="submit" value="新規会員登録する">
		    </p>
		
		</form>

	</div>

	<script src="../js/register.js"></script>
</body>
</html>
