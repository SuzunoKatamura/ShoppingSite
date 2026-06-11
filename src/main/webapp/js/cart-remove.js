/**
 * 
 */

function removeItem(productId, buttonElement) {
	// 裏側で新設した「CartRemove」サーブレットを叩くURL
	const url = "/ShoppingSite/cart/cart-remove?productId=" + encodeURIComponent(productId);

	// fetchを使って、画面はそのままで裏側だけで通信する
	fetch(url, { method: 'GET' })
	.then(response => {
		if (response.ok) {
			// サーブレット側でセッションからの削除が成功したら、画面上のHTMLもその場で消し去る
			const cartItem = buttonElement.closest('.cart-item');
			if (cartItem) {
				cartItem.remove(); 
			}

			// もしカートの中身が全滅して0個になったら、自動リロードして「空っぽ画面」に切り替える
			const remainingItems = document.querySelectorAll('.cart-item');
			if (remainingItems.length === 0) {
				window.location.reload(); 
			}
		} else {
			alert("データの破棄に失敗しました。");
		}
	})
	.catch(error => {
		console.error("エラー:", error);
		alert("通信エラーが発生しました。");
	});
}