package com.meshupProjekt.controller;

import java.util.List;

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
import com.meshupProjekt.model.User;
import com.meshupProjekt.repository.RoleRepository;
import com.meshupProjekt.repository.UserRepository;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	// get list of users from database using repository defualt function findall from userrepo and pass to thymeleaf using model object
	@GetMapping("/users")
	public String showUserList(Model model) {
      List<User> listUsers=userRepository.findAll();
      model.addAttribute("listUsers",listUsers);
      return "users";
	
	}
	
	//creating new user form by retreiving userrole from role repo and pass to thymeleaf using model object 
	
	@GetMapping("/users/new")
	public String showCreateNewUserForm(Model model) {
		model.addAttribute("listRoles", roleRepository.findAll());
		model.addAttribute("user", new User());
		return "user_form";
	}
	
	@PostMapping("/users/save")
	public String saveUser(User user) {
		userRepository.save(user);
		return "redirect:/users";
	}
	
	@GetMapping("/users/edit/{id}")
	public String showCreateNewUserForm(@PathVariable Integer id, Model model) {
		model.addAttribute("listRoles", roleRepository.findAll());
		model.addAttribute("user", userRepository.findById(id).get());
		return "user_form";
	}
	
	@GetMapping("/users/delete/{id}")
	public String deleteUser(@PathVariable Integer id) {
		userRepository.deleteById(id);
		return "redirect:/users";
	}
}
