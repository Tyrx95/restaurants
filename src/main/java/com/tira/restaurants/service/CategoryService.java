package com.tira.restaurants.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tira.restaurants.domain.Category;

public interface CategoryService {
	List<Category> getAll();

	Page<Category> getByFilter(String searchText, Pageable pageable);

	void addCategory(Category category);

	Category editCategory(Long id, String name);

	void deleteCategory(Long id);

	Category getCategory(Long id);

	Set<Category> getCategories(Set<Long> categories);
}
