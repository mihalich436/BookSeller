package com.dbtask.bookseller.Repository;

import com.dbtask.bookseller.Entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Author> getAuthors(){
        String sql = "select * from Authors";
        List<Author> authors = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Author.class));
        return authors;
    }

    public Author getAuthor(String surname, String name, String lastname){
        String sql = "select * from Authors where surname='" + surname
                + "' and name='" + name
                + "' and lastname='" + lastname + "'";
        List<Author> authors = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Author.class));
        if(authors.isEmpty()) return null;
        return authors.get(0);
    }

    public void saveAuthor(Author author){
        String sql = "insert into Authors values ('"
                + author.getSurname() + "','"
                + author.getName() + "','"
                +author.getLastname() + "')";
        jdbcTemplate.update(sql);
        System.out.println("Saved " + author);
    }
}
