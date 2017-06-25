package com.nemanjam.ebook.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nemanjam.ebook.entity.CategoryEntity;
import com.nemanjam.ebook.service.CategoryService;
import com.nemanjam.ebook.service.EBookService;

@Controller
@SessionAttributes("sessionUser")
public class SearchController {
	
	@Autowired
	private EBookService eBookService;
	
	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value="/search", method=RequestMethod.GET)
	public String SearchDisplay(ModelMap model) {
		
		addCategoriesToModel(model);
		return "viewSearch";
	}

	@RequestMapping(value="/searchresult", method=RequestMethod.GET)
	public String SearchBooks(@RequestParam("") String param, ModelMap model) {
		// vratiti knjige koje odgovaraju
		// vratiti parametre pretrage
		
		addCategoriesToModel(model);
		return "viewSearchResults";
	}
	
	private void addCategoriesToModel(ModelMap model) {
		List<CategoryEntity> categories = categoryService.getAllCategories();
		Collections.sort(categories, (CategoryEntity c1, CategoryEntity c2) -> c1.getName().compareTo(c2.getName()));		
		model.put("categories", categories);		
	}

}
