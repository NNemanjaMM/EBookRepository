package com.nemanjam.ebook.crud;

import org.springframework.data.repository.CrudRepository;

import com.nemanjam.ebook.model.entity.GeoLocation;

public interface IGeoLocationCRUD extends CrudRepository<GeoLocation, Integer> {

}
