package com.nemanjam.ebook.controller;

import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nemanjam.ebook.entity.CategoryEntity;
import com.nemanjam.ebook.exception.ExceptionRemovingObject;
import com.nemanjam.ebook.service.CategoryService;

@Controller
@SessionAttributes("sessionUser")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value="/categorymanage", method=RequestMethod.GET)
	public String CategoriesDisplay(ModelMap model) {
		// REQUIRES PERMISSION
		
		addCategoriesToModel(model);
		return "viewCategoriesManage";
	}

	@RequestMapping(value="/categoryupdate", method=RequestMethod.GET)
	public String CategoryUpdateDisplay(@RequestParam("categoryId") String categoryId, ModelMap model) {
		// REQUIRES PERMISSION
		
		int id = Integer.parseInt(categoryId);		
		CategoryEntity category = categoryService.findCategory(id);
		model.put("category", category);

		addCategoriesToModel(model);
		return "viewCategoryUpdate";
	}

	@RequestMapping(value="/categoryadd", method=RequestMethod.GET)
	public String CategoryAddDisplay(ModelMap model) {
		// REQUIRES PERMISSION

		addCategoriesToModel(model);
		return "viewCategoryAdd";
	}
	
	@RequestMapping(value="/categoryupdate", method=RequestMethod.POST)
	public String CategoryUpdate(@Valid @ModelAttribute("category") CategoryEntity category, BindingResult result, ModelMap model) {
		// REQUIRES PERMISSION
		
		if (result.hasErrors()) {
			String error = result.getAllErrors().get(0).getDefaultMessage();
			model.put("error", error);
			model.put("category", category);
			
			addCategoriesToModel(model);
			return "viewCategoryUpdate";
		}
		
		categoryService.updateCategory(category);

		addCategoriesToModel(model);
		return "redirect:/categorymanage";
	}
	
	@RequestMapping(value="/categoryadd", method=RequestMethod.POST)
	public String CategoryAdd(@Valid @ModelAttribute("newCategory") CategoryEntity category, BindingResult result, ModelMap model) {
		// REQUIRES PERMISSION
		
		if (result.hasErrors()) {
			String error = result.getAllErrors().get(0).getDefaultMessage();
			model.put("error", error);
			model.put("category", category);
			
			addCategoriesToModel(model);
			return "viewCategoryUpdate";
		}
		
		categoryService.addCategory(category);

		addCategoriesToModel(model);
		return "redirect:/categorymanage";
	}
	
	@RequestMapping(value="/categorydelete", method=RequestMethod.POST)
	public String CategoryDelete(@RequestParam("categoryId") String categoryId, ModelMap model) {
		// REQUIRES PERMISSION

		int id = Integer.parseInt(categoryId);
		try {
			categoryService.deleteCategory(id);
			
			addCategoriesToModel(model);
			return "redirect:/categorymanage";
		} catch (ExceptionRemovingObject e) {
			model.put("error", "Can not delete category that is in use!");

			addCategoriesToModel(model);
			return "viewCategoriesManage";
		}

	}	
	
	private void addCategoriesToModel(ModelMap model) {
		List<CategoryEntity> categories = categoryService.getAllCategories();
		Collections.sort(categories, (CategoryEntity c1, CategoryEntity c2) -> c1.getName().compareTo(c2.getName()));		
		model.put("categories", categories);		
	}
	
}
