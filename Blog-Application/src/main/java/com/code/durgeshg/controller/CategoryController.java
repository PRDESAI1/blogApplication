package com.code.durgeshg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.durgeshg.exception.ApiResponse;
import com.code.durgeshg.payloads.CategoryDto;
import com.code.durgeshg.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
	CategoryService categoryServive;
	
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto savedCategoryDto=categoryServive.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(savedCategoryDto,HttpStatus.CREATED);
	}
	
	
	@PutMapping("/{catId}")
	public ResponseEntity <CategoryDto> updateCategory(@PathVariable int catId,@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto savedCategoryDto=categoryServive.updateCategory(categoryDto, catId);
		
		
		return new ResponseEntity<CategoryDto>(savedCategoryDto,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{catId}")
	public ResponseEntity <ApiResponse> deleteCategory(@PathVariable int catId){
		 categoryServive.deleteCategory(catId);
		
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("deleted",true),HttpStatus.OK);
		
	}
	
	@GetMapping("/{catId}")
	public ResponseEntity <CategoryDto> getCategory(@PathVariable int catId){
		CategoryDto savedcategoryDto=categoryServive.getCategory(catId);
		
		
		return new ResponseEntity<CategoryDto>(savedcategoryDto,HttpStatus.OK);
		
	}
	
	@GetMapping("/")
	public ResponseEntity <List<CategoryDto>> getListCategory(){
		List<CategoryDto> savedcategoryDto=categoryServive.allCategory();
		
		
		return new ResponseEntity<List<CategoryDto>>(savedcategoryDto,HttpStatus.OK);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
