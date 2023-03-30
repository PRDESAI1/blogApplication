package com.code.durgeshg.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.code.durgeshg.entity.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer> {

}
