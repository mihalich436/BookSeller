package com.dbtask.bookseller.Service;

import com.dbtask.bookseller.Entity.*;
import com.dbtask.bookseller.Repository.AuthorRepo;
import com.dbtask.bookseller.Repository.BookRepo;
import com.dbtask.bookseller.Repository.PublisherRepo;
import com.dbtask.bookseller.Repository.SupplyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private AuthorRepo authorRepo;
    @Autowired
    private PublisherRepo publisherRepo;
    @Autowired
    private SupplyRepo supplyRepo;

    private final Map<String, Cart> carts = new HashMap<>();

    public List<Book> getBooks(){
        List<Book> books = bookRepo.getBooks();
        if(books==null) return null;
        List<Author> authors = authorRepo.getAuthors();
        if(authors==null) return null;
        List<Publisher> publishers = publisherRepo.getPublishers();
        if(publishers==null) return null;

        for(Book book:books){
            Author author = authors.stream().filter(a -> a.getId()==book.getAuthorID()).findFirst().orElse(null);
            book.setAuthor(author);
            Publisher publisher = publishers.stream().filter(p -> p.getId()== book.getPublisherID()).findFirst().orElse(null);
            book.setPublisher(publisher);
        }

        return books;
    }

    public Book getBook(int id){
        Book book = bookRepo.getBook(id);
        return book;
    }

    public void saveBook(Book book){
        if(book.getAuthorID()==0 && book.getAuthor()!=null){
            if(book.getAuthor().getId() != 0) book.setAuthorID(book.getAuthor().getId());
            else{
                Author author = authorRepo.getAuthor(book.getAuthor().getSurname(),
                        book.getAuthor().getName(),
                        book.getAuthor().getLastname());
                if(author==null) {
                    authorRepo.saveAuthor(book.getAuthor());
                    author = authorRepo.getAuthor(book.getAuthor().getSurname(),
                            book.getAuthor().getName(),
                            book.getAuthor().getLastname());
                }
                book.setAuthorID(author.getId());
            }
        }
        if(book.getPublisherID()==0 && book.getPublisher()!=null){
            if(book.getPublisher().getId() != 0) book.setPublisherID(book.getPublisher().getId());
            else{
                Publisher publisher = publisherRepo.getPublisher(book.getPublisher().getName());
                if(publisher==null){
                    publisherRepo.savePublisher(book.getPublisher());
                    publisher = publisherRepo.getPublisher(book.getPublisher().getName());
                }
                book.setPublisherID(publisher.getId());
            }
        }

        bookRepo.saveBook(book);
        System.out.println("Saved book " + book);
    }

    public List<Author> getAuthors(){
        return authorRepo.getAuthors();
    }

    public List<Publisher> getPublishers(){
        return publisherRepo.getPublishers();
    }

    public void putToCart(String username, CartItem cartItem){
        if(cartItem.getQuantity() <= 0) return;
        Cart cart = carts.get(username);
        if(cart == null){
            cart = new Cart(username);
            carts.put(username, cart);
        }
        cart.addItem(cartItem);
        System.out.println("Carts: " + carts);
    }

    public Cart getCart(String username){
        return carts.get(username);
    }

    public void addSupply(Supply supply){
        if(supply==null
                || supply.getQuantity()==0
                || supply.getBookID()==0)
            return;
        supplyRepo.saveSupply(supply);
    }

    public void setPrice(int bookID, float price){
        if(bookID==0 || price <= 1) return;
        bookRepo.setPrice(bookID, price);
    }
}
