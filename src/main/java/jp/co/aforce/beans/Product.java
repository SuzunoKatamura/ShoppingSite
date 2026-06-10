package jp.co.aforce.beans;

public class Product implements java.io.Serializable {
	
	private String productId;
    private String productName;
    private String description;
    private int price;
    private java.sql.Timestamp updatedAt;
    private String thumbnailName;
    private String sphereImageName;
    
    
//    ゲッター
	public String getProductId() {
		return productId;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public String getDescription() {
		return description;
	}
	
	public int getPrice() {
		return price;
	}
	
	public java.sql.Timestamp getUpdatedAt() {
		return updatedAt;
	}
	
	public String getThumbnailName() {
		return thumbnailName; 
	}
	
	public String getSphereImageName() {
		return sphereImageName; 
	}
	
	
//	セッター
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public void setUpdatedAt(java.sql.Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public void setThumbnailName(String thumbnailName) {
		this.thumbnailName = thumbnailName; 
	}
	
	public void setSphereImageName(String sphereImageName) {
		this.sphereImageName = sphereImageName; 
	}

}