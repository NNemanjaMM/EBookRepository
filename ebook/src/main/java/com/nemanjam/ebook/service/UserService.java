package com.nemanjam.ebook.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nemanjam.ebook.crud.IUserCRUD;
import com.nemanjam.ebook.entity.UserEntity;

@Service
public class UserService {

	@Autowired
	private IUserCRUD userCrud;
	
	public List<UserEntity> getAllUsers() {
		ArrayList<UserEntity> users = new ArrayList<UserEntity>();		
		userCrud.findAll().forEach(users::add);		
		return users;
	}

	public UserEntity findUser(Integer id) {
		return userCrud.findOne(id);
	}

	public List<UserEntity> findUserByUsername(String username) {
		return userCrud.findByUsername(username);
	}
	
	public void addUser(UserEntity user) {
		userCrud.save(user);
	}

	public void updateUser(Integer id, UserEntity user) {
		userCrud.save(user);
	}

	public void deleteUser(Integer id) {
		userCrud.delete(id);
	}

}
