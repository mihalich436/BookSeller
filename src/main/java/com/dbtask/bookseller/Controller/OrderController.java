package com.dbtask.bookseller.Controller;

import com.dbtask.bookseller.Entity.Cart;
import com.dbtask.bookseller.Entity.Order;
import com.dbtask.bookseller.Entity.OrderItem;
import com.dbtask.bookseller.Service.BookService;
import com.dbtask.bookseller.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private BookService bookService;
    @Autowired
    private OrderService orderService;

    @RequestMapping("/")
    public String getClientsOrders(Model model){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = securityContext.getAuthentication().getName();

        List<Order> orders = orderService.getClientsOrders(username, "");
        model.addAttribute("orders", orders);
        return "orders-clients";
    }

    @RequestMapping("/create")
    public String createOrder(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = securityContext.getAuthentication().getName();

        Cart cart = bookService.getCart(username);
        if(cart != null) orderService.createOrder(cart);

        return "redirect:/orders/";
    }

    @RequestMapping("/staff")
    public String getStaffOrders(Model model){
        List<Order> orders = orderService.getClientsOrders(null, "Принят");
        model.addAttribute("orders", orders);
        return "orders-staff";
    }

    @RequestMapping("/staff-assemble")
    public String getStaffAssembleOrders(Model model){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = securityContext.getAuthentication().getName();

        List<Order> orders = orderService.getAssemblingOrder(username);
        model.addAttribute("orders", orders);
        return "orders-assemble";
    }

    @RequestMapping("/assemble/{orderID}")
    public String assembleOrder(@PathVariable int orderID){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = securityContext.getAuthentication().getName();

        orderService.startAssemblingOrder(orderID, username);
        return "redirect:/orders/staff-assemble";
    }

    @RequestMapping("/assemble-stop/{orderID}")
    public String stopAssembleOrder(@PathVariable int orderID){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = securityContext.getAuthentication().getName();

        orderService.stopAssemblingOrder(orderID, username);
        return "redirect:/orders/staff";
    }

    @PostMapping("/assemble-item/{orderID}")
    public String assembleOrderItem(@PathVariable int orderID, @ModelAttribute OrderItem item){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = securityContext.getAuthentication().getName();

        orderService.assembleOrderItem(orderID, item, username);

        return "redirect:/orders/staff-assemble";
    }

    @RequestMapping("/find-form")
    public String getSearchForm(){
        return "order-find";
    }

    @PostMapping("/find")
    public String findOrder(@ModelAttribute Order order, Model model){
        order = orderService.getOrder(order.getId());
        model.addAttribute("order", order);
        return "order-info";
    }

    @RequestMapping("/set-status/{orderID}/{statusID}")
    public String setOrderStatus(@PathVariable int orderID, @PathVariable int statusID, Model model){
        orderService.setStatus(orderID, statusID);
        Order order = orderService.getOrder(orderID);
        model.addAttribute("order", order);
        return "order-info";
    }
}
