package com.tira.restaurants.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;
import com.tira.restaurants.domain.Category;
import com.tira.restaurants.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public List<Category> getAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Page<Category> getByFilter(String searchText, Pageable pageable) {
		if(searchText!=null) {
			return categoryRepository.getCategoriesByFilter(searchText,pageable);
		}
		else return categoryRepository.findAll(pageable);
		
	}

	@Override
	public void addCategory(Category category) {
		categoryRepository.save(category);
		
	}

	@Override
	public Category editCategory(Long id, String name) {
		Category category = categoryRepository.findOne(id);
		if(category == null) {
			return null;
		}
		category.setName(name);
		addCategory(category);
		return category;
	}

	@Override
	public void deleteCategory(Long id) {
		categoryRepository.delete(id);
		
	}

	@Override
	public Category getCategory(Long id) {
		return categoryRepository.findOne(id);
	}

	@Override
	public Set<Category> getCategories(Set<Long> categories) {
		return Sets.newHashSet(categoryRepository.findAll(categories));
	}

}
