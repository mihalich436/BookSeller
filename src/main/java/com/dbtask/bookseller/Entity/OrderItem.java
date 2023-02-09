package com.dbtask.bookseller.Entity;

public class OrderItem {
    private int id;
    private String name;
    private int authorID;
    private int publisherID;
    private int quantity;
    private int publicationYear;
    private Author author;
    private Publisher publisher;
    float price;
    private Integer assembled;

    public OrderItem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    public int getPublisherID() {
        return publisherID;
    }

    public void setPublisherID(int publisherID) {
        this.publisherID = publisherID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Integer getAssembled() {
        return assembled;
    }

    public void setAssembled(Integer assembled) {
        this.assembled = assembled;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", authorID=" + authorID +
                ", publisherID=" + publisherID +
                ", quantity=" + quantity +
                ", publicationYear=" + publicationYear +
                ", author=" + author +
                ", publisher=" + publisher +
                ", price=" + price +
                ", assembled=" + assembled +
                '}';
    }
}
