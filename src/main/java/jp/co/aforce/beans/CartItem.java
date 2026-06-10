package jp.co.aforce.beans;

public class CartItem implements java.io.Serializable {
	private String productId;
	private String name;
	private int price;
	private int quantity; // 数量 (デジタルコンテンツなら基本 1)

	public CartItem() {}

	// 便利用のコンストラクタ（これがあるとサーブレット等でインスタンス化しやすい）
	public CartItem(String productId, String name, int price, int quantity) {
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	// ゲッター
	public String getProductId() {
		return productId;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	
	// セッター
	public void setProductId(String productId) {
		this.productId = productId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	// 商品の小計
	public int getSubtotal() {
		return this.price * this.quantity;
	}

}
