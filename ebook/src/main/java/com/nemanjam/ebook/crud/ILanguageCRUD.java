package com.nemanjam.ebook.crud;

import org.springframework.data.repository.CrudRepository;

import com.nemanjam.ebook.entity.LanguageEntity;

public interface ILanguageCRUD extends CrudRepository<LanguageEntity, Integer> {
	
		
	
}
