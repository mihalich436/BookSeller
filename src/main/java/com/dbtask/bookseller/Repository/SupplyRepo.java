package com.dbtask.bookseller.Repository;

import com.dbtask.bookseller.Entity.Supply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SupplyRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveSupply(Supply supply){
        String sql = "insert into Supplies values ("
                + supply.getBookID() + ", "
                + supply.getQuantity()
                + ", GETDATE())";
        jdbcTemplate.update(sql);
    }
}
