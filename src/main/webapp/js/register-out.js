/**
 * 
 */

// 時間経過でアニメーションをリピートさせる
setInterval(() => {
  const el = document.querySelector(".circle");
  el.classList.remove("animation");
  setTimeout(() => {
    el.classList.add("animation");
  }, 16);
}, 4000);