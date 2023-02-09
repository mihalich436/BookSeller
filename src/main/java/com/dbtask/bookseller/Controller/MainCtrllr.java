package com.dbtask.bookseller.Controller;

import com.dbtask.bookseller.Entity.Book;
import com.dbtask.bookseller.Repository.UserRepo;
import com.dbtask.bookseller.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/bookseller")
public class MainCtrllr {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BookService bookService;
/*
    @RequestMapping("/login_page")
    public String login(){
        return "login_page";
    }
*/
    @RequestMapping("/main")
    public String mainPage(Model model){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        List<String> roles = securityContext.getAuthentication().getAuthorities()
                .stream().map(GrantedAuthority::getAuthority).toList();

        model.addAttribute("role", roles.get(0));

        return "mainpage";
    }

    @RequestMapping("/administration")
    public String administration(){
        return "administration";
    }
}
