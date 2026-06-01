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

function goRegister() {
	document.body.classList.add("fade-out");

	setTimeout(() => {
		location.href = "register.jsp";
	}, 400);
}