package com.faisalabdulle.casestudy.DAO;

import com.faisalabdulle.casestudy.Beans;
import com.faisalabdulle.casestudy.CasestudyApplication;
import com.faisalabdulle.casestudy.model.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = {CasestudyApplication.class, Beans.class})
public class BookDAOTest {

    @Autowired
    private BookDAO bookRepository;

    @Test
    public void saveBookTest() {
        Book newBook = new Book();
        newBook.setName("Test name");
        newBook.setDescription("Test description");
        newBook.setAuthor("Test author");
        Book savedBook = bookRepository.save(newBook);

        Assertions.assertEquals(savedBook.getName(), "Test name");
        Assertions.assertEquals(savedBook.getDescription(), "Test description");
        Assertions.assertEquals(savedBook.getAuthor(), "Test author");
    }
}
