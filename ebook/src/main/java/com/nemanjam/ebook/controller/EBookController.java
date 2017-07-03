package com.nemanjam.ebook.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.nemanjam.ebook.entity.EBookDisplay;
import com.nemanjam.ebook.entity.db.CategoryEntity;
import com.nemanjam.ebook.entity.db.EBookEntity;
import com.nemanjam.ebook.entity.db.LanguageEntity;
import com.nemanjam.ebook.service.CategoryService;
import com.nemanjam.ebook.service.EBookService;
import com.nemanjam.ebook.service.LanguageService;
import com.nemanjam.ebook.service.StorageService;

@Controller
@SessionAttributes("sessionUser")
public class EBookController {
	
	@Autowired
	private EBookService eBookService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private LanguageService languageService;
	
	@Autowired
	private StorageService storageService;

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String BooksPreviewDisplayAll(ModelMap model) {
		
		model.put("selectBy", "All books");
		List<EBookEntity> books = eBookService.getAllEBooks();
		
		addBooksToModelDisplay(model, books);
		addCategoriesToModel(model);
		return "viewBooks";
	}

	@RequestMapping(value="/category", method=RequestMethod.GET)
	public String BooksPreviewDisplay(@RequestParam("categoryId") String categoryId, ModelMap model) {
		
		int id = Integer.parseInt(categoryId);
		CategoryEntity category = categoryService.findCategory(id);
		
		List<EBookEntity> books = eBookService.findEBooksForCategory(id);

		model.put("selectBy", category.getName());
		addBooksToModelDisplay(model, books);
		addCategoriesToModel(model);
		return "viewBooks";
	}

	@RequestMapping(value="/bookmanage", method=RequestMethod.GET)
	public String BooksManageDisplayAll(ModelMap model) {
		// REQUIRES PERMISSION

		model.put("selectBy", "All books");		

		addBooksToModel(model);
		addCategoriesToModel(model);
		return "viewBooksManage";
	}

	@RequestMapping(value="/bookmanagecategory", method=RequestMethod.GET)
	public String BooksManageDisplay(@RequestParam("categoryId") String categoryId, ModelMap model) {
		// REQUIRES PERMISSION

		int id = Integer.parseInt(categoryId);
		CategoryEntity category = categoryService.findCategory(id);
		
		List<EBookEntity> books = eBookService.findEBooksForCategory(id);
		Collections.sort(books, (EBookEntity b1, EBookEntity b2) -> b1.getTitle().compareTo(b2.getTitle()));

		model.put("books", books);
		model.put("selectBy", category.getName());		

		addLanguageToModel(model);
		addCategoriesToModel(model);
		return "viewBooksManage";
	}

	@RequestMapping(value="/bookupdate", method=RequestMethod.GET)
	public String BookUpdateDisplay(@RequestParam("bookId") String bookId, ModelMap model) {
		// REQUIRES PERMISSION
		
		int id = Integer.parseInt(bookId);		
		EBookEntity book = eBookService.findEBook(id);
		model.put("book", book);
		
		// vratiti kljigu koja se menja
		addLanguageToModel(model);
		addCategoriesToModel(model);
		return "viewBookUpdate";
	}

	@RequestMapping(value="/bookadd", method=RequestMethod.GET)
	public String BookAddUploadDisplay(ModelMap model) {
		// REQUIRES PERMISSION

		addCategoriesToModel(model);
		return "viewBookAddUpload";
	}

	@RequestMapping(value="/bookupload", method=RequestMethod.POST)
	public String BookAddInfoDisplay(@RequestParam("file") MultipartFile file, ModelMap model) {
		// REQUIRES PERMISSION
		
		String savedFileName = null;
		try {
			savedFileName = storageService.store(file);		
			if (savedFileName == null) {
				model.put("error", "File was not uploaded, try again. Check if file type is .pdf!");
				addCategoriesToModel(model);
				return "viewBookAddUpload";
			}
		} catch (Exception e) {
			model.put("error", "File was not uploaded, try again. Check if file type is .pdf!");
			addCategoriesToModel(model);
			return "viewBookAddUpload";
		}
		
		EBookEntity ebook = eBookService.getFileInfo(savedFileName);
		model.put("book", ebook);
		
		addLanguageToModel(model);
		addCategoriesToModel(model);
		return "viewBookAddInfo";
	}

