<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="../css/header.css">
    
	<header class="site-header">
	
		<div class="logo">Residual Records</div>
		
		<div class="header-menu">
		
			<button class="search">
				<img alt="search" src="../img/search_24dp_555_FILL0_wght400_GRAD0_opsz24.svg">
			</button>
			
			<button class="cart" title="カート">
				<img alt="search" src="../img/shopping_cart_24dp_555_FILL0_wght400_GRAD0_opsz24.svg">
			</button>
			
			<button class="userpage" title="ログイン" onclick="location.href='${pageContext.request.contextPath}/views/login-in.jsp'">
				<img alt="search" src="../img/login_24dp_555_FILL0_wght400_GRAD0_opsz24.svg">
			</button>
			
		</div>
		
	</header>