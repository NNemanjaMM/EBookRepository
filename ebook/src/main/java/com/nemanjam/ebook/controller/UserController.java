package com.nemanjam.ebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.nemanjam.ebook.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

	public String UsersDisplay() {
		// vratiti spisak kategorija
		return "viewUsersManage";
	}
	
	public String UserUpdateDisplay() {
		// vratiti kategoriju koja se menja
		return "viewUserUpdateRole";
	}
	
	public void UserUpdate() {

		UsersDisplay();
	}
	
	public void UserAdd() {

		UsersDisplay();
	}
	
	public void UserDelete() {

		UsersDisplay();
	}		

}
