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

import com.nemanjam.ebook.entity.CategoryEntity;
import com.nemanjam.ebook.entity.UserEntity;
import com.nemanjam.ebook.service.CategoryService;
import com.nemanjam.ebook.service.UserService;

@Controller
@SessionAttributes("sessionUser")
public class SessionController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value="/authentication", method=RequestMethod.GET)
	public String LoginDisplay() {

        return "viewLogin";
	}

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String LoginUser(@RequestParam("username") String username, @RequestParam("password") String password, ModelMap model) {
		List<UserEntity> users = userService.findUserByUsername(username);
		
		if (users != null && users.size() > 0) {
			UserEntity user = users.get(0);
			//if (user.getPassword().equals(password)) {
				model.put("sessionUser", user);
				return "redirect:/";
			//}
		}
		model.put("error", "Wrong username/password combination");

		return "viewLogin";
	}

	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String LogoutUser(ModelMap model) {
		model.remove("sessionUser");

		addCategoriesToModel(model);
		return "redirect:/";
	}
	
	@RequestMapping(value="/account", method=RequestMethod.GET)
	public String UserUpdateDisplay(ModelMap model) {
		
		addCategoriesToModel(model);
		return "viewUserUpdate";
	}
	
	@RequestMapping(value="/accountinfo", method=RequestMethod.POST)
	public String UserUpdateInfo(@Valid @ModelAttribute("user") UserEntity user, BindingResult result, ModelMap model) {

		UserEntity sessionUser = (UserEntity) model.get("sessionUser");
		boolean hasUsernameError = false;
		String usernameError = null;

		if (!sessionUser.getUsername().equals(user.getUsername())) {
			List<UserEntity> foundUsernames = userService.findUserByUsername(user.getUsername());
			if (foundUsernames != null && foundUsernames.size() > 0) {
				hasUsernameError = true;
				usernameError = "Username must be unique. This one is already in use.";
			}
		}
		
		if (result.hasErrors() || hasUsernameError) {
			StringBuilder errorBuilder = new StringBuilder();
			if (usernameError != null) {
				errorBuilder.append(usernameError);
				errorBuilder.append("<br/>");
			}		
			
			for (ObjectError objectError : result.getAllErrors()) {
				String error = objectError.getDefaultMessage();
				errorBuilder.append(error);
				errorBuilder.append("<br/>");
			}
			
			model.put("error", errorBuilder.toString());

			addCategoriesToModel(model);
			return "viewUserUpdate";
			
		}
		user.setId(sessionUser.getId());
		user.setPassword(sessionUser.getPassword());
		user.setType(sessionUser.getType());
		user.setCategory(sessionUser.getCategory());
		
		model.put("sessionUser", user);
		userService.updateUser(user);

		addCategoriesToModel(model);
		return "redirect:/";
	}
	
	@RequestMapping(value="/accountpassword", method=RequestMethod.POST)
	public String UserUpdatePassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword, 
			@RequestParam("repeatPassword") String repeatPassword, ModelMap model) {
		
		UserEntity user = (UserEntity) model.get("sessionUser");
		String error = "";
		
		if (newPassword.equals(repeatPassword)) {
			if (user.getPassword().equals(oldPassword)) {
				
				user.setPassword(newPassword);
				model.put("sessionUser", user);
				userService.updateUser(user);

				addCategoriesToModel(model);
				return "redirect:/";
			} else {
				error = "User's old password is not correct";
			}
		} else {
			error = "New password and repeated password are not equal";
		}
		
		model.put("error2", error);

		addCategoriesToModel(model);
		return "viewUserUpdate";
	}
	
	private void addCategoriesToModel(ModelMap model) {
		List<CategoryEntity> categories = categoryService.getAllCategories();
		Collections.sort(categories, (CategoryEntity c1, CategoryEntity c2) -> c1.getName().compareTo(c2.getName()));		
		model.put("categories", categories);		
	}
	
}
