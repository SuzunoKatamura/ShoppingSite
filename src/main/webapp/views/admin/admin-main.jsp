<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%
//  サーブレット側から届く「商品リスト」と「注文履歴リスト」をキャッチする箱（一旦nullで安全弁）
// ※次のステップでサーブレット経由でこの画面を開くようにします
List<jp.co.aforce.beans.Product> productList = (List<jp.co.aforce.beans.Product>) request.getAttribute("productList");
List<jp.co.aforce.beans.Order> orderList = (List<jp.co.aforce.beans.Order>) request.getAttribute("orderList");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Master Console | Residual Records</title>
<link rel="stylesheet" href="../css/style.css">
<style>
body {
	background-color: #ebf0f3;
	color: #2d3748;
} /* 管理画面は少しメカニカルなグレー */
.admin-container {
	max-width: 1000px;
	margin: 40px auto;
	padding: 0 20px;
}

.admin-header {
	display: flex;
	justify-content: space-between;
	align-items: center;
	border-bottom: 2px solid #cbd5e0;
	padding-bottom: 20px;
	margin-bottom: 40px;
}

.admin-title {
	font-size: 1.5rem;
	font-weight: 700;
	letter-spacing: 0.1em;
	color: #4a5568;
}

.admin-panel {
	background: #ffffff;
	border-radius: 20px;
	padding: 30px;
	box-shadow: 0 10px 25px rgba(184, 198, 209, 0.4);
	margin-bottom: 40px;
}

.panel-title {
	font-size: 1.1rem;
	color: #718096;
	margin-bottom: 20px;
	border-left: 4px solid #4a5568;
	padding-left: 10px;
}

/* 管理画面用テーブルスタイル（カルテ風） */
.admin-table {
	width: 100%;
	border-collapse: collapse;
	text-align: left;
	margin-bottom: 20px;
}

.admin-table th {
	padding: 12px;
	border-bottom: 2px solid #e2e8f0;
	color: #718096;
	font-size: 0.85rem;
}

.admin-table td {
	padding: 16px 12px;
	border-bottom: 1px solid #e2e8f0;
	font-size: 0.9rem;
}

/* フォームの調整 */
.admin-flex-form {
	display: flex;
	gap: 15px;
	align-items: flex-end;
}

.admin-input-group {
	flex: 1;
	display: flex;
	flex-direction: column;
	gap: 8px;
}

.admin-input-group label {
	font-size: 0.8rem;
	font-weight: 600;
	color: #718096;
}

/* 管理画面用の小さな凸ボタン */
.admin-btn-sm {
	padding: 8px 16px;
	font-size: 0.8rem;
	border-radius: 8px;
	border: none;
	background: #ebf0f3;
	cursor: pointer;
	box-shadow: 3px 3px 6px #cbd5e0, -3px -3px 6px #ffffff;
	transition: all 0.2s;
}

.admin-btn-sm:active {
	box-shadow: inset 2px 2px 4px #cbd5e0, inset -2px -2px 4px #ffffff;
}

