/**
 * 
 */

function login() {
	// フェードアウト開始
	document.body.classList.add("fade-out");

	// 少し待ってから遷移
	setTimeout(() => {
		document.getElementById("loginForm").submit();
	}, 400);
}


//	入力チェック (空欄の場合は送信しない)
function validateForm() {
	
	const id = document.getElementById("member_id");
	const password = document.getElementById("password");
	const error = document.getElementById("error-message");

	const idValue = id.value.trim();
	const passwordValue = password.value.trim();

	// 入力欄のエラー色を一旦リセット（白に戻す）
	id.classList.remove("input-error");
	password.classList.remove("input-error");
	error.textContent = "";

	// 判定と同時に、エラーの入力欄を赤く染める
	if (idValue === "" && passwordValue === "") {
		error.textContent = "メンバーIDとパスワードを入力してください";
		id.classList.add("input-error");       // IDを赤く
		password.classList.add("input-error"); // パスワードも赤く

	} else if (idValue === "") {
		error.textContent = "メンバーIDを入力してください";
		id.classList.add("input-error");       // IDだけを赤く

	} else if (passwordValue === "") {
		error.textContent = "パスワードを入力してください";
		password.classList.add("input-error"); // パスワードだけを赤く

	} else {
		// エラーがなければフェードアウトして送信
		login();
	}

	return false;
}

//パスワードの表示切替
const password = document.getElementById("password");
const eyeButton = document.getElementById("togglePassword");
const eyeIcon = document.getElementById("eyeIcon");

eyeButton.addEventListener("click", () => {

	if (password.type === "password") {
		password.type = "text";
		eyeIcon.src = "../img/eye-regular-full.svg";
	} else {
		password.type = "password";
		eyeIcon.src = "../img/eye-slash-regular-full.svg";
	}

});

