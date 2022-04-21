package com.faisalabdulle.casestudy.service;

import com.faisalabdulle.casestudy.exception.NoRecordFoundException;
import com.faisalabdulle.casestudy.model.Book;
import com.faisalabdulle.casestudy.model.Cart;
import com.faisalabdulle.casestudy.model.User;
import com.faisalabdulle.casestudy.DAO.BookDAO;
import com.faisalabdulle.casestudy.DAO.CartDAO;
import com.faisalabdulle.casestudy.DAO.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private BookDAO bookRepository;
    @Autowired
    private CartDAO cartRepository;
    @Autowired
    private UserDAO userRepository;

    @Override
    public int addBookToCart(String username, Long bookId) {
        User user = userRepository.findByUsername(username);
        Cart cart = user.getCart();
        Optional<Book> book = bookRepository.findById(bookId);
        if (!book.isPresent()) {
            throw new IllegalArgumentException("Book not found");
        }
        if (cart == null) {
            cart = new Cart();
            cart.setBooks(new HashSet<>());
        }
        cart.getBooks().add(book.get());
        cartRepository.save(cart);
        user.setCart(cart);
        userRepository.save(user);

        return cart.getBooks().size();
    }

    @Override
    public Set<Book> getCartItemsByUsername(String username) {
        Set<Book> books = new HashSet<>();

        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new NoRecordFoundException("User not found. Custom exception thrown.");
        }

        Cart cart = user.getCart();

        if (cart != null && cart.getBooks() != null) {
            books = cart.getBooks();
        }

        return books;
    }

    public double calculateTotalPrice(Set<Book> books) {
        double totalPrice = books.stream().mapToDouble(o -> o.getPrice()).sum();
        return totalPrice;
    }

    @Override
    public void clearCart(String username) {
        User user = userRepository.findByUsername(username);
        Cart cart = user.getCart();

        if (cart.getBooks().size() > 0) {
            cart.setBooks(new HashSet<>());
            cartRepository.save(cart);
        }
    }
}
