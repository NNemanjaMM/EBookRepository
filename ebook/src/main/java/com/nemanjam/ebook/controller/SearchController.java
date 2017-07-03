package com.nemanjam.ebook.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nemanjam.ebook.model.AdvancedSearchObject;
import com.nemanjam.ebook.model.EBookDisplay;
import com.nemanjam.ebook.model.SimpleSearchObject;
import com.nemanjam.ebook.model.entity.CategoryEntity;
import com.nemanjam.ebook.model.entity.LanguageEntity;
import com.nemanjam.ebook.service.CategoryService;
import com.nemanjam.ebook.service.EBookService;
import com.nemanjam.ebook.service.LanguageService;

@Controller
@SessionAttributes("sessionUser")
public class SearchController {
	
	@Autowired
	private EBookService eBookService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private LanguageService languageService;

	@RequestMapping(value="/search", method=RequestMethod.GET)
	public String SearchDisplay(ModelMap model) {

		addLanguageToModel(model);
		addCategoriesToModel(model);
		return "viewSearch";
	}

	@RequestMapping(value="/searchadvanced", method=RequestMethod.GET)
	public String SearchDisplayAdvanced(ModelMap model) {
		
		addLanguageToModel(model);
		addCategoriesToModel(model);
		return "viewSearchAdvanced";
	}

	@RequestMapping(value="/searchresult", method=RequestMethod.POST)
	public String SearchBooks(@ModelAttribute("params") SimpleSearchObject params, ModelMap model) {

		List<EBookDisplay> books = eBookService.getSimpleSearchResults(params);		

		model.put("books", books);
		addCategoriesToModel(model);
		return "viewSearchResults";
	}

	@RequestMapping(value="/searchresultadvanced", method=RequestMethod.POST)
	public String SearchBooksAdvanced(@ModelAttribute("params") AdvancedSearchObject params, ModelMap model) {

		List<EBookDisplay> books = eBookService.getAdvancedSearchResults(params);		

		model.put("books", books);
		addCategoriesToModel(model);
		return "viewSearchResults";
	}
	
	private void addLanguageToModel(ModelMap model) {
		List<LanguageEntity> languages = languageService.getAllLanguages();
		Collections.sort(languages, (LanguageEntity l1, LanguageEntity l2) -> l1.getName().compareTo(l2.getName()));		
		model.put("languages", languages);		
	}
	
	private void addCategoriesToModel(ModelMap model) {
		List<CategoryEntity> categories = categoryService.getAllCategories();
		Collections.sort(categories, (CategoryEntity c1, CategoryEntity c2) -> c1.getName().compareTo(c2.getName()));		
		model.put("categories", categories);		
	}

}
