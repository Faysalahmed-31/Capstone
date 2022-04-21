package com.faisalabdulle.casestudy.DAO;

import com.faisalabdulle.casestudy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