	@RequestMapping(value="/bookadd", method=RequestMethod.POST)
	public String BookAdd(@ModelAttribute("book") EBookEntity book, BindingResult result, ModelMap model) {
		// REQUIRES PERMISSION

		if (result.hasErrors()) {
			StringBuilder errorBuilder = new StringBuilder();
			
			for (ObjectError objectError : result.getAllErrors()) {
				String error = objectError.getDefaultMessage();
				errorBuilder.append(error);
				errorBuilder.append("<br/>");
			}
			
			model.put("error", errorBuilder.toString());

			addCategoriesToModel(model);
			addLanguageToModel(model);
			return "viewBookAddInfo";
		}
		
		

		model.put("selectBy", "All books");		
		eBookService.addEBook(book);
		addBooksToModel(model);
		addCategoriesToModel(model);
		return "viewBooksManage";
	}

	@RequestMapping(value="/bookupdate", method=RequestMethod.POST)
	public String BookUpdate(@ModelAttribute("book") EBookEntity book, BindingResult result, ModelMap model) {
		// REQUIRES PERMISSION

		if (result.hasErrors()) {
			StringBuilder errorBuilder = new StringBuilder();
			
			for (ObjectError objectError : result.getAllErrors()) {
				String error = objectError.getDefaultMessage();
				errorBuilder.append(error);
				errorBuilder.append("<br/>");
			}
			
			model.put("error", errorBuilder.toString());

			addCategoriesToModel(model);
			addLanguageToModel(model);
			return "viewBookUpdate";			
		}
		
		eBookService.updateEBook(book);		

		model.put("selectBy", "All books");		
		addCategoriesToModel(model);
		addBooksToModel(model);
		return "viewBooksManage";
	}

	@RequestMapping(value="/bookdelete", method=RequestMethod.POST)
	public String BookDelete(@RequestParam("bookId") String bookId, ModelMap model) {
		// REQUIRES PERMISSION

		int id = Integer.parseInt(bookId);
		EBookEntity book = eBookService.findEBook(id);
		eBookService.deleteEBook(id);	
		storageService.deleteFile(book.getFilename());

		List<EBookEntity> books = eBookService.getAllEBooks();
		addBooksToModelDisplay(model, books);
		addCategoriesToModel(model);
		return "viewBooksManage";
	}	

	@RequestMapping(value="/bookdownload", method=RequestMethod.GET)
	public ResponseEntity<Resource> BookDownload(@RequestParam("bookId") String bookId, ModelMap model) {
		
		int id = Integer.parseInt(bookId);
		EBookEntity book = eBookService.findEBook(id);
		
		if (book == null) {	
			model.put("download_error", "Selected e-Book was not found");		
			return new ResponseEntity<Resource>(HttpStatus.NO_CONTENT);
		}
		
		//String downloadFileName = "udzbenik.pdf";
		//String fileName = "udzbenik.pdf";
		String downloadFileName = book.getTitle() + " (" + book.getAuthor() + ").pdf";
		String fileName = book.getFilename();
		
		Resource res = storageService.loadFile(fileName);
	    InputStreamResource resource = null;
	    long contentLength = 0;
		try {
			resource = new InputStreamResource(res.getInputStream());
		    contentLength = res.getFile().length();
		} catch (IOException e) {
			e.printStackTrace();
		}

	    return ResponseEntity.ok()
	            .contentLength(contentLength)
	            .contentType(MediaType.parseMediaType("application/octet-stream"))
	            .header("Content-disposition", "attachment; filename=" + downloadFileName)
	            .body(resource);
	}
	
	private void addCategoriesToModel(ModelMap model) {
		List<CategoryEntity> categories = categoryService.getAllCategories();
		Collections.sort(categories, (CategoryEntity c1, CategoryEntity c2) -> c1.getName().compareTo(c2.getName()));		
		model.put("categories", categories);		
	}
	
	private void addLanguageToModel(ModelMap model) {
		List<LanguageEntity> languages = languageService.getAllLanguages();
		Collections.sort(languages, (LanguageEntity l1, LanguageEntity l2) -> l1.getName().compareTo(l2.getName()));		
		model.put("languages", languages);		
	}
	
	private void addBooksToModelDisplay(ModelMap model, List<EBookEntity> list) {
		/*List<EBookDisplay> books = new ArrayList<EBookDisplay>();
		
		for (EBookEntity eBook : list) {
			EBookDisplay book = new EBookDisplay(eBook, "");
			books.add(book);
		}
		
		Collections.sort(books, (EBookDisplay b1, EBookDisplay b2) -> b1.getTitle().compareTo(b2.getTitle()));
		model.put("books", books);	
		*/		
		
		
		model.put("books", list);	
	}
	
	private void addBooksToModel(ModelMap model) {
		List<EBookEntity> books = eBookService.getAllEBooks();
		Collections.sort(books, (EBookEntity b1, EBookEntity b2) -> b1.getTitle().compareTo(b2.getTitle()));
		model.put("books", books);	
	}
}