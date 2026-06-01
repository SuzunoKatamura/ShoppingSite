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

//function goRegister() {
//	document.body.classList.add("fade-out");
//
//	setTimeout(() => {
//		location.href = "register.jsp";
//	}, 400);
//}

//	入力チェック (空欄の場合は送信しない)
function validateForm() {
	const id = document.querySelector(
		"input[name='member_id']"
	);

	const password = document.querySelector(
		"input[name='password']"
	);

	const error = document.getElementById(
		"error-message"
	);

	const idValue = id.value.trim();
	const passwordValue = password.value.trim();

	error.textContent = "";

	if(idValue === "" && passwordValue === ""){
	    error.textContent =
	        "会員IDとパスワードを入力してください";

	} else if(idValue === ""){
	    error.textContent =
	        "会員IDを入力してください";

	} else if(passwordValue === ""){
	    error.textContent =
	        "パスワードを入力してください";

	} else {

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

