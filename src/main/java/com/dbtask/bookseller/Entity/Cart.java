package com.dbtask.bookseller.Entity;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private String username;
    private List<CartItem> cartItems;

    public Cart() {
        this.cartItems = new ArrayList<>();
    }

    public Cart(String username) {
        this.username = username;
        this.cartItems = new ArrayList<>();
    }

    public void addItem(CartItem item){
        CartItem existingItem = cartItems.stream()
                .filter(i -> i.getBookID() == item.getBookID()).findFirst().orElse(null);
        if(existingItem == null){
            cartItems.add(item);
        }
        else{
            existingItem.addQuantity(item.getQuantity());
        }
    }

    public void clear(){
        cartItems.clear();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "username='" + username + '\'' +
                ", cartItems=" + cartItems +
                '}';
    }
}
