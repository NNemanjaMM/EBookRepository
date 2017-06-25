package com.nemanjam.ebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nemanjam.ebook.entity.UserEntity;
import com.nemanjam.ebook.service.UserService;

@Controller
@SessionAttributes("sessionUser")
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="/usermanage", method=RequestMethod.GET)
	public String UsersDisplay() {
		// REQUIRES PERMISSION
		
		// vratiti spisak kategorija
		return "viewUsersManage";
	}

	@RequestMapping(value="/userupdate", method=RequestMethod.GET)
	public String UserUpdateDisplay(@RequestParam("userId") String userId) {
		// REQUIRES PERMISSION
		
		// vratiti kategoriju koja se menja
		return "viewUserUpdateRole";
	}

	@RequestMapping(value="/useradd", method=RequestMethod.GET)
	public String UserAddDisplay() {
		// REQUIRES PERMISSION


		return "viewUserAdd";
	}
	
	@RequestMapping(value="/userupdate", method=RequestMethod.POST)
	public String UserUpdate(@ModelAttribute("user") UserEntity user) {
		// REQUIRES PERMISSION

		return "redirect:/usermanage";	
	}
	
	@RequestMapping(value="/useradd", method=RequestMethod.POST)
	public String UserAdd(@ModelAttribute("user") UserEntity user) {
		// REQUIRES PERMISSION

		return "redirect:/usermanage";	
	}
	
	@RequestMapping(value="/userdelete", method=RequestMethod.POST)
	public String UserDelete(@RequestParam("userId") String userId) {
		// REQUIRES PERMISSION

		return "redirect:/usermanage";		
	}

}
