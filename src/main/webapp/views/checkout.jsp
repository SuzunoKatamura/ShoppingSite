<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/checkout.css">
<title>Insert title here</title>
</head>
<body>
	<header class="site-header"> ... </header>

	<div class="confirm-container">
		<h1 class="confirm-title">Review Protocol ／ 最終確認手続き</h1>

		<div class="confirm-section">
			<h3>対象コンテンツ</h3>
			<div class="confirm-row">
				<span>誰もいない夕方のプールサイド（VR版）</span> <span>¥4,500</span>
			</div>
			<div class="confirm-row total">
				<span>合計金額</span> <span>¥4,500</span>
			</div>
		</div>

		<div class="confirm-section">
			<h3>定着先 ＆ 決済情報</h3>
			<div class="confirm-row">
				<span style="color: #718096;">送信先アドレス</span> <span>your-mind@example.com</span>
			</div>
			<div class="confirm-row">
				<span style="color: #718096;">決済手段</span> <span>クレジットカード
					(****-****-****-1234)</span>
			</div>
		</div>

		<div class="btn-area">
			<button type="button" class="neu-btn-circle" onclick="history.back()">入力を修正する</button>
			<button type="button" class="neu-btn-circle"
				style="font-weight: bold;" onclick="location.href='cart-out.jsp'">記憶を定着させる</button>
		</div>
	</div>

	<jsp:include page="footer.jsp" />

</body>
</html>