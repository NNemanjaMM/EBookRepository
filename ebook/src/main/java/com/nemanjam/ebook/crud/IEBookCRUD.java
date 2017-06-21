package com.nemanjam.ebook.crud;

import org.springframework.data.repository.CrudRepository;

import com.nemanjam.ebook.entity.EBookEntity;

public interface IEBookCRUD extends CrudRepository<EBookEntity, Integer> {

}
