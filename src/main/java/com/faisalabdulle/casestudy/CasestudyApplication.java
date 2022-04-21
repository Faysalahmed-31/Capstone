package com.faisalabdulle.casestudy;

import com.faisalabdulle.casestudy.model.Cart;
import com.faisalabdulle.casestudy.model.Role;
import com.faisalabdulle.casestudy.model.User;
import com.faisalabdulle.casestudy.DAO.CartDAO;
import com.faisalabdulle.casestudy.DAO.RoleDAO;
import com.faisalabdulle.casestudy.DAO.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class CasestudyApplication {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(CasestudyApplication.class, args);
	}

	@Bean
	public CommandLineRunner demoData(UserDAO repo, RoleDAO roleRepo, CartDAO cartRepository) {
		return args -> {
			Role adminRole = new Role();
			adminRole.setName("admin");
			roleRepo.save(adminRole);

			Role userRole = new Role();
			userRole.setName("user");
			roleRepo.save(userRole);

			Set<Role> list = new HashSet<>();
			list.add(adminRole);

			Cart cart = new Cart();
			cartRepository.save(cart);

			User newUser = new User();
			newUser.setUsername("admin");
			newUser.setPassword(bCryptPasswordEncoder.encode("admin"));
			newUser.setRoles(list);
			newUser.setCart(cart);

			repo.save(newUser);
		};
	}
}
