<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/index.css">
<title>Residual Records</title>
</head>
<body>
	<!-- 1. ヘッダー (将来的にインクルード化) -->
	<header class="site-header">
		<div class="logo">Residual Records</div>
		<div class="header-menu">
			<button class="neu-btn-circle">検索</button>
			<button class="neu-btn-circle">カート</button>
			<button class="neu-btn-circle">マイページ</button>
		</div>
	</header>

	<!-- 2. ファーストビュー (Blobの遊び場) -->
	<section class="first-view">
		<div class="blob-space-holder"></div>

		<div class="fv-content">
			<h1 class="catchphrase">追憶する</h1>
			<p class="fv-sub">Residual Records</p>
		</div>
	</section>

	<!-- 3. メインコンテンツ（2カラム） -->
	<div class="main-container">

		<aside class="sidebar neu-panel-inset">
			<h3 class="sidebar-title">Filter by Void</h3>
			<ul class="filter-list">
				<li><label><input type="checkbox"> ノスタルジー（郷愁）</label></li>
				<li><label><input type="checkbox"> デジャヴ（既視感）</label></li>
				<li><label><input type="checkbox"> リミナルスペース（境界）</label></li>
			</ul>
		</aside>

		<main class="product-grid">

			<article class="product-card neu-panel-outset">
				<div class="product-thumb neu-panel-inset">
					<span class="preview-label">360° PREVIEW</span>
				</div>
				<div class="product-info">
					<span class="product-id">#1998-SUMMER</span>
					<h4 class="product-name">誰もいない夕方のプールサイド</h4>
					<p class="product-price">
						¥4,500 <span class="data-type">(VR / 2D)</span>
					</p>
				</div>
			</article>

			<article class="product-card neu-panel-outset">
				<div class="product-thumb neu-panel-inset">
					<span class="preview-label">360° PREVIEW</span>
				</div>
				<div class="product-info">
					<span class="product-id">#2004-CORRIDOR</span>
					<h4 class="product-name">なぜか見覚えのある放課後の長い廊下</h4>
					<p class="product-price">
						¥3,800 <span class="data-type">(VR / 2D)</span>
					</p>
				</div>
			</article>

		</main>
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

	<!-- 5. フッター (将来的にインクルード化) -->
	<footer class="site-footer">
		<p>&copy; Residual Records</p>
	</footer>
</body>
</html>