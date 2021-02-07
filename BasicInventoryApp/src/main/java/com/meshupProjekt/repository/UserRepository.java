package com.meshupProjekt.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.meshupProjekt.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	
}
