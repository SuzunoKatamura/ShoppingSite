package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jp.co.aforce.beans.Product;

public class ProductDAO extends DAO {
	/**
     * productsテーブルからすべての商品データを取得するメソッド
     */
    public List<Product> findAll() throws Exception { // 🔴getConnectionのエラーを上に投げるためthrowsを追加
        List<Product> productList = new ArrayList<>();
        
        String sql = "SELECT PRODUCT_ID, PRODUCT_NAME, DESCRIPTION, PRICE, THUMBNAIL_NAME, SPHERE_IMAGE_NAME, UPDATED_AT FROM products";

        //  親の getConnection() を使ってスッキリ接続！
        try (Connection conn = getConnection();
             PreparedStatement pStmt = conn.prepareStatement(sql);
             ResultSet rs = pStmt.executeQuery()) {

            while (rs.next()) {
                Product product = new Product();
                
                product.setProductId(rs.getString("PRODUCT_ID"));
                product.setProductName(rs.getString("PRODUCT_NAME"));
                product.setDescription(rs.getString("DESCRIPTION"));
                product.setPrice(rs.getInt("PRICE"));
                product.setThumbnailName(rs.getString("THUMBNAIL_NAME"));
                product.setSphereImageName(rs.getString("SPHERE_IMAGE_NAME"));
                product.setUpdatedAt(rs.getTimestamp("UPDATED_AT"));

                productList.add(product);
            }
        } //  try-with-resources文なので、エラーが起きてもconnやrsは自動で安全に閉じられます
        
        return productList;
    }

}
