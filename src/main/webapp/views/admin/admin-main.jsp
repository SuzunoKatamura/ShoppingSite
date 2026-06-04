<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>

	<jsp:include page="admin-sidebar.jsp" />

	<div class="main-content">
		<h2>管理者専用 ダッシュボード</h2>
		<p >※この画面は管理者権限を持つユーザーのみアクセスできます</p>
		<hr>

		<div
			>
			<h3>現在のシステムステータス</h3>
			<p>一般ユーザー用の「会員登録」「ログイン」「重複チェック」「会員情報修正」「退会処理」はすべて正常に稼働しています。</p>
			<p>予備日以降に、サイドバーの各メニューとJava（サーブレット/DAO）の連携を実装します。</p>
		</div>
	</div>

</body>
</html>