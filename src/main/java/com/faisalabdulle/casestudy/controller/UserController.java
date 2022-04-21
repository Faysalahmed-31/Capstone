package com.faisalabdulle.casestudy.controller;

import com.faisalabdulle.casestudy.exception.NoRecordFoundException;
import com.faisalabdulle.casestudy.model.Book;
import com.faisalabdulle.casestudy.model.User;
import com.faisalabdulle.casestudy.DAO.BookDAO;
import com.faisalabdulle.casestudy.DAO.CartDAO;
import com.faisalabdulle.casestudy.service.CartService;
import com.faisalabdulle.casestudy.service.SecurityService;
import com.faisalabdulle.casestudy.service.UserService;
import com.faisalabdulle.casestudy.validator.UserValidator;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private BookDAO bookRepository;

    @Autowired
    private CartDAO cartRepository;

    @Autowired
    private CartService cartService;


    //Logger logger = (Logger) LoggerFactory.getLogger(UserController.class);

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
      //logger.trace("errors happened ");
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult,
                               Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);

        return "redirect:/welcome";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "welcome";
    }

    @GetMapping("/search")
    public String searchBooks(Model model, @RequestParam String name) {
        List<Book> books = bookRepository.findAllByNameContainingIgnoreCase(name);
        model.addAttribute("books", books);
        return "welcome";
    }

    @GetMapping("/book/{id}")
    public String getBookDetail(Model model, @PathVariable Long id) {
        model.addAttribute("book", bookRepository.findById(id).get());
        return "book";
    }

    @PostMapping("/book/create")
    public ResponseEntity<Long> getBookDetail(@RequestBody Book book) {
        if (book.getImageUrl() == null) {
            book.setImageUrl("resources/img/default.jpg");
        }
        Book createdBook = bookRepository.save(book);
        return new ResponseEntity<>(createdBook.getId(), HttpStatus.OK);
    }

    @GetMapping("/cart")
    public String checkoutPage(Model model, Principal principal) {
        Set<Book> books = cartService.getCartItemsByUsername(principal.getName());

        model.addAttribute("totalPrice", String.format("%.2f", cartService.calculateTotalPrice(books)));
        model.addAttribute("totalItems", books.size());
        model.addAttribute("cartItems", books);
        return "cart";
    }

    @PostMapping("/cart/add/{bookId}")
    public ResponseEntity<Integer> addToCart(Model model, Principal principal, @PathVariable Long bookId) {
        String username = principal.getName();
        int cartCount = cartService.addBookToCart(username, bookId);
        return new ResponseEntity<>(cartCount, HttpStatus.OK);
    }

    @GetMapping("/clear-cart")
    public String clearCart(Model model, Principal principal) {
        cartService.clearCart(principal.getName());

        model.addAttribute("totalPrice", 0.00f);
        model.addAttribute("totalItems", 0);
        model.addAttribute("cartItems", new ArrayList<>());
        return "cart";
    }

    @GetMapping("/cart/total")
    public ResponseEntity<Integer> getCartCount(Model model, Principal principal) {
        Set<Book> books = cartService.getCartItemsByUsername(principal.getName());
        return new ResponseEntity<>(books.size(), HttpStatus.OK);
    }

    @GetMapping("/book/add")
    public String addNewBook() {
        return "addBook";
    }

    @ExceptionHandler(NoRecordFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String handleNoRecordFoundException(NoRecordFoundException ex) {
        return ex.getMessage();
    }
}
