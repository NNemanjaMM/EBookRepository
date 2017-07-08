package com.nemanjam.ebook.crud;

import org.springframework.data.repository.CrudRepository;

import com.nemanjam.ebook.model.entity.GeoBook;

public interface IGeoBookCRUD extends CrudRepository<GeoBook, Integer> {
	
}
