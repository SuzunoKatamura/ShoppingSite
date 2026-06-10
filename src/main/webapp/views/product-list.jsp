<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<link rel="stylesheet" href="../css/product-list.css">

<main class="product-grid">

	<%--  サーブレットから届いた productList を1件ずつ「product」という名前で取り出してループ --%>
	<c:forEach var="product" items="${productList}">
		<article class="product-card"
			onclick="openModal('${product.productId}')">
			<div class="product-thumb">
				<span class="preview-label">360° PREVIEW</span>
			</div>
			<div class="product-info">
				<span class="product-id">#${product.productId}</span>
				<h4 class="product-name">${product.productName}</h4>

				<p class="product-price">
					¥${product.price} <span class="data-type">(VR / 2D)</span>
				</p>
			</div>
		</article>
	</c:forEach>

	<div class="pagination-area">
		<button class="page-btn">
			<i class="fa-solid fa-chevron-left"></i>
		</button>
		<button class="page-btn active">1</button>
		<button class="page-btn">2</button>
		<button class="page-btn">3</button>
		<button class="page-btn">
			<i class="fa-solid fa-chevron-right"></i>
		</button>
	</div>

</main>


<script>
// グローバルな変数として、JavaのデータをJSの連想配列にコンバートして保持する
const memoryData = {
    <c:forEach var="product" items="${productList}">
        '${product.productId}': {
            id: '#${product.productId}',
            name: '${product.productName}',
            desc: `${product.description}`,
            price: '${product.price}',

            sphere: '${product.sphereImageName}'
        },
    </c:forEach>
};
</script>