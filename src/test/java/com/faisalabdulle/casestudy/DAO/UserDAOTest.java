package com.faisalabdulle.casestudy.DAO;

import com.faisalabdulle.casestudy.Beans;
import com.faisalabdulle.casestudy.CasestudyApplication;
import com.faisalabdulle.casestudy.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = {CasestudyApplication.class, Beans.class})
public class UserDAOTest {

    @Autowired
    private UserDAO userRepository;

    @Test
    public void findUserByUsernameTest() {
        User savedUser = userRepository.findByUsername("admin");

        Assertions.assertEquals(savedUser.getUsername(), "admin");
    }
}
