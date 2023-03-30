package com.code.durgeshg.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import com.code.durgeshg.entity.Category;
import com.code.durgeshg.exception.RsourseNotFoundException;
import com.code.durgeshg.payloads.CategoryDto;
import com.code.durgeshg.repo.CategoryRepo;
import com.code.durgeshg.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
	CategoryRepo categoryRepo; 
    
    @Autowired
    ModelMapper ModelMapper;
	@Override
	public CategoryDto createCategory(CategoryDto CategoryDto) {
		Category cat=	changeCategoryDto(CategoryDto);
		Category savedCategory=categoryRepo.save(cat);
		return changeCategory(savedCategory);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto CategoryDto, int oldId) {
		Category category=categoryRepo.findById(oldId).orElseThrow(()->new RsourseNotFoundException("cateroryid","Id",oldId));
		category.setCategoryDescription(CategoryDto.getCategoryDescription());
		category.setCategoryName(CategoryDto.getCategoryName());
		
		Category savedCategory=categoryRepo.save(category);
		return changeCategory(savedCategory);
	}

	@Override
	public void deleteCategory(int CategoryId) {
		Category cat=categoryRepo.findById(CategoryId).orElseThrow(()->new RsourseNotFoundException("id","fieldName",CategoryId));
		categoryRepo.delete(cat);
	}

	@Override
	public CategoryDto getCategory(int CategoryId) {
		Category cat=categoryRepo.findById(CategoryId).orElseThrow(()->new RsourseNotFoundException("id","fieldName",CategoryId));
		return changeCategory(cat);
	}

	@Override
	public List<CategoryDto> allCategory() {
		List<Category> listOfCategory=categoryRepo.findAll();
		List<CategoryDto> returncat=listOfCategory.stream().map((e)->changeCategory(e)).collect(Collectors.toList());
		return returncat;
	}
	
	public Category changeCategoryDto(CategoryDto categoryDto) {
		Category Category=ModelMapper.map(categoryDto, Category.class);
		return Category;
	}
	public CategoryDto changeCategory(Category category) {
		CategoryDto categoryDto=ModelMapper.map(category, CategoryDto.class);
		return categoryDto;
	}

}
