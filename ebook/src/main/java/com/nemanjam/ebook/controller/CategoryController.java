package com.nemanjam.ebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nemanjam.ebook.entity.CategoryEntity;
import com.nemanjam.ebook.service.CategoryService;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value="/categorymanage", method=RequestMethod.GET)
	public String CategoriesDisplay() {
		if (!hasPermision()) {
			return "redirect:/";
		}
		// vratiti spisak kategorija
		return "viewCategoriesManage";
	}

	@RequestMapping(value="/categoryupdate", method=RequestMethod.GET)
	public String CategoryUpdateDisplay(@RequestParam("categoryId") String categoryId) {
		if (!hasPermision()) {
			return "redirect:/";
		}
		// vratiti kategoriju koja se menja
		return "viewCategoryUpdate";
	}

	@RequestMapping(value="/categoryadd", method=RequestMethod.GET)
	public String CategoryAddDisplay() {
		if (!hasPermision()) {
			return "redirect:/";
		}

		return "viewCategoryAdd";
	}
	
	@RequestMapping(value="/categoryupdate", method=RequestMethod.POST)
	public String CategoryUpdate(@ModelAttribute("category") CategoryEntity category) {
		if (!hasPermision()) {
			return "redirect:/";
		}

		return "redirect:/categorymanage";
	}
	
	@RequestMapping(value="/categoryadd", method=RequestMethod.POST)
	public String CategoryAdd(@ModelAttribute("category") CategoryEntity category) {
		if (!hasPermision()) {
			return "redirect:/";
		}

		return "redirect:/categorymanage";
	}
	
	@RequestMapping(value="/categorydelete", method=RequestMethod.POST)
	public String CategoryDelete(@PathVariable("categoryId") String categoryId) {
		if (!hasPermision()) {
			return "redirect:/";
		}

		return "redirect:/categorymanage";
	}	
	
	private boolean hasPermision() {
		return true;
	}	
	
}
