package com.nemanjam.ebook.crud;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.nemanjam.ebook.model.entity.UserEntity;

public interface IUserCRUD extends CrudRepository<UserEntity, Integer> {

	public List<UserEntity> findByUsername(String username);

	public List<UserEntity> findByCategoryId(int id);
	
}
