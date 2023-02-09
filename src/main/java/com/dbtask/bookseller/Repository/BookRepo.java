package com.dbtask.bookseller.Repository;

import com.dbtask.bookseller.Entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Book> getBooks(){
        String sql = "select * from Books";
        List<Book> books = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Book.class));
        return books;
    }

    public Book getBook(int id){
        String sql = "select * from Books where ID=" + id;
        List<Book> books = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Book.class));
        if(books.isEmpty()) return null;
        return books.get(0);
    }

    public void saveBook(Book book){
        String sql = "insert into Books values ('"
                +book.getName() + "',"
                +book.getAuthorID() + ","
                +book.getPublisherID() + ","
                +book.getQuantity() + ","
                +book.getPublicationYear() + ","
                + book.getPrice() + ")";

        jdbcTemplate.update(sql);
    }

    public void setPrice(int bookID, float price){
        String sql = "update Books set Price=" + price + " where ID=" + bookID;
        jdbcTemplate.update(sql);
    }

    public void reduceQuantity(int bookID, int quantity){
        String sql = "update Books set Quantity=Quantity-" + quantity + " where ID=" + bookID;
        jdbcTemplate.update(sql);
    }
}
