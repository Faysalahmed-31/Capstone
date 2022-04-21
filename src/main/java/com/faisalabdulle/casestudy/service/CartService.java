package com.faisalabdulle.casestudy.service;

import com.faisalabdulle.casestudy.model.Book;

import java.util.Set;

public interface CartService {
    int addBookToCart(String username, Long bookId);
    Set<Book> getCartItemsByUsername(String username);
    double calculateTotalPrice(Set<Book> book);
    void clearCart(String username);
}
