package com.nemanjam.ebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.nemanjam.ebook.service.EBookService;

@Controller
public class SearchController {
	
	@Autowired
	private EBookService eBookService;

	public String SearchDisplay() {
		return "viewSearch";
	}

	public String SearchBooks() {
		// vratiti knjige koje odgovaraju
		// vratiti parametre pretrage
		return "viewSearchResults";
	}

}
