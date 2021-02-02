package com.meshupProjekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meshupProjekt.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	
}
