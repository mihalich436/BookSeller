package com.dbtask.bookseller.Entity;

public class Supply {
    private int bookID;
    private int quantity;
    private String time;

    public Supply() {
    }

    public Supply(int bookID, int quantity) {
        this.bookID = bookID;
        this.quantity = quantity;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Supply{" +
                "bookID=" + bookID +
                ", quantity=" + quantity +
                ", time='" + time + '\'' +
                '}';
    }
}
