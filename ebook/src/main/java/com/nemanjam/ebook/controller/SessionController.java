package com.nemanjam.ebook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nemanjam.ebook.entity.UserEntity;
import com.nemanjam.ebook.service.UserService;

@Controller
@SessionAttributes("sessionUser")
public class SessionController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="/authentication", method=RequestMethod.GET)
	public String LoginDisplay() {
		
        return "viewLogin";
	}

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String LoginUser(@RequestParam("username") String username, @RequestParam("password") String password, ModelMap model) {
		List<UserEntity> user = userService.findUserByUsername(username);
		
		if (user != null && user.size() > 0)
			model.put("sessionUser", user.get(0));
		
		
		return "redirect:/";
	}

	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String LogoutUser(ModelMap model) {
		model.remove("sessionUser");

		return "redirect:/";
	}
	
	@RequestMapping(value="/account", method=RequestMethod.GET)
	public String UserUpdateDisplay() {
		
		return "viewUserUpdate";
	}
	
	@RequestMapping(value="/accountinfo", method=RequestMethod.POST)
	public String UserUpdateInfo(@RequestParam("username") String username, @RequestParam("firstname") String firstName,
								 @RequestParam("lastname") String lastName, @RequestParam("category") String category) {

		return "redirect:/";
	}
	
	@RequestMapping(value="/accountpassword", method=RequestMethod.POST)
	public String UserUpdatePassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword) {

		return "redirect:/";
	}
	
/*	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public void LoginUser(@RequestParam("username") String username, @RequestParam("password") String password) {
		List<UserEntity> users = userService.findUserByUsername(username);
		if (users.size() == 0) {
			System.out.println("no user found");
		}
		UserEntity user = users.get(0);
		System.out.println("login acquired: " + user.getUsername() + ", pass: " + user.getPassword() + ", name: " + user.getFirstName() + ", surname: " + user.getLastName());
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public void CreateUser(@RequestParam("username") String username, @RequestParam("password") String password, 
			@RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname) {
			/*UserEntity user = new UserEntity();
			user.setFirstName(firstname);
			user.setLastName(lastname);
			user.setUsername(username);
			user.setPassword(password);
			user.setType("guest");
			user.setCategory(null);
			userService.addUser(user);
		System.out.println("create acquired: " + username + ", pass: " + password + ", name: " + firstname + ", surname: " + lastname);
	}
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String LoginUser(@ModelAttribute("user") UserEntity user, ModelMap model) {
	
		CategoryEntity ce = new CategoryEntity("Novel");
		CategoryEntity ce1 = new CategoryEntity("History");
		CategoryEntity ce2 = new CategoryEntity("Drama");
		CategoryEntity ce3 = new CategoryEntity("Adventure");
		categoryService.addCategory(ce);
		categoryService.addCategory(ce1);
		categoryService.addCategory(ce2);
		categoryService.addCategory(ce3);
		List<CategoryEntity> cats = categoryService.getAllCategories();
		CategoryEntity cat = categoryService.findCategory(1);
		s
		UserEntity us = new UserEntity();
		us.setFirstName("Garret");
		us.setLastName("Scott");
		us.setUsername("garrets");
		us.setPassword("user");
		us.setType("guest");
		us.setCategory(null);
		us.setId(1);
		userService.addUser(us);
		List<UserEntity> users = userService.getAllUsers();
        model.addAttribute("name", users);

        return "viewLogin";
	}*/
}
