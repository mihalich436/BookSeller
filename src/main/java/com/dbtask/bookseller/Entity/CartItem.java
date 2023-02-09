package com.dbtask.bookseller.Entity;

public class CartItem {
    private int bookID;
    private int quantity;

    public CartItem() {
    }

    public CartItem(int bookID, int quantity) {
        this.bookID = bookID;
        this.quantity = quantity;
    }

    public void addQuantity(int num){
        this.quantity += num;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "bookID=" + bookID +
                ", quantity=" + quantity +
                '}';
    }
}
