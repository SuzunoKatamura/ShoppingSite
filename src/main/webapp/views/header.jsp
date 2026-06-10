<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="../css/header.css">
<%
//  セッションからログイン情報を取ってきて、今ログインしてるかチェックする
Object currentCustomer = session.getAttribute("customer");
%>
    
	<header class="site-header">
	
		<div class="logo">Residual Records</div>
		
		<div class="header-menu">
		
			<button class="search">
				<img alt="search" src="../img/search_24dp_555_FILL0_wght400_GRAD0_opsz24.svg">
			</button>
			
			<button class="cart" title="カート" onclick="location.href='${pageContext.request.contextPath}/views/cart.jsp'">
				<img alt="search" src="../img/shopping_cart_24dp_555_FILL0_wght400_GRAD0_opsz24.svg">
			</button>
			
			<%-- ログイン状態によってボタンの見た目を切り替える --%>
			<% if (currentCustomer == null) { %>
				<button class="userpage" title="ログイン" onclick="location.href='${pageContext.request.contextPath}/views/login-in.jsp'">
					<img alt="login" src="../img/login_24dp_555_FILL0_wght400_GRAD0_opsz24.svg">
				</button>
			<% } else { %>
				<button class="userpage" title="マイページ" onclick="location.href='${pageContext.request.contextPath}/views/user-menu.jsp'" style="border: 2px solid #555; border-radius: 50%;">
					<img alt="mypage" src="../img/person_24dp_555_FILL0_wght400_GRAD0_opsz24.svg">
				</button>
			<% } %>
			
		</div>
		
	</header>