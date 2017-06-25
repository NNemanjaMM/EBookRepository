package com.nemanjam.ebook.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nemanjam.ebook.entity.CategoryEntity;
import com.nemanjam.ebook.entity.EBookEntity;
import com.nemanjam.ebook.service.CategoryService;
import com.nemanjam.ebook.service.EBookService;

@Controller
@SessionAttributes("sessionUser")
public class EBookController {
	
	@Autowired
	private EBookService eBookService;
	
	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String BooksPreviewDisplayAll(ModelMap model) {
		// vratiti ime kategorije
		// vratiti spisak knjiga

		addCategoriesToModel(model);
		return "viewBooks";
	}

	@RequestMapping(value="/category", method=RequestMethod.GET)
	public String BooksPreviewDisplay(@RequestParam("categoryId") String categoryId, ModelMap model) {
		// vratiti ime kategorije
		// vratiti spisak knjiga

		addCategoriesToModel(model);
		return "viewBooks";
	}

	@RequestMapping(value="/bookmanage", method=RequestMethod.GET)
	public String BooksManageDisplayAll(ModelMap model) {
		// REQUIRES PERMISSION
			
		// ukoliko korisnik ima dozvolu
		// vratiti ime kategorije
		// vratiti spisak knjiga
		addCategoriesToModel(model);
		return "viewBooksManage";
	}

	@RequestMapping(value="/bookmanagecategory", method=RequestMethod.GET)
	public String BooksManageDisplay(@RequestParam("categoryId") String categoryId, ModelMap model) {
		// REQUIRES PERMISSION
		
		// ukoliko korisnik ima dozvolu
		// vratiti ime kategorije
		// vratiti spisak knjiga
		addCategoriesToModel(model);
		return "viewBooksManage";
	}

	@RequestMapping(value="/bookupdate", method=RequestMethod.GET)
	public String BookUpdateDisplay(@RequestParam("bookId") String bookId, ModelMap model) {
		// REQUIRES PERMISSION
		
		// vratiti kljigu koja se menja
		addCategoriesToModel(model);
		return "viewBookUpdate";
	}

	@RequestMapping(value="/bookadd", method=RequestMethod.GET)
	public String BookAddDisplay(ModelMap model) {
		// REQUIRES PERMISSION

		addCategoriesToModel(model);
		return "viewBookAdd";
	}

	@RequestMapping(value="/bookupdate", method=RequestMethod.POST)
	public String BookUpdate(@ModelAttribute("book") EBookEntity book, ModelMap model) {
		// REQUIRES PERMISSION

		addCategoriesToModel(model);
		return "redirect:/bookmanage";
	}

	@RequestMapping(value="/bookadd", method=RequestMethod.POST)
	public String BookAdd(@ModelAttribute("book") EBookEntity book, ModelMap model) {
		// REQUIRES PERMISSION

		addCategoriesToModel(model);
		return "redirect:/bookmanage";
	}


	@RequestMapping(value="/bookdelete", method=RequestMethod.POST)
	public String BookDelete(@RequestParam("bookId") String bookId, ModelMap model) {
		// REQUIRES PERMISSION

		addCategoriesToModel(model);
		return "redirect:/bookmanage";
	}	

	@RequestMapping(value="/bookdownload", method=RequestMethod.GET)
	public String BookDownload(@RequestParam("bookId") String bookId, ModelMap model) {

		addCategoriesToModel(model);
		return "redirect:/";
	}
	
	private void addCategoriesToModel(ModelMap model) {
		List<CategoryEntity> categories = categoryService.getAllCategories();
		Collections.sort(categories, (CategoryEntity c1, CategoryEntity c2) -> c1.getName().compareTo(c2.getName()));		
		model.put("categories", categories);		
	}
}
