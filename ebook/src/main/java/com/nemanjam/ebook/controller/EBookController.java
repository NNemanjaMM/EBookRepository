package com.nemanjam.ebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.nemanjam.ebook.service.EBookService;

@Controller
public class EBookController {
	
	@Autowired
	private EBookService eBookService;

	public String BooksPreviewDisplay() {
		// vratiti ime kategorije
		// vratiti spisak knjiga
		return "viewBooks";
	}

	public String BooksManageDisplay() {
		// vratiti ime kategorije
		// vratiti spisak knjiga
		return "viewBooksManage";
	}
	
	public String BookUpdateDisplay() {
		// vratiti kljigu koja se menja
		return "viewBookUpdate";
	}
	
	public String BookAddDisplay() {

		return "viewBookAdd";
	}
	
	public void BookUpdate() {
				
		BooksManageDisplay();
	}
	
	public void BookAdd() {

		BooksManageDisplay();
	}
	
	public void BookDelete() {

		BooksManageDisplay();
	}	
	
	public void BookDownload() {
		
		BooksPreviewDisplay();
	}
}
