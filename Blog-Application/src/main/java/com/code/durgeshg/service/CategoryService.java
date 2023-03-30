package com.code.durgeshg.service;

import java.util.List;

import com.code.durgeshg.payloads.CategoryDto;

public interface CategoryService {

	CategoryDto createCategory(CategoryDto CategoryDto);
	CategoryDto updateCategory(CategoryDto CategoryDto,int oldId);
	void deleteCategory(int CategoryId);
	CategoryDto getCategory(int CategoryId);
	List<CategoryDto> allCategory();
	
}
