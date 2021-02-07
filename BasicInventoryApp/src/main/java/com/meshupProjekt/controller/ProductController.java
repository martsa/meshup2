package com.meshupProjekt.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.meshupProjekt.Constants;
import com.meshupProjekt.model.Category;
import com.meshupProjekt.model.Product;
import com.meshupProjekt.model.User;
import com.meshupProjekt.repository.CategoryRepository;
import com.meshupProjekt.repository.ProductRepository;

@Controller
public class ProductController {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping("/products")
	public String listProducts(Model model) {
		List<Product> listProduct= productRepository.findAll();
		model.addAttribute("listProducts",listProduct);
	return "products";
	}

	
	
	@GetMapping("/products/new")
	public String showProductNewForm(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("listCategories", categoryRepository.findAll());
		return "product_form";
	}
	
	@PostMapping("products/save")
	public String saveProduct(Product product, HttpServletRequest request) {
		String[] detailIds = request.getParameterValues("detailId");
		String[] detailNames = request.getParameterValues("detailName");
		String[] detailValues = request.getParameterValues("detailValue");
		
		for (int i = 0; i <detailNames.length; i++) {
			if (detailIds != null && detailIds.length > 0) {
				product.addDetail(Integer.valueOf(detailIds[i]), detailNames[i], detailValues[i]);
			} else {
				product.addDetail(detailNames[i], detailValues[i]);
			}
		}
		
		productRepository.save(product);
		return "redirect:/products";
	}
	
	@GetMapping("/products/edit/{id}")
	public String showProductEditForm(@PathVariable Integer id, Model model) {
		model.addAttribute("product", productRepository.findById(id).get());
		model.addAttribute("listCategories", categoryRepository.findAll());
		return "product_form";
	}
	
	@GetMapping("/products/delete/{id}")
	public String deleteProduct(@PathVariable Integer id, Model model) {
		productRepository.deleteById(id);
		return "redirect:/products";
	}
}
