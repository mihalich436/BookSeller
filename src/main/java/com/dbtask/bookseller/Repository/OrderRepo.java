package com.dbtask.bookseller.Repository;

import com.dbtask.bookseller.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class OrderRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private List<OrderStatus> statuses;

    public List<Order> getOrders(String username, String statusName){
        String sql;
        if(username!=null) sql = "select * from OrdersView where ClientID='" + username + "'";
        else sql = "select * from OrdersView where Status='" + statusName + "'";
        List<Order> orders = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Order.class));

        sql = "select ID, Name, AuthorID, PublisherID, PublicationYear, Quantity, Price, Assembled from OrderBookView where OrderID=";
        for(Order order: orders){
            order.setItems(jdbcTemplate.query(sql + order.getId(), BeanPropertyRowMapper.newInstance(OrderItem.class)));
        }

        return orders;
    }

    public Order getOrder(int orderID){
        String sql = "select * from OrdersView where ID=" + orderID;
        List<Order> orders = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Order.class));
        if(orders.isEmpty()) return null;
        Order order = orders.get(0);

        sql = "select ID, Name, AuthorID, PublisherID, PublicationYear, Quantity, Price, Assembled from OrderBookView where OrderID=";
        order.setItems(jdbcTemplate.query(sql + order.getId(), BeanPropertyRowMapper.newInstance(OrderItem.class)));

        return order;
    }

    public void createOrder(Order order, Cart cart){
        String INSERT_MESSAGE_SQL
                = "insert into Orders values(?, GETDATE(), " + order.getStatusID() + ") ";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(INSERT_MESSAGE_SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, order.getClientID());
            return ps;
        }, keyHolder);

        order.setId(keyHolder.getKey().intValue());

        String sql;
        for(CartItem item: cart.getCartItems()){
            sql = "insert into OrderBook values ("
                    + order.getId() + ","
                    + item.getBookID() + ","
                    + item.getQuantity() + ","
                    + "(select Price from Books where ID="
                    + item.getBookID() + "))";
            jdbcTemplate.update(sql);
        }
    }

    public void setOrderStatus(int orderID, int status){
        String sql = "update Orders set Status='" + status + "' where ID=" + orderID;
        jdbcTemplate.update(sql);
    }

    public String getStaffAssembleOrder(int orderID){
        String sql = "select StaffID from StaffAssembleOrder where OrderID=" + orderID;
        try{
            String staffID = jdbcTemplate.queryForObject(sql, String.class);
            return staffID;
        }
        catch (EmptyResultDataAccessException empty){
            return null;
        }
    }

    public List<Integer> getStaffAssembleOrder(String username){
        String sql = "select OrderID from StaffAssembleOrder where StaffID='" + username + "'";
        List<Integer> orderIDs = jdbcTemplate.queryForList(sql, Integer.class);
        //System.out.println("Got orders: " + orderIDs.size());
        return orderIDs;
    }

    public void addStaffAssembleOrder(int orderID, String username){
        String sql = "insert into StaffAssembleOrder values (" + orderID + ", '" + username + "')";
        jdbcTemplate.update(sql);
        sql = "update Orders set Status=2 where ID=" + orderID;
        jdbcTemplate.update(sql);
    }

    public void deleteStaffAssembleOrder(int orderID){
        String sql = "delete from StaffAssembleOrder where OrderID=" + orderID;
        jdbcTemplate.update(sql);
        sql = "update Orders set Status=1 where ID=" + orderID;
        jdbcTemplate.update(sql);
    }

    public void saveOrderItemAssemble(int orderID, OrderItem item, String username){
        String sql = "select Quantity from AssembleOrders where OrderID="
                + orderID + " and BookID="
                + item.getId() + " and StaffID='"
                + username + "'";

        Integer quantity = null;
        try{
            quantity = jdbcTemplate.queryForObject(sql, Integer.class);
        }
        catch (EmptyResultDataAccessException empty){
            quantity = null;
        }

        if(quantity == null){
            sql = "insert into AssembleOrders values ("
                    + orderID + ","
                    + item.getId() + ",'"
                    + username + "',"
                    + item.getAssembled() + ")";
        }
        else{
            sql = "update AssembleOrders set Quantity=Quantity+" + item.getAssembled() + " where OrderID="
                    + orderID + " and BookID="
                    + item.getId() + " and StaffID='"
                    + username + "'";
        }

        jdbcTemplate.update(sql);
    }

    public List<OrderStatus> getStatuses(){
        if(statuses != null) return statuses;

        String sql = "select * from OrderStatuses";
        statuses = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(OrderStatus.class));
        return statuses;
    }
}
