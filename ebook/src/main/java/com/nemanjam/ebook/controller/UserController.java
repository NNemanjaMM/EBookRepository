package com.nemanjam.ebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nemanjam.ebook.entity.UserEntity;
import com.nemanjam.ebook.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="/usermanage", method=RequestMethod.GET)
	public String UsersDisplay() {
		if (!hasPermision()) {
			return "redirect:/";
		}
		
		// vratiti spisak kategorija
		return "viewUsersManage";
	}

	@RequestMapping(value="/userupdate", method=RequestMethod.GET)
	public String UserUpdateDisplay(@RequestParam("userId") String userId) {
		if (!hasPermision()) {
			return "redirect:/";
		}
		
		// vratiti kategoriju koja se menja
		return "viewUserUpdateRole";
	}
	
	@RequestMapping(value="/userupdate", method=RequestMethod.POST)
	public String UserUpdate(@ModelAttribute("user") UserEntity user) {
		if (!hasPermision()) {
			return "redirect:/";
		}

		return "redirect:/usermanage";	
	}
	
	@RequestMapping(value="/useradd", method=RequestMethod.POST)
	public String UserAdd(@ModelAttribute("user") UserEntity user) {
		if (!hasPermision()) {
			return "redirect:/";
		}

		return "redirect:/usermanage";	
	}
	
	@RequestMapping(value="/userdelete", method=RequestMethod.POST)
	public String UserDelete(@RequestParam("userId") String userId) {
		if (!hasPermision()) {
			return "redirect:/";
		}

		return "redirect:/usermanage";		
	}		
	
	private boolean hasPermision() {
		return true;
	}	

}
