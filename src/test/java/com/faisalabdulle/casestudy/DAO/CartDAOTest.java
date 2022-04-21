package com.faisalabdulle.casestudy.DAO;

import com.faisalabdulle.casestudy.Beans;
import com.faisalabdulle.casestudy.CasestudyApplication;
import com.faisalabdulle.casestudy.model.Book;
import com.faisalabdulle.casestudy.model.Cart;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.HashSet;
import java.util.Set;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = {CasestudyApplication.class, Beans.class})
public class CartDAOTest {

    @Autowired
    private CartDAO cartRepository;

    @Test
    public void saveCartTest() {
        Cart newCart = new Cart();
        Set<Book> bookSet = new HashSet<>();

        Book newBook = new Book();
        newBook.setName("Test name");
        newBook.setDescription("Test description");
        newBook.setAuthor("Test author");
        bookSet.add(newBook);

        newCart.setBooks(bookSet);

        Cart savedCart = cartRepository.save(newCart);
        Book savedBook = savedCart.getBooks().iterator().next();

        Assertions.assertEquals(savedCart.getId(), 1L);
        Assertions.assertEquals(savedBook.getName(), "Test name");
        Assertions.assertEquals(savedBook.getDescription(), "Test description");
        Assertions.assertEquals(savedBook.getAuthor(), "Test author");
    }
}
