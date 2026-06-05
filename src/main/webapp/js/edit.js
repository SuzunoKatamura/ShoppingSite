/**
 * 
 */

//パスワードの表示切替
const password = document.getElementById("password");
const eyeButton = document.getElementById("editTogglePassword");
const eyeIcon = document.getElementById("editEyeIcon");

eyeButton.addEventListener("click", () => {

	if (password.type === "password") {
		password.type = "text";
		eyeIcon.src = "../img/eye-regular-full.svg";
	} else {
		password.type = "password";
		eyeIcon.src = "../img/eye-slash-regular-full.svg";
	}

});