.btn-delete {
	color: #e53e3e;
} /* 削除は赤文字 */
</style>
</head>
<body>

	<div class="admin-container">

		<header class="admin-header">
			<div class="admin-title">MAIN FRAME ／ 記憶アーカイブ管理コンソール</div>
			<button class="admin-btn-sm" onclick="location.href='${pageContext.request.contextPath}/product/product-list'">一般サイトへ戻る</button>
		</header>

		<section class="admin-panel">
			<div class="panel-title">Archive Injection ／ 新規記憶データの登録</div>
			<form action="/admin/register-product" method="POST"
				class="admin-flex-form">
				<div class="admin-input-group">
					<label>記憶名称</label> <input type="text" name="pName"
						class="neu-input" style="padding: 10px;" placeholder="例：夕暮れの無人駅"
						required>
				</div>
				<div class="admin-input-group" style="flex: 2;">
					<label>記憶の解説（モーダル用）</label> <input type="text" name="pDesc"
						class="neu-input" style="padding: 10px;"
						placeholder="例：誰もいないホームに、ひぐらしの声だけが響く。" required>
				</div>
				<div class="admin-input-group" style="flex: 0.5;">
					<label>定着費用 (¥)</label> <input type="number" name="pPrice"
						class="neu-input" style="padding: 10px;" placeholder="4500"
						required>
				</div>
				<button type="submit" class="admin-btn-sm"
					style="font-weight: bold; height: 42px;">アーカイブへ注入</button>
			</form>
		</section>

		<section class="admin-panel">
			<div class="panel-title">Stored Records ／ 現在保管中の記憶一覧</div>
			<table class="admin-table">
				<thead>
					<tr>
						<th>ID</th>
						<th>記憶名称</th>
						<th>解説文</th>
						<th>価格</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<%
					//  サーブレットから届いた本物の商品リストをループで回して全件表示する
					if (productList != null && !productList.isEmpty()) {
						for (jp.co.aforce.beans.Product p : productList) {
					%>
					<tr>
						<td>#<%=p.getProductId()%></td>
						<td><strong><%=p.getProductName()%></strong></td>
						<td style="color: #718096; font-size: 0.8rem;"><%=p.getDescription()%></td>
						<td>¥<%=String.format("%,d", p.getPrice())%></td>
						<td>
							<button class="admin-btn-sm btn-delete"
								onclick="deleteProduct('<%=p.getProductId()%>')">消去</button>
						</td>
					</tr>
					<%
					}
					} else {
					%>
					<tr>
						<td colspan="5" style="text-align: center; color: #a0aec0;">保管中の記憶データはありません。</td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
		</section>

		<section class="admin-panel">
			<div class="panel-title">Fixation Logs ／ 記憶定着プロトコル履歴（注文監視）</div>
			<table class="admin-table">
				<thead>
					<tr>
						<th>定着日時</th>
						<th>注文ID</th>
						<th>対象アカウント（送信先）</th>
						<th>定着記憶</th>
						<th>決済金額</th>
					</tr>
				</thead>
				<tbody>

					<%
					//  サーブレットから届いた本物の注文履歴リストをループで回して全件表示する
					if (orderList != null && !orderList.isEmpty()) {
						// LocalDateTimeを表示用に綺麗にフォーマットするための準備
						java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

						for (jp.co.aforce.beans.Order o : orderList) {
							String formattedDate = (o.getOrderDate() != null) ? o.getOrderDate().format(formatter) : "---";
					%>
					<tr>
						<td><%=formattedDate%></td>
						<td style="font-family: monospace;"><%=o.getOrderId()%></td>
						<td>User ID: <%=o.getUserId()%></td>
						<td>---（データ連携中）---</td>
						<td>¥<%=String.format("%,d", o.getTotalPrice())%></td>
					</tr>
					<%
					}
					} else {
					%>
					<tr>
						<td colspan="5" style="text-align: center; color: #a0aec0;">定着プロトコルの履歴はありません。</td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
		</section>

		<section class="admin-panel">
			<div class="panel-title">User Database ／ 同期アカウント監視（会員管理）</div>
			<table class="admin-table">
				<thead>
					<tr>
						<th>ユーザーID</th>
						<th>識別名（氏名）</th>
						<th>周波数（メールアドレス）</th>
						<th>居住地（住所）</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<%
					// サーブレットから届いた customerList を取り出す
					java.util.List<jp.co.aforce.beans.Customer> customerList = (java.util.List<jp.co.aforce.beans.Customer>) request
							.getAttribute("customerList");

					if (customerList != null && !customerList.isEmpty()) {
						for (jp.co.aforce.beans.Customer c : customerList) {
					%>
					<tr>
						<td style="font-family: monospace;">#<%=c.getMember_id()%></td>
						<td><strong><%=c.getLast_name()%> <%=c.getFirst_name()%></strong></td>
						<td><%=c.getMail_address()%></td>
						<td style="color: #718096; font-size: 0.85rem;"><%=c.getAddress()%></td>
						<td>
							<button class="admin-btn-sm btn-delete"
								onclick="deleteUser('<%=c.getMember_id()%>')">削除</button>
						</td>
					</tr>
					<%
					}
					} else {
					%>
					<tr>
						<td colspan="5" style="text-align: center; color: #a0aec0;">同期中のアカウントデータはありません。</td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
		</section>

	</div>


	<script>
		function deleteProduct(productId) {
			if (confirm("この記憶データをアーカイブから完全に消去しますか？\n（この操作は取り消せません）")) {
				// ⭕ OKが押されたら、削除用サーブレットにIDを付けてジャンプする
				location.href = "DeleteProductServlet?id=" + productId;
			}
		}

		//  会員削除
		function deleteUser(memberId) {
			if (confirm("警告: このアカウントの同期を完全に解除（強制削除）しますか？\n（ユーザーデータが完全に消失します）")) {
				// OKを押したら、あとで作る会員削除用サーブレットにIDを飛ばす
				location.href = "DeleteUserServlet?id=" + memberId;
			}
		}
	</script>
</body>
</html>
</body>
</html>
</body>
</html>