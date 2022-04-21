package com.faisalabdulle.casestudy.DAO;

import com.faisalabdulle.casestudy.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDAO extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.name LIKE %:name%")
    List<Book> findAllByNameContainingIgnoreCase(String name);
}
