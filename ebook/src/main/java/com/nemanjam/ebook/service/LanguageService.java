package com.nemanjam.ebook.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nemanjam.ebook.crud.ILanguageCRUD;
import com.nemanjam.ebook.entity.LanguageEntity;

@Service
public class LanguageService {

	@Autowired
	private ILanguageCRUD languageCrud;
	
	public List<LanguageEntity> getAllLanguages() {
		ArrayList<LanguageEntity> languages = new ArrayList<LanguageEntity>();		
		languageCrud.findAll().forEach(languages::add);		
		return languages;
	}

	public LanguageEntity findLanguage(Integer id) {
		return languageCrud.findOne(id);
	}
	
	public void addLanguage(LanguageEntity language) {
		languageCrud.save(language);
	}

	public void updateLanguage(LanguageEntity language) {
		languageCrud.save(language);
	}

	public void deleteLanguage(Integer id) {
		languageCrud.delete(id);
	}
	
}
