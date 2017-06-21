package com.nemanjam.ebook.crud;

import org.springframework.data.repository.CrudRepository;

import com.nemanjam.ebook.entity.CategoryEntity;

public interface ICategoryCRUD extends CrudRepository<CategoryEntity, Integer> {

}
