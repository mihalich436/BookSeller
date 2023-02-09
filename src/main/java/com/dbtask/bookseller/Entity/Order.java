package com.dbtask.bookseller.Entity;

import java.util.List;

public class Order {
    private int id;
    private String clientID;
    private String creationDate;
    private int statusID;
    private String status;
    List<OrderItem> items;

    public Order() {
    }

    public Order(String clientID, int statusID) {
        this.clientID = clientID;
        this.statusID = statusID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public OrderItem getItem(int itemID){
        return items.stream().filter(i -> i.getId() == itemID).findFirst().orElse(null);
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", clientID='" + clientID + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", statusID=" + statusID +
                ", status=" + status +
                ", items=" + items +
                '}';
    }
}
