<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/index.css">
<!--天球用のリンク-->
<script src="https://aframe.io/releases/1.5.0/aframe.min.js"></script>
<title>Residual Records</title>
</head>
<body>
	<!-- 1. ヘッダー  -->
	<jsp:include page="header.jsp" />

	<!-- 2. ファーストビュー (Blobの遊び場) -->
	<section class="first-view">
		<div class="blob-space-holder">
			<canvas id="blob-canvas"></canvas>
		</div>

		<div class="fv-content">
			<h1 class="catchphrase">追憶する</h1>
			<p class="fv-sub">Residual Records</p>
		</div>
	</section>

	<!-- 3. メインコンテンツ -->
	<div class="main-container">

		<aside class="sidebar neu-panel-inset">
			<h3 class="sidebar-title">Filter by Void</h3>
			<ul class="filter-list">
				<li><label><input type="checkbox"> ノスタルジー（郷愁）</label></li>
				<li><label><input type="checkbox"> デジャヴ（既視感）</label></li>
				<li><label><input type="checkbox"> リミナルスペース（境界）</label></li>
			</ul>
		</aside>

		<div class="product-list-wrapper">
			<jsp:include page="product-list.jsp" />
		</div>

	</div>

	<!-- 4. 閲覧履歴 -->
	<section class="history-section">
		<div class="history-container">
			<h2 class="history-title">
				Recently Viewed Void <span class="history-subtitle">／
					接触した記憶の履歴</span>
			</h2>

			<div class="history-grid">

				<article class="history-card">
					<div class="history-thumb">
						<span class="history-thumb-text">LOGGED DATA</span>
					</div>
					<div class="history-info">
						<h4 class="history-name">1998年夏の終わりのプールサイド</h4>
						<p class="history-date">接触日時: 2分前</p>
					</div>
				</article>

				<article class="history-card">
					<div class="history-thumb">
						<span class="history-thumb-text">LOGGED DATA</span>
					</div>
					<div class="history-info">
						<h4 class="history-name">なぜか見覚えのある放課後の長い廊下</h4>
						<p class="history-date">接触日時: 15分前</p>
					</div>
				</article>

				<article class="history-card">
					<div class="history-thumb">
						<span class="history-thumb-text">LOGGED DATA</span>
					</div>
					<div class="history-info">
						<h4 class="history-name">不自然に続く無人の地下通路</h4>
						<p class="history-date">接触日時: 1日前</p>
					</div>
				</article>

			</div>
		</div>
	</section>

	<!-- 商品詳細モーダル  -->
	<div id="product-modal" class="modal-overlay"
		onmousedown="handleModalMouseDown(event)"
		onmouseup="handleModalMouseUp(event)">

		<a-scene vr-mode-ui="enabled: false" loading-screen="enabled: false"
			class="fullscreen-bg-scene"> <a-sky id="modal-sky" src=""
			rotation="0 -90 0"></a-sky> <a-entity camera
			look-controls="reverseMouseDrag: true; magicWindowTrackingEnabled: false;"></a-entity>
		</a-scene>

		<div class="modal-fullscreen-layer" onclick="closeModal()">
			<div class="modal-glass-panel" onclick="event.stopPropagation()">

				<button class="modal-close-btn" onclick="closeModal()">×</button>

				<div class="modal-info-content">
					<span id="modal-product-id" class="modal-id">#000-VOID</span>
					<h2 id="modal-product-name" class="modal-name">記憶のタイトル</h2>

					<div class="modal-desc-scroll">
						<p id="modal-product-desc" class="modal-desc">ここに解説文が流し込まれます。</p>
					</div>

					<div class="modal-purchase-box">
						<div class="modal-price-tag">
							<span class="price-label">ACCESS PRICE</span> <span
								id="modal-product-price" class="price-value">¥0</span>
						</div>
						<form action="${pageContext.request.contextPath}/cart/cart-add" method="post">

							<input type="hidden" name="productId"
								value="${product.productId}">

							<button type="submit" class="neu-btn-circle modal-cart-btn">
								この記憶をカートに保存する</button>
						</form>
					</div>
				</div>

			</div>
		</div>
	</div>

	<!-- 5. フッター  -->
	<jsp:include page="footer.jsp" />

	<script src="${pageContext.request.contextPath}/js/index.js"></script>

	<!-- 6. ページトップボタン  -->
	<button id="to-top-btn" class="to-top-btn" title="最上部へ戻る"
		onclick="scrollToTop()">
		<i class="fa-solid fa-chevron-up"></i>
	</button>
</body>
</html>