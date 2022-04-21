package com.faisalabdulle.casestudy.DAO;

import com.faisalabdulle.casestudy.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDAO extends JpaRepository<Cart, Long> {
}
