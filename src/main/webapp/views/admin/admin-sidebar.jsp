<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="admin-sidebar"
	style="width: 250px; background-color: #2c3e50; color: white; min-height: 100vh; padding: 20px; box-sizing: border-box;">
	<h3>管理者メニュー</h3>

	<ul>
		<li><a href="#">👤 会員一覧・検索</a></li>
		<li><a href="#">📦 商品登録・編集</a></li>
		<li><a href="#">📈 売上・注文履歴</a></li>
		<li><a href="${pageContext.request.contextPath}/views/login.jsp">🚪
				ログアウト</a></li>
	</ul>
</div>