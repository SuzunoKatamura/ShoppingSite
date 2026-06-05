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

    <div class="checkout-container">
        <h1 class="checkout-title">Fixation Protocol ／ 記憶の定着手続き</h1>

        <form action="#" method="POST">
            
            <div class="form-section">
                <div class="form-group">
                    <label for="email">記憶の定着先（メールアドレス）</label>
                    <input type="email" id="email" class="neu-input" placeholder="your-mind@example.com" required>
                    <span style="font-size: 0.75rem; color: #a0aec0; display: block; margin-top: 5px;">※購入完了後、このアドレス宛にデータへのアクセスキーが送信されます。</span>
                </div>
            </div>

            <div class="form-section">
                <div class="form-group">
                    <label for="card-num">クレジットカード番号</label>
                    <input type="text" id="card-num" class="neu-input" placeholder="0000 0000 0000 0000" required>
                </div>
                
                <div class="form-row">
                    <div class="form-group">
                        <label for="card-expiry">有効期限</label>
                        <input type="text" id="card-expiry" class="neu-input" placeholder="MM / YY" required>
                    </div>
                    <div class="form-group">
                        <label for="card-cvc">セキュリティコード</label>
                        <input type="text" id="card-cvc" class="neu-input" placeholder="CVC" required>
                    </div>
                </div>
            </div>

            <div class="form-section">
                <label>注文内容の最終確認</label>
                <div class="order-summary-box">
                    <div class="summary-line">
                        <span>記憶データ (#1998-SUMMER) × 1</span>
                        <span>¥4,500</span>
                    </div>
                    <div class="summary-line total">
                        <span>合計金額</span>
                        <span>¥4,500</span>
                    </div>
                </div>
            </div>

            <div class="submit-area">
                <button type="submit" class="neu-btn-circle submit-btn">記憶を定着させる（注文確定）</button>
            </div>

        </form>
    </div>

    <footer class="site-footer"> ... </footer>

</body>
</html>