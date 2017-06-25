package com.nemanjam.ebook.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	@Size(max = 30, min = 1, message = "User's first name must contain between 1 and 30 characters!")
	private String firstName;

	@Column(nullable = false)
	@Size(max = 30, min = 1, message = "User's last name must contain between 1 and 30 characters!")
	private String lastName;

	//@IsUsernameUnique
	@Column(nullable = false)
	@Size(max = 10, min = 1, message = "User's username must contain between 1 and 10 characters!")
	private String username;

	@Column(nullable = false)
	@Size(max = 10, min = 1, message = "User's password must contain between 1 and 10 characters!")
	private String password;

	@Column(nullable = false)
	@Size(max = 30, min = 1)
	private String type;

	
	@ManyToOne
	private CategoryEntity category;    
	
	
	public UserEntity() {
		
	}
	
	public UserEntity(Integer id, String firstName, String lastName,
			String username, String password, String type,
			CategoryEntity category) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.type = type;
		this.category = category;
	}

	public Integer getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getType() {
		return type;
	}

	public CategoryEntity getCategory() {
		return category;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

}
