package com.nemanjam.ebook.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nemanjam.ebook.crud.IEBookCRUD;
import com.nemanjam.ebook.entity.EBookEntity;

@Service
public class EBookService {

	@Autowired
	private IEBookCRUD ebookCrud;
	
	public List<EBookEntity> getAllEBooks() {
		ArrayList<EBookEntity> ebooks = new ArrayList<EBookEntity>();		
		ebookCrud.findAll().forEach(ebooks::add);		
		return ebooks;
	}

	public boolean hasEBookForCategory(int id) {
		if (ebookCrud.findByCategoryId(id).size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public EBookEntity findEBook(Integer id) {
		return ebookCrud.findOne(id);
	}

	public void addEBook(EBookEntity ebook) {
		ebookCrud.save(ebook);
	}

	public void updateEBook(Integer id, EBookEntity ebook) {
		ebookCrud.save(ebook);
	}

	public void deleteEBook(Integer id) {
		ebookCrud.delete(id);
	}
	
}
