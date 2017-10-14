package com.tira.restaurants.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.tira.restaurants.domain.Category;

public interface CategoryService {
	List<Category> getAll();

	Page<Category> getByFilter(String searchText, Pageable pageable);

	void addCategory(Category category);

	Category editCategory(Long id, String name);

	void deleteCategory(Long id);

	Category getCategory(Long id);
}
