package com.nemanjam.ebook.crud;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.nemanjam.ebook.entity.db.EBookEntity;

public interface IEBookCRUD extends CrudRepository<EBookEntity, Integer> {

	public List<EBookEntity> findByCategoryId(int id);

	public List<EBookEntity> findByFilename(String filename);
	
}
