package com.nemanjam.ebook.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nemanjam.ebook.crud.ICategoryCRUD;
import com.nemanjam.ebook.entity.CategoryEntity;
import com.nemanjam.ebook.exception.ExceptionRemovingObject;

@Service
public class CategoryService {

	@Autowired
	private ICategoryCRUD categoryCrud;

	@Autowired
	private UserService userService;

	@Autowired
	private EBookService ebookService;
	
	public List<CategoryEntity> getAllCategories() {
		ArrayList<CategoryEntity> categories = new ArrayList<CategoryEntity>();		
		categoryCrud.findAll().forEach(categories::add);		
		return categories;
	}

	public CategoryEntity findCategory(Integer id) {
		return categoryCrud.findOne(id);
	}
	
	public void addCategory(CategoryEntity category) {
		categoryCrud.save(category);
	}

	public void updateCategory(Integer id, CategoryEntity category) {
		categoryCrud.save(category);
	}

	public void deleteCategory(Integer id) throws ExceptionRemovingObject {
		if (userService.hasUsersForCategory(id) || ebookService.hasEBookForCategory(id)) {
			throw new ExceptionRemovingObject("Can not remove category that is in use!");
		} else {
			categoryCrud.delete(id);
		}
	}

}
