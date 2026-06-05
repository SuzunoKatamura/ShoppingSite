<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/user-confirm.css">
<title>Check</title>
</head>

<body>

<div class="container">

	<h2>入力内容確認</h2>
	
		<div class="stepper">
	
	    <div class="step active">
	        <div class="icon">
	            <img src="../img/pencil.svg" alt="入力">
	        </div>
	        <div class="label">入力</div>
	    </div>
	
	    <div class="line active"></div>
	
	    <div class="step active">
	        <div class="icon">
	            <img src="../img/display.svg" alt="確認">
	        </div>
	        <div class="label">確認</div>
	    </div>
	
	    <div class="line active"></div>
	
	    <div class="step">
	        <div class="icon">
	            <img src="../img/check.svg" alt="完了">
	        </div>
	        <div class="label">完了</div>
	    </div>
	
	</div>

	<div class="confirm-item"><span>メンバーID</span> ${customer.member_id}</div>
	<div class="confirm-item"><span>パスワード</span> ${customer.password}</div>
	<div class="confirm-item"><span>姓</span> ${customer.last_name}</div>
	<div class="confirm-item"><span>名</span> ${customer.first_name}</div>
	<div class="confirm-item"><span>住所</span> ${customer.address}</div>
	<div class="confirm-item"><span>メール</span> ${customer.mail_address}</div>

	<form action="${pageContext.request.contextPath}/confirm/user-insert" method="post">
		<input type="hidden" name="member_id" value="${customer.member_id}">
		<input type="hidden" name="password" value="${customer.password}">
		<input type="hidden" name="last_name" value="${customer.last_name}">
		<input type="hidden" name="first_name" value="${customer.first_name}">
		<input type="hidden" name="address" value="${customer.address}">
		<input type="hidden" name="mail_address" value="${customer.mail_address}">
		
		<input type="submit"value="登録">
	</form>

	<form action="${pageContext.request.contextPath}/confirm/register-back" method="post">
		<input type="hidden" name="member_id" value="${customer.member_id}">
	    <input type="hidden" name="password" value="${customer.password}">
	    <input type="hidden" name="last_name" value="${customer.last_name}">
	    <input type="hidden" name="first_name" value="${customer.first_name}">
	    <input type="hidden" name="address" value="${customer.address}">
	    <input type="hidden" name="mail_address" value="${customer.mail_address}">
    
		<input type="submit" value="戻る">
	</form>
	
</div>

</body>
</html>
