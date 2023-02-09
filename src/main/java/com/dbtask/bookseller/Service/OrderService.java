package com.dbtask.bookseller.Service;

import com.dbtask.bookseller.Entity.*;
import com.dbtask.bookseller.Repository.AuthorRepo;
import com.dbtask.bookseller.Repository.BookRepo;
import com.dbtask.bookseller.Repository.OrderRepo;
import com.dbtask.bookseller.Repository.PublisherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private AuthorRepo authorRepo;
    @Autowired
    private PublisherRepo publisherRepo;
    @Autowired
    private BookRepo bookRepo;

    private final Map<OrderStatusEnum, OrderStatus> statusIDs = new HashMap<>();

    public List<Order> getClientsOrders(String username, String statusName){
        List<Order> orders = orderRepo.getOrders(username, statusName);
        if(orders.isEmpty()) return null;

        List<Author> authors = authorRepo.getAuthors();
        if(authors==null) return null;
        List<Publisher> publishers = publisherRepo.getPublishers();
        if(publishers==null) return null;

        for(Order order: orders){
            for(OrderItem book: order.getItems()){
                Author author = authors.stream().filter(a -> a.getId()==book.getAuthorID()).findFirst().orElse(null);
                book.setAuthor(author);
                Publisher publisher = publishers.stream().filter(p -> p.getId()== book.getPublisherID()).findFirst().orElse(null);
                book.setPublisher(publisher);
            }
        }
        return orders;
    }

    public Order getOrder(int orderID){
        return orderRepo.getOrder(orderID);
    }

    public void createOrder(Cart cart){
        Order order = new Order(cart.getUsername(), getStatusID(OrderStatusEnum.ACCEPTED));
        orderRepo.createOrder(order, cart);
        cart.clear();
    }

    public void setStatus(int orderID, int statusID){
        orderRepo.setOrderStatus(orderID, statusID);
    }

    public List<Order> getAssemblingOrder(String username){
        List<Integer> orderIDs = orderRepo.getStaffAssembleOrder(username);
        List<Order> orders = getClientsOrders(null, "Формируется");
        if (orders == null) return null;
        List<Order> assembledOrders = new ArrayList<>(orders.size());

        for(int orderID : orderIDs){
            orders.stream().filter(o -> o.getId() == orderID).findFirst().ifPresent(assembledOrders::add);
        }

        return assembledOrders;
    }

    public void startAssemblingOrder(int orderID, String username){
        String staffID = orderRepo.getStaffAssembleOrder(orderID);
        if(staffID != null) return;
        orderRepo.addStaffAssembleOrder(orderID, username);
    }

    public void stopAssemblingOrder(int orderID, String username){
        String staffID = orderRepo.getStaffAssembleOrder(orderID);
        if(!staffID.equals(username)) return;
        orderRepo.deleteStaffAssembleOrder(orderID);
    }

    public void assembleOrderItem(int orderID, OrderItem item, String username){

        List<Order> orders = getClientsOrders(null, "Формируется");
        Order order = orders.stream().filter(o -> o.getId()==orderID).findFirst().orElse(null);

        OrderItem foundItem = order.getItem(item.getId());

        if(foundItem == null) return;
        if(foundItem.getAssembled() != null && foundItem.getQuantity() - foundItem.getAssembled() < item.getAssembled()) return;
        if(foundItem.getAssembled()==null) foundItem.setAssembled(0);

        Book book = bookRepo.getBook(item.getId());
        if(item.getAssembled() > book.getQuantity()) return;
        bookRepo.reduceQuantity(book.getId(), item.getAssembled());

        orderRepo.saveOrderItemAssemble(orderID, item, username);

        foundItem.setAssembled(foundItem.getAssembled() + item.getAssembled());

        for(OrderItem orderItem:order.getItems()){
            if(orderItem.getAssembled()==null) orderItem.setAssembled(0);
            if(orderItem.getQuantity() != orderItem.getAssembled()) return;
        }

        orderRepo.setOrderStatus(orderID, getStatusID(OrderStatusEnum.READY));
    }

    public int getStatusID(OrderStatusEnum statusNum){
        if(!statusIDs.isEmpty()) return statusIDs.get(statusNum).getId();
        List<OrderStatus> orderStatuses = orderRepo.getStatuses();
        for(OrderStatus status: orderStatuses){
            switch (status.getName()){
                case "Принят" -> statusIDs.put(OrderStatusEnum.ACCEPTED, status);
                case "Формируется" -> statusIDs.put(OrderStatusEnum.ASSEMBLING, status);
                case "Готов к выдаче" -> statusIDs.put(OrderStatusEnum.READY, status);
                case "Выдан" -> statusIDs.put(OrderStatusEnum.DELIVERED, status);
                case "Возвращен" -> statusIDs.put(OrderStatusEnum.RETURNED, status);
                case "Расформируется" -> statusIDs.put(OrderStatusEnum.DISASSEMBLING, status);
                case "Расформирован" -> statusIDs.put(OrderStatusEnum.DISASSEMBLED, status);
            }
        }
        return statusIDs.get(statusNum).getId();
    }
}
