/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quyen.vegetablestore.shopping;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author To Quyen Phan
 */
public class Cart{ //Tạo cart
    private Map<String, Product> cart;

    public Cart() {
    }

    public Cart(Map<String, Product> cart) {
        this.cart = cart;
    }

    public Map<String, Product> getCart() {
        return cart;
    }

    public void setCart(Map<String, Product> cart) {
        this.cart = cart;
    }
    
    public boolean add(Product product){
        boolean check = false;
        if(this.cart == null){
            this.cart = new HashMap<>();
        }
        if(this.cart.containsKey(product.getProductID())){
            int currentQuantity = this.cart.get(product.getProductID()).getQuantity();
            product.setQuantity(currentQuantity + product.getQuantity());
        }
        this.cart.put(product.getProductID(), product);
        check = true;
        return check;
    }
    
    public boolean remove(String id){
        boolean check = false;
        if(this.cart != null){
            if(this.cart.containsKey(id)){
                this.cart.remove(id);
                check = true;
            }
        }
        return check;
    }
    
    public boolean edit(String id, Product product){
        boolean check = true;
        if(this.cart != null){
            if(this.cart.containsKey(id)){
                this.cart.replace(id, product);
                check = true;
            }
        }
        return check;
    }
    
    public void removeAll(Map<String, Product> cart){
        boolean check = false;
        for(int i = 0; i <= cart.size(); i++){
            check = remove(cart.get(i).getProductID());
        }
    }
}
