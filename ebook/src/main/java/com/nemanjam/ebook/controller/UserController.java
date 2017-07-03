package com.nemanjam.ebook.controller;

import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nemanjam.ebook.model.entity.CategoryEntity;
import com.nemanjam.ebook.model.entity.UserEntity;
import com.nemanjam.ebook.service.CategoryService;
import com.nemanjam.ebook.service.UserService;

@Controller
@SessionAttributes("sessionUser")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value="/usermanage", method=RequestMethod.GET)
	public String UsersDisplay(ModelMap model) {
		// REQUIRES PERMISSION
		
		List<UserEntity> users = userService.getAllUsers();
		Collections.sort(users, (UserEntity u1, UserEntity u2) -> u1.getFirstName().compareTo(u2.getFirstName()));
		
		model.put("users", users);
		
		addCategoriesToModel(model);
		return "viewUsersManage";
	}

	@RequestMapping(value="/useradd", method=RequestMethod.GET)
	public String UserAddDisplay(ModelMap model) {
		// REQUIRES PERMISSION

		addCategoriesToModel(model);
		return "viewUserAdd";
	}

	@RequestMapping(value="/userupdate", method=RequestMethod.GET)
	public String UserUpdateDisplay(@RequestParam("userId") String userId, ModelMap model) {
		// REQUIRES PERMISSION

		int id = Integer.parseInt(userId);		
		UserEntity user = userService.findUser(id);
		model.put("user", user);
		
		addCategoriesToModel(model);
		return "viewUserUpdateRole";
	}
	
	@RequestMapping(value="/userupdate", method=RequestMethod.POST)
	public String UserUpdate(@Valid @ModelAttribute("user") UserEntity user, BindingResult result, ModelMap model) {
		// REQUIRES PERMISSION

		if (result.hasErrors()) {
			StringBuilder errorBuilder = new StringBuilder();
			
			for (ObjectError objectError : result.getAllErrors()) {
				String error = objectError.getDefaultMessage();
				errorBuilder.append(error);
				errorBuilder.append("<br/>");
			}
			
			model.put("error", errorBuilder.toString());

			addCategoriesToModel(model);
			return "viewUserUpdateRole";			
		}
		
		if (user.getCategory() == null || user.getCategory().getId() == 8888) {
			user.setCategory(null);
		}
		
		userService.updateUser(user);
		
		
		addCategoriesToModel(model);
		return "redirect:/usermanage";	
	}
	
	@RequestMapping(value="/useradd", method=RequestMethod.POST)
	public String UserAdd(@Valid @ModelAttribute("user") UserEntity user, 
			@RequestParam("repeatPassword") String repeatPassword, BindingResult result, ModelMap model) {
		// REQUIRES PERMISSION
		
		boolean hasUsernameError = false;
		boolean hasPasswordError = false;
		String usernameError = null;
		String passwordError = null;

		List<UserEntity> foundUsernames = userService.findUserByUsername(user.getUsername());
		if (foundUsernames != null && foundUsernames.size() > 0) {
			hasUsernameError = true;
			usernameError = "Username must be unique. This one is already in use.";
		}
		
		if (!user.getPassword().equals(repeatPassword)) {
			hasPasswordError = true;
			passwordError = "Passwords are not matching.";
		}

		if (result.hasErrors() || hasUsernameError || hasPasswordError) {
			StringBuilder errorBuilder = new StringBuilder();
			if (hasUsernameError) {
				errorBuilder.append(usernameError);
				errorBuilder.append("<br/>");
			}			
			if (hasPasswordError) {
				errorBuilder.append(passwordError);
				errorBuilder.append("<br/>");
			}	
			
			for (ObjectError objectError : result.getAllErrors()) {
				String error = objectError.getDefaultMessage();
				errorBuilder.append(error);
				errorBuilder.append("<br/>");
			}
			
			model.put("error", errorBuilder.toString());

			addCategoriesToModel(model);
			return "viewUserAdd";			
		}
		
		if (user.getCategory() == null || user.getCategory().getId() == 8888) {
			user.setCategory(null);
		}
		
		userService.addUser(user);

		addCategoriesToModel(model);
		return "redirect:/usermanage";	
	}
	
	@RequestMapping(value="/userdelete", method=RequestMethod.POST)
	public String UserDelete(@RequestParam("userId") String userId, ModelMap model) {
		// REQUIRES PERMISSION
		
		int id = Integer.parseInt(userId);
		UserEntity sessionUser = (UserEntity) model.get("sessionUser");
		if (sessionUser.getId() == id) {
			model.put("error", "Can not delete user that is currently logged in");	
			
			List<UserEntity> users = userService.getAllUsers();
			Collections.sort(users, (UserEntity u1, UserEntity u2) -> u1.getFirstName().compareTo(u2.getFirstName()));
			model.put("users", users);
			
			addCategoriesToModel(model);
			return "viewUsersManage";	
		}
		
		userService.deleteUser(id);

		addCategoriesToModel(model);
		return "redirect:/usermanage";		
	}
	
	private void addCategoriesToModel(ModelMap model) {
		List<CategoryEntity> categories = categoryService.getAllCategories();
		Collections.sort(categories, (CategoryEntity c1, CategoryEntity c2) -> c1.getName().compareTo(c2.getName()));		
		model.put("categories", categories);		
	}

}
