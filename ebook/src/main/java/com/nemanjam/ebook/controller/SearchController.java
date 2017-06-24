package com.nemanjam.ebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nemanjam.ebook.service.EBookService;

@Controller
@SessionAttributes("sessionUser")
public class SearchController {
	
	@Autowired
	private EBookService eBookService;

	@RequestMapping(value="/search", method=RequestMethod.GET)
	public String SearchDisplay() {
		return "viewSearch";
	}

	@RequestMapping(value="/searchresult", method=RequestMethod.GET)
	public String SearchBooks(@RequestParam("") String param) {
		// vratiti knjige koje odgovaraju
		// vratiti parametre pretrage
		return "viewSearchResults";
	}

}
