<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/user-menu.css">
<title>User menu</title>
</head>

<body class="fade-in">

	<div class="header">
		<button class="logout" type="button"
			onclick="location.href='/ShoppingSite/LoginAction/logout'">Logout</button>
	</div>

	<p class="msg">ようこそ、${customer.last_name}さん！</p>

	<div class="container">

		<div class="card">

			<div class="action">
				<form action="${pageContext.request.contextPath}/update/user-edit" method="get">
				    <input type="hidden" name="member_id" value="${customer.member_id}">
				    <input type="submit" class="edit" value="修正">
				</form>
				
				<form action="${pageContext.request.contextPath}/update/user-delete" method="post">
					<input type="hidden" name="member_id" value="${customer.member_id}">
				    <input type="submit" class="delete" value="削除" onclick="return confirm('本当に削除しますか？');">
				</form>
			</div>


		</div>

	</div>
	
	<section class="mypage-section">
    <div class="mypage-panel-title">Synchronized Records ／ 同期済みの記憶（購入履歴）</div>
    
    <div class="history-list">
        
        <div class="history-item">
            <div class="history-meta">
                <span class="history-date">定着日: 2026/06/08</span>
                <span class="history-id">#001-ROAD</span>
            </div>
            <div class="history-detail">
                <h4 class="history-name">雨上がりの通学路</h4>
                <button class="admin-btn-sm" onclick="alert('データを再ダウンロードします')">
                    <i class="fa-solid fa-download"></i> 再受信
                </button>
            </div>
        </div>

        <div class="history-item">
            <div class="history-meta">
                <span class="history-date">定着日: 2026/05/12</span>
                <span class="history-id">#004-PA</span>
            </div>
            <div class="history-detail">
                <h4 class="history-name">深夜のサービスエリア</h4>
                <button class="admin-btn-sm" onclick="alert('データを再ダウンロードします')">
                    <i class="fa-solid fa-download"></i> 再受信
                </button>
            </div>
        </div>

    </div>
</section>

	<script src="../js/user-menu.js"></script>
</body>
</html>