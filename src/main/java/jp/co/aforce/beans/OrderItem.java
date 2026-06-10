package jp.co.aforce.beans;

public class OrderItem implements java.io.Serializable {
	
	private int orderItemId;
	private int orderId;
	private String productId;
	private int price;

	public OrderItem() {}

	// ゲッター
	public int getOrderItemId() {
		return orderItemId;
	}

	public int getOrderId() {
		return orderId;
	}

	public String getProductId() {
		return productId;
	}

	public int getPrice() {
		return price;
	}

	//    セッター
	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
