<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
// 1. セッションからカート情報を取得
jp.co.aforce.beans.Cart cart = (jp.co.aforce.beans.Cart) session.getAttribute("cart");

// 安全対策：もしカートが空っぽなら、確認するものが無いのでカート画面に戻す
if (cart == null || cart.getItems() == null || cart.getItems().isEmpty()) {
	response.sendRedirect(request.getContextPath() + "/cart/cart.jsp"); // パスは環境に合わせて調整してください
	return;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/purchase.css">
<title>Fixation Protocol - Residual Records</title>
</head>
<body>

	<jsp:include page="header.jsp" />

	<div class="checkout-container">
		<h1 class="checkout-title">Fixation Protocol ／ 記憶の定着手続き</h1>

		<form action="${pageContext.request.contextPath}/cart/purchase"
			method="POST">

			<div class="form-section">
				<div class="form-group">
					<label for="email">記憶の定着先（メールアドレス）</label> <input type="email"
						id="email" name="email" class="neu-input"
						placeholder="your-mind@example.com" required> <span
						style="font-size: 0.75rem; color: #a0aec0; display: block; margin-top: 5px;">※購入完了後、このアドレス宛にデータへのアクセスキーが送信されます。</span>
				</div>
			</div>

			<div class="form-section">
				<div class="form-group">
					<label for="card-num">クレジットカード番号</label> <input type="text"
						id="card-num" name="cardNum" class="neu-input"
						placeholder="0000 0000 0000 0000" required>
				</div>

				<div class="form-row">
					<div class="form-group">
						<label for="card-expiry">有効期限</label> <input type="text"
							id="card-expiry" name="cardExpiry" class="neu-input"
							placeholder="MM / YY" required>
					</div>
					<div class="form-group">
						<label for="card-cvc">セキュリティコード</label> <input type="text"
							id="card-cvc" name="cardCvc" class="neu-input" placeholder="CVC"
							required>
					</div>
				</div>
			</div>

			<div class="form-section">
				<label>注文内容の最終確認</label>
				<div class="order-summary-box">

					<%-- ⭕ カートの中身をループして動的に表示！ --%>
					<%
					for (jp.co.aforce.beans.CartItem item : cart.getItems()) {
					%>
					<div class="summary-line">
						<span><%=item.getName()%> × 1</span> <span>¥<%=String.format("%,d", item.getPrice())%></span>
					</div>
					<%
					}
					%>

					<div class="summary-line total">
						<span>合計金額</span> <span>¥<%=String.format("%,d", cart.getTotalPrice())%></span>
					</div>
				</div>
			</div>

			<div class="submit-area">
				<button type="submit" class="neu-btn-circle submit-btn">記憶を定着させる（注文確定）</button>
			</div>

		</form>
	</div>

	<jsp:include page="footer.jsp" />

</body>
</html>