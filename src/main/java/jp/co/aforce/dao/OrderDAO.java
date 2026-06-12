package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jp.co.aforce.beans.Order;
import jp.co.aforce.beans.OrderItem;

public class OrderDAO extends DAO {
	/**
     * 購入処理（注文情報と注文明細をトランザクション内で一括インサートする）
     * @param order 注文情報（明細リストを含んでいるもの）
     * @return 処理がすべて成功したら true, 失敗したら false
     */
    public boolean executePurchase(Order order) {
    	
        Connection conn = null;
        PreparedStatement psOrder = null;
        PreparedStatement psItem = null;
        ResultSet rs = null;
        boolean result = false;

        String sqlOrder = "INSERT INTO orders (user_id, total_price, order_date) VALUES (?, ?, NOW())";
        String sqlItem = "INSERT INTO order_items (order_id, product_id, price) VALUES (?, ?, ?)";

        try {
            conn = this.getConnection();

            // トランザクションを開始
            conn.setAutoCommit(false);

            //  orders テーブルにインサート
            psOrder = conn.prepareStatement(sqlOrder, Statement.RETURN_GENERATED_KEYS);
            psOrder.setString(1, order.getUserId());
            psOrder.setInt(2, order.getTotalPrice());
            
            int affectedRows = psOrder.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("注文情報の保存に失敗しました。");
            }

            // 生成された order_id を引っこ抜く
            rs = psOrder.getGeneratedKeys();
            int generatedOrderId = 0;
            if (rs.next()) {
                generatedOrderId = rs.getInt(1);
            } else {
                throw new SQLException("注文IDの取得に失敗しました。");
            }

            // ③ order_items テーブルにカートの商品をループでインサート（バッチ処理）
            psItem = conn.prepareStatement(sqlItem);
            for (OrderItem item : order.getOrderItems()) {
                psItem.setInt(1, generatedOrderId); // 親の order_id をセット
                psItem.setString(2, item.getProductId());
                psItem.setInt(3, item.getPrice());
                
                psItem.addBatch(); // まとめ打ち用のキューに追加
            }
            
            // 溜まったSQLを一気にドカンと実行！
            psItem.executeBatch();

            //  すべての処理が成功したので、DBにコミット
            conn.commit();
            result = true;
            System.out.println("【OrderDAO】購入処理が正常にコミットされました。注文ID: " + generatedOrderId);

        } catch (Exception e) {
            //  どこか1箇所でもエラーが発生したら、この注文に関する書き込みをすべて無かったことにする
            System.err.println("【OrderDAO】購入処理中にエラーが発生したため、ロールバックします。");
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
        } finally {
            //  後片付け（リソースの解放）
            try { if (rs != null) rs.close(); } catch (SQLException e) {}
            try { if (psOrder != null) psOrder.close(); } catch (SQLException e) {}
            try { if (psItem != null) psItem.close(); } catch (SQLException e) {}
            try { 
                if (conn != null) {
                    conn.setAutoCommit(true); // 接続プール(DataSource)に戻す前のマナーとしてONに戻す
                    conn.close(); //  接続を閉じる（これでDataSourceにコネクションが返却されます）
                } 
            } catch (SQLException e) {}
        }

        return result;
    }
    
 // ⭕ OrderDAO.java の中（クラスの末尾など）に追加します
    public List<Order> findAllOrders() {
        List<Order> orderList = new ArrayList<>();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            
            //  注文履歴を新しい順（降順：DESC）で取得するSQL文
            String sql = "SELECT order_id, user_id, total_price, order_date FROM orders ORDER BY order_date DESC";
            st = con.prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                
                // DBから値を取り出して Order Bean にセット
                // ※ご自身のOrderクラスのsetter名や型（Stringかintかなど）に合わせてください
                order.setOrderId(rs.getInt("order_id"));     // 注文ID
                order.setUserId(rs.getString("user_id"));          // ユーザーID
                order.setTotalPrice(rs.getInt("total_price"));  // 決済金額
                java.sql.Timestamp timestamp = rs.getTimestamp("order_date");
                if (timestamp != null) {
                    order.setOrderDate(timestamp.toLocalDateTime());
                }
                
                //  もし購入された「商品名」や「メールアドレス」もここで一緒に取りたい場合、
                // 本当はSQLで テーブル結合（JOIN） をする必要があります。
                // 一旦は、このシンプルなOrderオブジェクトをリストに詰めて返します。
                
                orderList.add(order);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // いつものクローズ処理
            if (rs != null) { try { rs.close(); } catch (Exception e) {} }
            if (st != null) { try { st.close(); } catch (Exception e) {} }
            if (con != null) { try { con.close(); } catch (Exception e) {} }
        }

        return orderList;
    }

}
