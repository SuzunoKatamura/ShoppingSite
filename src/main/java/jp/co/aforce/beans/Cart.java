package jp.co.aforce.beans;

import java.util.ArrayList;
import java.util.List;

public class Cart implements java.io.Serializable {
	
	// カートに入っている商品のリスト
    private List<CartItem> items = new ArrayList<>();

    public Cart() {}

    // 商品リストを取得する
    public List<CartItem> getItems() {
        return items;
    }

    // カートに商品を追加するメソッド
    public void addItem(CartItem newItem) {
        // すでにカートに同じ商品が入っているかチェック
        for (CartItem item : items) {
            if (item.getProductId().equals(newItem.getProductId())) {
                return;
            }
        }
        // 新しい商品ならリストにそのまま追加
        items.add(newItem);
    }

    // カートから指定した商品を削除するメソッド（カート画面用）
    public void removeItem(String productId) {
        items.removeIf(item -> item.getProductId().equals(productId));
    }

    //  サーブレットで呼び出す、カート内の「合計金額」を自動計算する魔法のメソッド
    public int getTotalPrice() {
        int total = 0;
        for (CartItem item : items) {
            total += item.getSubtotal(); // 各商品の小計を足していく
        }
        return total;
    }

    // カートを完全に空っぽにするメソッド（購入完了用）
    public void clear() {
        items.clear();
    }

}
