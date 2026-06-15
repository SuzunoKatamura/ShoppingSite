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
    public List<Product> findAll() throws Exception { // getConnectionのエラーを上に投げるためthrowsを追加
        List<Product> productList = new ArrayList<>();
        
        String sql = "SELECT PRODUCT_ID, PRODUCT_NAME, DESCRIPTION, PRICE, THUMBNAIL_NAME, SPHERE_IMAGE_NAME, UPDATED_AT FROM products";

        //  親の getConnection() を使って接続
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
    
    
 // 商品追加（インサート）
    public boolean insertProduct(Product product) {
        Connection con = null;
        PreparedStatement st = null;
        int rows = 0;

        try {
            con = getConnection(); 
           
            String sql = "INSERT INTO products (PRODUCT_NAME, PRICE, DESCRIPTION) VALUES (?, ?, ?)";
            st = con.prepareStatement(sql);
            st.setString(1, product.getProductName());
            st.setInt(2, product.getPrice());
            st.setString(3, product.getDescription()); 

            rows = st.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // いつものクローズ処理
            if (st != null) { try { st.close(); } catch (Exception e) {} }
            if (con != null) { try { con.close(); } catch (Exception e) {} }
        }

        // 1行以上追加されていれば成功（true）を返す
        return rows > 0;
    }
    
    // 商品検索メソッド（キーワードで部分一致検索）
    public List<Product> searchProducts(String keyword) throws Exception {
        List<Product> list = new java.util.ArrayList<>();
        Connection con = getConnection();

        // 💡 商品名(name) または 商品説明(descriptionやdescなど) にキーワードが含まれるものを取得
        // ※ カラム名（name, price 等）はご自身のProduct BeanやDBに合わせて調整してください
        String sql = "SELECT * FROM product WHERE name LIKE ? OR description LIKE ? ORDER BY id ASC";
        PreparedStatement st = con.prepareStatement(sql);
        
        // 「%キーワード%」という形を作る（部分一致）
        String wildCardKey = "%" + keyword + "%";
        st.setString(1, wildCardKey);
        st.setString(2, wildCardKey);
        
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            Product p = new Product();
            
            p.setProductId(rs.getString("id"));
            p.setProductName(rs.getString("name"));
            p.setPrice(rs.getInt("price"));
            p.setDescription(rs.getString("description")); 
            
            list.add(p);
        }

        rs.close();
        st.close();
        con.close();

        return list;
    }

}
