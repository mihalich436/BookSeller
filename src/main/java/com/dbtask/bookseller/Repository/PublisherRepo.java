package com.dbtask.bookseller.Repository;

import com.dbtask.bookseller.Entity.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class PublisherRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Publisher> getPublishers(){
        String sql = "select * from Publishers";
        List<Publisher> publishers = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Publisher.class));
        return publishers;
    }

    public Publisher getPublisher(String name){
        String sql = "select * from Publishers where name='" + name + "'";
        List<Publisher> publishers = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Publisher.class));
        if(publishers.isEmpty()) return null;
        return publishers.get(0);
    }

    public void savePublisher(Publisher publisher){
        String sql = "insert into Publishers values ('"
                + publisher.getName() + "','"
                + publisher.getPhoneNumber() + "')";
        jdbcTemplate.update(sql);
        System.out.println("Saved publisher " + publisher);
    }
}
