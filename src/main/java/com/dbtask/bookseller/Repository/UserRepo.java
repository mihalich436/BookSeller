package com.dbtask.bookseller.Repository;

import com.dbtask.bookseller.Entity.Role;
import com.dbtask.bookseller.Entity.User;
import com.dbtask.bookseller.Entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepo {

    private List<Role> roles;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Role> getRoles(){
        if(roles != null) return roles;

        String sql = "select * from Roles";
        roles = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Role.class));
        return roles;
    }

    public User authenticate(String login, String password){
        String sql = "select userID from Auth where login='"+login+"' and password='"+password+"'";
        Integer userID = jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println(userID);
        if(userID==null) return null;

        sql = "select * from Users where ID="+userID;
        List<User> users = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(User.class));
        if(users.isEmpty()) return null;
        return users.get(0);
    }

    public void addUser(User user){
        String sql = "insert into users values ('"
                + user.getUsername() + "', '"
                + user.getPassword() + "', 1)";
        jdbcTemplate.update(sql);

        sql = "insert into authorities values ('"
                + user.getUsername() + "','"
                + user.getRole() + "')";
        jdbcTemplate.update(sql);

        sql = "insert into UserInfo values ('" + user.getUsername() + "', '"
                + user.getUserInfo().getSurname() + "', '"
                + user.getUserInfo().getName() + "', '"
                + user.getUserInfo().getLastname() + "','"
                + user.getUserInfo().getPhoneNumber() + "')";
        jdbcTemplate.update(sql);

        System.out.println("Saved user " + user);
    }

    public UserInfo getUser(String username){
        String sql = "select * from UserInfo where username='" + username + "'";
        List<UserInfo> users = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(UserInfo.class));
        if(users.isEmpty()) return null;
        return users.get(0);
    }
/*
    public Role getUsersRole(int userID){
        String sql = "select * from Users where ID="+userID;
        List<User> users = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(User.class));
        if(users.isEmpty()) return null;

        if(roles == null) getRoles();
        return roles.stream().filter(r -> r.getId()==users.get(0).getRoleID()).findFirst().orElse(null);
    }*/
}
