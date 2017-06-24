package com.nemanjam.ebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.nemanjam.ebook.service.CategoryService;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;

	public String CategoriesDisplay() {
		// vratiti spisak kategorija
		return "viewCategoriesManage";
	}
	
	public String CategoryUpdateDisplay() {
		// vratiti kategoriju koja se menja
		return "viewCategoryUpdate";
	}
	
	public String CategoryAddDisplay() {

		return "viewCategoryAdd";
	}
	
	public void CategoryUpdate() {
				
		CategoriesDisplay();
	}
	
	public void CategoryAdd() {

		CategoriesDisplay();
	}
	
	public void CategoryDelete() {

		CategoriesDisplay();
	}		
	
}
