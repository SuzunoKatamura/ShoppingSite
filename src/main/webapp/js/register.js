/**
 * 
 */

// IDが重複しているかどうかを記憶するグローバル変数（最初は重複していない前提）
let isIdDuplicate = false;

function validateForm() {
	// エラー表示エリアを一旦クリア
	const errorView = document.getElementById("errorView");
	errorView.innerHTML = "";

	// エラーをためる配列
	let errors = [];

	// 入力項目の要素を丸ごと取得（色を変えるために要素自体を捕まえておく）
	const txtMemberId = document.getElementById("member_id");
	const txtPassword = document.getElementById("password");
	const txtLastName = document.getElementById("last_name");
	const txtFirstName = document.getElementById("first_name");
	const txtMailAddress = document.getElementById("mail_address");

	// 一旦、すべての入力欄のエラー色を消してリセットする
	txtMemberId.classList.remove("input-error");
	txtPassword.classList.remove("input-error");
	txtLastName.classList.remove("input-error");
	txtFirstName.classList.remove("input-error");
	txtMailAddress.classList.remove("input-error");

	// 半角英数字のチェック用パターン
	const alphaNumPattern = /^[a-zA-Z0-9]+$/;

	// 1. メンバーIDのチェック
	if (txtMemberId.value.trim() === "") {
		errors.push("メンバーIDを入力してください");
		txtMemberId.classList.add("input-error"); // 色をつける
	} else if (!alphaNumPattern.test(txtMemberId.value.trim())) {
		errors.push("メンバーIDは半角英数字のみで入力してください");
		txtMemberId.classList.add("input-error"); // 色をつける
	} else if (isIdDuplicate) {
		errors.push("このメンバーIDは既に登録されています");
		txtMemberId.classList.add("input-error"); // 色をつける
	}

	// 2. パスワードのチェック
	if (txtPassword.value.trim() === "") {
		errors.push("パスワードを入力してください");
		txtPassword.classList.add("input-error"); // 色をつける
	} else if (txtPassword.value.trim().length > 10) {
		errors.push("パスワードは10文字以内で入力してください");
		txtPassword.classList.add("input-error"); // 色をつける
	} else if (!alphaNumPattern.test(txtPassword.value.trim())) {
		errors.push("パスワードは半角英数字のみで入力してください");
		txtPassword.classList.add("input-error"); // 色をつける
	}

	// 3. 姓名のチェック
	if (txtLastName.value.trim() === "") {
		errors.push("姓を入力してください");
		txtLastName.classList.add("input-error"); // 色をつける
	}
	if (txtFirstName.value.trim() === "") {
		errors.push("名を入力してください");
		txtFirstName.classList.add("input-error"); // 色をつける
	}

	// 4. メールのチェック
	if (txtMailAddress.value.trim() === "") {
		errors.push("メールアドレスを入力してください");
		txtMailAddress.classList.add("input-error"); // 色をつける
	} else if (!txtMailAddress.value.trim().includes("@")) {
		errors.push("メール形式が正しくありません");
		txtMailAddress.classList.add("input-error"); // 色をつける
	}

	// エラーが1つでもあれば、送信をストップして画面に箇条書きを表示
	if (errors.length > 0) {
		errors.forEach(function(err) {
			const div = document.createElement("div");
			div.textContent = "・" + err;
			errorView.appendChild(div);
		});
		return false; // これでJavaへの送信（画面遷移）が止まる
	}

	return true; // エラーがなければ無さにJavaへ送信
}

function checkIdDuplicate() {
	const memberIdInput = document.getElementById("member_id");
	const msgSpan = document.getElementById("id-check-msg");
	const memberId = memberIdInput.value.trim();

	// 空っぽのときは何もしない（未入力エラーは送信時にJSがやるため）
	if (memberId === "") {
		msgSpan.textContent = "";
		memberIdInput.classList.remove("input-error");
		isIdDuplicate = false; // リセット
		return;
	}

	// Javaの専用サーブレットに、IDを乗せて通信
	fetch(`/ShoppingSite/confirm/id-check?member_id=${encodeURIComponent(memberId)}`)
		.then(response => response.text())
		.then(result => {
			if (result === "NG") {
				// 重複していた場合
				isIdDuplicate = true;
				msgSpan.textContent = "このメンバーIDは既に登録されています。";
				msgSpan.style.color = "#ff4d4d"; // 赤文字
				memberIdInput.classList.add("input-error"); // 入力欄を赤くする
			} else if (result === "OK") {
				// 使える場合
				isIdDuplicate = false; // 重複フラグを折る
				msgSpan.textContent = "この会員IDは使用可能です。";
				msgSpan.style.color = "#2ed573"; // 緑文字
				memberIdInput.classList.remove("input-error"); // 赤色を消す
			}
		})
		.catch(error => {
			console.error("エラーが発生しました:", error);
		});
}


//パスワードの表示切替
const password = document.getElementById("password");
const eyeButton = document.getElementById("registerTogglePassword");
const eyeIcon = document.getElementById("registerEyeIcon");

eyeButton.addEventListener("click", () => {

	if (password.type === "password") {
		password.type = "text";
		eyeIcon.src = "../img/eye-regular-full.svg";
	} else {
		password.type = "password";
		eyeIcon.src = "../img/eye-slash-regular-full.svg";
	}

});