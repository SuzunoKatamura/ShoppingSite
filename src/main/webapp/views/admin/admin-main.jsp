<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--<!DOCTYPE html>-->
<!--<html>-->
<!--<head>-->
<!--<meta charset="UTF-8">-->
<!--<title>Insert title here</title>-->
<!--</head>-->

<!--<body>-->

<!--	<jsp:include page="admin-sidebar.jsp" />-->

<!--	<div class="main-content">-->
<!--		<h2>管理者専用 ダッシュボード</h2>-->
<!--		<p >※この画面は管理者権限を持つユーザーのみアクセスできます</p>-->
<!--		<hr>-->

<!--		<div-->
<!--			>-->
<!--			<h3>現在のシステムステータス</h3>-->
<!--			<p>一般ユーザー用の「会員登録」「ログイン」「重複チェック」「会員情報修正」「退会処理」はすべて正常に稼働しています。</p>-->
<!--			<p>予備日以降に、サイドバーの各メニューとJava（サーブレット/DAO）の連携を実装します。</p>-->
<!--		</div>-->
<!--	</div>-->

<!--</body>-->
<!--</html>-->


<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>Master Console | Residual Records</title>
    <link rel="stylesheet" href="../css/style.css">
    <style>
        body { background-color: #ebf0f3; color: #2d3748; } /* 管理画面は少しメカニカルなグレー */
        .admin-container { max-width: 1000px; margin: 40px auto; padding: 0 20px; }
        .admin-header { display: flex; justify-content: space-between; align-items: center; border-bottom: 2px solid #cbd5e0; padding-bottom: 20px; margin-bottom: 40px; }
        .admin-title { font-size: 1.5rem; font-weight: 700; letter-spacing: 0.1em; color: #4a5568; }
        .admin-panel { background: #ffffff; border-radius: 20px; padding: 30px; box-shadow: 0 10px 25px rgba(184, 198, 209, 0.4); margin-bottom: 40px; }
        .panel-title { font-size: 1.1rem; color: #718096; margin-bottom: 20px; border-left: 4px solid #4a5568; padding-left: 10px; }
        
        /* 管理画面用テーブルスタイル（カルテ風） */
        .admin-table { width: 100%; border-collapse: collapse; text-align: left; margin-bottom: 20px; }
        .admin-table th { padding: 12px; border-bottom: 2px solid #e2e8f0; color: #718096; font-size: 0.85rem; }
        .admin-table td { padding: 16px 12px; border-bottom: 1px solid #e2e8f0; font-size: 0.9rem; }
        
        /* フォームの調整 */
        .admin-flex-form { display: flex; gap: 15px; align-items: flex-end; }
        .admin-input-group { flex: 1; display: flex; flex-direction: column; gap: 8px; }
        .admin-input-group label { font-size: 0.8rem; font-weight: 600; color: #718096; }
        
        /* 管理画面用の小さな凸ボタン */
        .admin-btn-sm { padding: 8px 16px; font-size: 0.8rem; border-radius: 8px; border: none; background: #ebf0f3; cursor: pointer; box-shadow: 3px 3px 6px #cbd5e0, -3px -3px 6px #ffffff; transition: all 0.2s; }
        .admin-btn-sm:active { box-shadow: inset 2px 2px 4px #cbd5e0, inset -2px -2px 4px #ffffff; }
        .btn-delete { color: #e53e3e; } /* 削除は赤文字 */
    </style>
</head>
<body>

    <div class="admin-container">
        
        <header class="admin-header">
            <div class="admin-title">MAIN FRAME ／ 記憶アーカイブ管理コンソール</div>
            <button class="admin-btn-sm" onclick="location.href='main.jsp'">一般サイトへ戻る</button>
        </header>

        <section class="admin-panel">
            <div class="panel-title">Archive Injection ／ 新規記憶データの登録</div>
            <form action="RegisterProductServlet" method="POST" class="admin-flex-form">
                <div class="admin-input-group">
                    <label>記憶名称</label>
                    <input type="text" name="pName" class="neu-input" style="padding: 10px;" placeholder="例：夕暮れの無人駅" required>
                </div>
                <div class="admin-input-group" style="flex: 2;">
                    <label>記憶の解説（モーダル用）</label>
                    <input type="text" name="pDesc" class="neu-input" style="padding: 10px;" placeholder="例：誰もいないホームに、ひぐらしの声だけが響く。" required>
                </div>
                <div class="admin-input-group" style="flex: 0.5;">
                    <label>定着費用 (¥)</label>
                    <input type="number" name="pPrice" class="neu-input" style="padding: 10px;" placeholder="4500" required>
                </div>
                <button type="submit" class="admin-btn-sm" style="font-weight: bold; height: 42px;">アーカイブへ注入</button>
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
                    <tr>
                        <td>#001</td>
                        <td><strong>雨上がりの通学路</strong></td>
                        <td style="color:#718096; font-size: 0.8rem;">誰もいない住宅街。アスファルトだけが濡れていて…</td>
                        <td>¥4,500</td>
                        <td><button class="admin-btn-sm btn-delete">消去</button></td>
                    </tr>
                    <tr>
                        <td>#002</td>
                        <td><strong>閉館間際の水族館</strong></td>
                        <td style="color:#718096; font-size: 0.8rem;">館内放送が流れている。青い光だけが静かに揺れている。</td>
                        <td>¥4,500</td>
                        <td><button class="admin-btn-sm btn-delete">消去</button></td>
                    </tr>
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
                    <tr>
                        <td>2026/06/08 13:14</td>
                        <td style="font-family: monospace;">AM-9804-6173E</td>
                        <td>patient-01@example.com</td>
                        <td>誰もいない夕方のプールサイド</td>
                        <td>¥4,500</td>
                    </tr>
                </tbody>
            </table>
        </section>

    </div>

</body>
</html>