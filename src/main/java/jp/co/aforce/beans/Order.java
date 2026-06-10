package jp.co.aforce.beans;

import java.time.LocalDateTime;
import java.util.List;

public class Order implements java.io.Serializable {

	private int orderId;
	private String userId;
	private int totalPrice;
	private LocalDateTime orderDate;
	private List<OrderItem> orderItems;

	public Order() {}

	// ゲッター
	public int getOrderId() {
		return orderId;
	}

	public String getUserId() {
		return userId;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	
	// セッター
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

}
