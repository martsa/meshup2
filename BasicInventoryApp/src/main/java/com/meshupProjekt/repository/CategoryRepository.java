package com.meshupProjekt.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.meshupProjekt.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
	
}
