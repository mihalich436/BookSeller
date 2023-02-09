package com.dbtask.bookseller.Service;

import com.dbtask.bookseller.Entity.User;
import com.dbtask.bookseller.Entity.UserInfo;
import com.dbtask.bookseller.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public void addUser(User user){
        if(!user.getPassword().startsWith("{noop}"))
            user.setPassword("{noop}" + user.getPassword());
        userRepo.addUser(user);
    }

    public UserInfo getUser(String username){
        return userRepo.getUser(username);
    }
}
