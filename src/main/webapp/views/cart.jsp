<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
// セッションからログイン情報を取得してみる
Object user = session.getAttribute("customer");

// もしログイン情報がnullなら、強制的にログイン画面へ突き返す
if (user == null) {
	// ログインが終わったらcart.jspに帰ってきてね、とメモする
	session.setAttribute("returnTarget", "cart.jsp");

	response.sendRedirect(request.getContextPath() + "/views/login-in.jsp");
	return;
}
jp.co.aforce.beans.Cart cart = (jp.co.aforce.beans.Cart) session.getAttribute("cart");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/cart.css">
<title>Insert title here</title>
</head>
<body>

	<header class="site-header"> ... </header>

	<div class="cart-container">
		<h1 class="cart-title">Selected Void ／ カートに入れた記憶</h1>

		<div class="cart-list">
			<%
			// カートが空っぽじゃなければ、中の商品を1つずつ取り出してループ表示する
			if (cart != null && cart.getItems() != null && !cart.getItems().isEmpty()) {
				for (jp.co.aforce.beans.CartItem item : cart.getItems()) {
			%>
			<div class="cart-item">
				<div class="cart-thumb-sm"></div>
				<div class="cart-item-info">
					<div class="cart-item-title"><%=item.getName()%></div>
					<div class="cart-item-type">Format: VR Data (.mp4)</div>
				</div>
				<div class="cart-item-price">
					¥<%=String.format("%,d", item.getPrice())%></div>
				<button class="neu-btn-circle"
					style="margin-left: 20px; padding: 10px 15px;">×</button>
			</div>
			<%
			}
			}
			%>
		</div>

		<div class="cart-summary">
			<div class="total-price">
				Total: ¥<%=String.format("%,d", (cart != null) ? cart.getTotalPrice() : 0)%></div>

			<button class="neu-btn-circle checkout-btn"
				onclick="location.href='order-confirm.jsp'">決済手続きへ（記憶の定着）</button>
		</div>
	</div>

	<div class="empty-cart-box">
		<div class="empty-icon">
			<i class="fa-solid fa-box-open"></i>
		</div>
		<h2 class="empty-title">No Void Detected ／ カートは空です</h2>
		<p class="empty-message">
			現在、一時保存されている記憶データはありません。<br> アーカイブからあなたの意識に引っかかる記憶をお探しください。
		</p>
		<button class="neu-btn-circle empty-return-btn"
			onclick="location.href='main.jsp'">記憶の検索へ戻る</button>
	</div>

	<footer class="site-footer"> ... </footer>

</body>
</html>