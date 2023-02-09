package com.dbtask.bookseller.Controller;

import com.dbtask.bookseller.Entity.User;
import com.dbtask.bookseller.Entity.UserInfo;
import com.dbtask.bookseller.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add-staff")
    public String addStaff(@ModelAttribute User user){
        user.setRole("ROLE_STAFF");
        userService.addUser(user);
        return "redirect:/bookseller/administration";
    }

    @GetMapping("/add-staff")
    public String addStaffForm(){
        return "staff-add";
    }

    @GetMapping("/register")
    public String registrationForm(){
        return "registration";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user){
        user.setRole("ROLE_CLIENT");
        userService.addUser(user);
        return "redirect:/login";
    }

    @RequestMapping("/info")
    public String getUserInfo(Model model){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = securityContext.getAuthentication().getName();

        UserInfo user = userService.getUser(username);
        model.addAttribute("userinfo", user);
        return "user-info";
    }
}
