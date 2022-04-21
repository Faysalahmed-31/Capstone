package com.faisalabdulle.casestudy.DAO;

import com.faisalabdulle.casestudy.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDAO extends JpaRepository<Role, Long>{
    public Role findByName(String name);
}
