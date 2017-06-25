package com.nemanjam.ebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nemanjam.ebook.entity.EBookEntity;
import com.nemanjam.ebook.service.EBookService;

@Controller
@SessionAttributes("sessionUser")
public class EBookController {
	
	@Autowired
	private EBookService eBookService;

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String BooksPreviewDisplayAll() {
		// vratiti ime kategorije
		// vratiti spisak knjiga
		
		return "viewBooks";
	}

	@RequestMapping(value="/category", method=RequestMethod.GET)
	public String BooksPreviewDisplay(@RequestParam("categoryId") String categoryId) {
		// vratiti ime kategorije
		// vratiti spisak knjiga
		
		return "viewBooks";
	}

	@RequestMapping(value="/bookmanage", method=RequestMethod.GET)
	public String BooksManageDisplayAll() {
		// REQUIRES PERMISSION
			
		// ukoliko korisnik ima dozvolu
		// vratiti ime kategorije
		// vratiti spisak knjiga
		return "viewBooksManage";
	}

	@RequestMapping(value="/bookmanagecategory", method=RequestMethod.GET)
	public String BooksManageDisplay(@RequestParam("categoryId") String categoryId) {
		// REQUIRES PERMISSION
		
		// ukoliko korisnik ima dozvolu
		// vratiti ime kategorije
		// vratiti spisak knjiga
		return "viewBooksManage";
	}

	@RequestMapping(value="/bookupdate", method=RequestMethod.GET)
	public String BookUpdateDisplay(@RequestParam("bookId") String bookId) {
		// REQUIRES PERMISSION
		
		// vratiti kljigu koja se menja
		return "viewBookUpdate";
	}

	@RequestMapping(value="/bookadd", method=RequestMethod.GET)
	public String BookAddDisplay() {
		// REQUIRES PERMISSION

		return "viewBookAdd";
	}

	@RequestMapping(value="/bookupdate", method=RequestMethod.POST)
	public String BookUpdate(@ModelAttribute("book") EBookEntity book) {
		// REQUIRES PERMISSION

		return "redirect:/bookmanage";
	}

	@RequestMapping(value="/bookadd", method=RequestMethod.POST)
	public String BookAdd(@ModelAttribute("book") EBookEntity book) {
		// REQUIRES PERMISSION

		return "redirect:/bookmanage";
	}


	@RequestMapping(value="/bookdelete", method=RequestMethod.POST)
	public String BookDelete(@RequestParam("bookId") String bookId) {
		// REQUIRES PERMISSION

		return "redirect:/bookmanage";
	}	

	@RequestMapping(value="/bookdownload", method=RequestMethod.GET)
	public String BookDownload(@RequestParam("bookId") String bookId) {

		return "redirect:/";
	}
}
