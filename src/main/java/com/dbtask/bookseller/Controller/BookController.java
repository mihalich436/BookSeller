package com.dbtask.bookseller.Controller;

import com.dbtask.bookseller.Entity.*;
import com.dbtask.bookseller.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/")
    public String books(Model model){
        List<Book> books = bookService.getBooks();
        model.addAttribute("books", books);

        SecurityContext securityContext = SecurityContextHolder.getContext();
        boolean authenticated = !Objects.equals(securityContext.getAuthentication().getName(), "anonymousUser");

        model.addAttribute("authenticated", authenticated);

        return "books";
    }

    @GetMapping("/add")
    public String getBookAddForm(Model model){
        List<Author> authors = bookService.getAuthors();
        List<Publisher> publishers = bookService.getPublishers();
        model.addAttribute("authors", authors);
        model.addAttribute("publishers", publishers);

        /*Book book = new Book();
        book.setAuthor(new Author());
        model.addAttribute("book", book);*/

        return "book-add";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book){
        bookService.saveBook(book);
        return "redirect:/bookseller/administration";
    }

    @PostMapping("/to-cart")
    public String postToCart(@ModelAttribute CartItem cartItem){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = securityContext.getAuthentication().getName();
        if(!Objects.equals(username, "anonymousUser")){
            bookService.putToCart(username, cartItem);
        }
        return "redirect:/books/";
    }

    @RequestMapping("/cart")
    public String getCart(Model model){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = securityContext.getAuthentication().getName();

        Cart cart = bookService.getCart(username);

        if(cart != null) model.addAttribute("cartItems", cart.getCartItems());

        return "cart";
    }

    @GetMapping("/supply-page")
    public String supplyForm(Model model){
        List<Book> books = bookService.getBooks();
        model.addAttribute("books", books);
        return "book-supply";
    }

    @PostMapping("/supply")
    public String supplyBook(@ModelAttribute Supply supply){
        bookService.addSupply(supply);
        return "redirect:/books/supply-page";
    }

    @GetMapping("/set-price-page")
    public String setPriceForm(Model model){
        List<Book> books = bookService.getBooks();
        model.addAttribute("books", books);
        return "book-set-price";
    }

    @PostMapping("/set-price")
    public String setBookPrice(Book book){
        bookService.setPrice(book.getId(), book.getPrice());
        return "redirect:/books/set-price-page";
    }
}
