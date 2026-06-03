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
<h2>会員情報編集</h2>

<form action="${pageContext.request.contextPath}/update/user-update" method="post">

    <input type="hidden" name="member_id" value="${customer.member_id}">

    <p>
        パスワード<br>
        <input type="password" name="password" value="${customer.password}">
    </p>

    <p>
        姓<br>
        <input type="text" name="last_name" value="${customer.last_name}">
    </p>

    <p>
        名<br>
        <input type="text" name="first_name" value="${customer.first_name}">
    </p>

    <p>
        住所<br>
        <input type="text" name="address" value="${customer.address}">
    </p>

    <p>
        メールアドレス<br>
        <input type="email" name="mail_address" value="${customer.mail_address}">
    </p>

    <input type="submit" value="更新する">

</form>

</body>
</html>