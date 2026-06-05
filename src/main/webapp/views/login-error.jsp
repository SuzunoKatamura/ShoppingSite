<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/login-error.css">
<title>Login error</title>
</head>

<body class="fade-in">

<div class="container">
    <div class="card">

        <h1 class="title">ログインに失敗しました</h1>

        <p class="msg">メンバーIDもしくはパスワードが違います</p>

        <button class="btn" type="button"
            onclick="location.href='/ShoppingSite/views/login-in.jsp'">
            ログイン画面へ戻る
        </button>

    </div>
</div>

</body>
</html>