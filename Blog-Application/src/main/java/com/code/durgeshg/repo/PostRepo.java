package com.code.durgeshg.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.code.durgeshg.entity.Category;
import com.code.durgeshg.entity.Post;
import com.code.durgeshg.entity.User;
@Repository
public interface PostRepo extends JpaRepository<Post,Integer>{

	Page<Post> findByUser(User user,Pageable pageable);
	List<Post> findByCategory(Category category);
	List<Post> findByTitleContaining(String title);//search like
	
	
	
}
