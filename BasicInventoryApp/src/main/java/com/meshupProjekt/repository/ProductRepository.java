package com.meshupProjekt.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.meshupProjekt.model.Category;
import com.meshupProjekt.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	@Query("SELECT p FROM Product p WHERE CONCAT(p.id, ' ', p.name, ' ', p.price, ' ', p.category.name) LIKE %?1%")
	public Page<Product> findAll(String keyword, Pageable pageable);
}
