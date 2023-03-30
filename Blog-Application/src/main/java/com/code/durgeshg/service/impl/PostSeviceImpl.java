package com.code.durgeshg.service.impl;


import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.code.durgeshg.entity.Category;
import com.code.durgeshg.entity.Post;
import com.code.durgeshg.entity.User;
import com.code.durgeshg.exception.RsourseNotFoundException;
import com.code.durgeshg.payloads.PostDto;
import com.code.durgeshg.payloads.PostResponse;
import com.code.durgeshg.repo.CategoryRepo;
import com.code.durgeshg.repo.PostRepo;
import com.code.durgeshg.repo.UserRepo;
import com.code.durgeshg.service.PostService;
@Service
public class PostSeviceImpl implements PostService {
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	ModelMapper modelmapper;
	
	@Autowired
	CategoryRepo categoryRepo;
	
	@Autowired
	UserRepo userRepo;

	@Override
	public PostDto createPost(PostDto postDto,int userId,int categoryId) {
		
		User user =this.userRepo.findById(userId).orElseThrow(()->new RsourseNotFoundException("user","User Id",userId));
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new RsourseNotFoundException("category","categoryId",categoryId));
		
		Post post=modelmapper.map(postDto,Post.class);
		post.setImage("default.img");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
	Post newPost=	postRepo.save(post);
	
	PostDto NewpostDto=modelmapper.map(newPost, PostDto.class);
		
		
		return NewpostDto;
	}

	@Override
	public PostDto UpdatePost(PostDto postDto, int id) {
	Post post=	postRepo.findById(id).orElseThrow(()->new RsourseNotFoundException("user","User Id",id));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImage(postDto.getImage());
		
		Post updatedPost= postRepo.save(post);
		
		PostDto addedPost=modelmapper.map(updatedPost, PostDto.class);
		
		return addedPost;
	}

	@Override
	public void deletePost(int id) {
	Post post=	postRepo.findById(id).orElseThrow(()->new RsourseNotFoundException("user","User Id",id));
		postRepo.delete(post);

	}

	@Override
	public PostResponse getAllPost(int pageNumber ,int pageSixe,String sortBy,String sortDir) {
		
		Sort sort=null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort=Sort.by(sortBy).ascending();
		}else {
			sort=Sort.by(sortBy).descending();
		}
		
	//Pageable p= PageRequest.of(pageNumber, pageSixe, Sort.by(sortBy).ascending());
		
		Pageable p= PageRequest.of(pageNumber, pageSixe,sort );
	Page<Post> pagePost=postRepo.findAll(p);
	
	List <Post> getPost=pagePost.getContent();
	
	
	List<PostDto> getPostDto=getPost.stream().map((post)->modelmapper.map(post, PostDto.class)).collect(Collectors.toList());
	
	//get INFO FORM page
	PostResponse postRespo=new PostResponse();
	postRespo.setPageNumber(pagePost.getNumber());
	postRespo.setContent(getPostDto);
	postRespo.setPageSize(pagePost.getSize());
	postRespo.setTotalElement(pagePost.getTotalElements());
	postRespo.setTotalPage(pagePost.getTotalPages());
	postRespo.setLastPage(pagePost.isLast());
		return postRespo;
	}

	@Override
	public PostDto getPostById(int postId) {
		Post post=postRepo.findById(postId).orElseThrow(()->new RsourseNotFoundException("postId","postId",postId) );
		PostDto postDto=modelmapper.map(post, PostDto.class);
		return postDto;
	}

	@Override
	public List<PostDto> getPostByCategoryPost(int categoryId) {
		 Category cat=categoryRepo.findById(categoryId).orElseThrow(()->new RsourseNotFoundException("category","categoryId",categoryId));
		List<Post> post=postRepo.findByCategory(cat);
		
	List<PostDto> getPostDto=	post.stream().
			map((posts) -> modelmapper.map(posts,PostDto.class) ).
			collect(Collectors.toList());
		 
		 return getPostDto;
	}

	@Override
	public PostResponse getPOstByUser(int userId,int pageNumber,int pageSize) {
	User user=	userRepo.findById(userId).orElseThrow(()->new RsourseNotFoundException("user","userId",userId) );
	
	
	Pageable p=PageRequest.of(pageNumber, pageSize);
	//pass pegeable in user method in repo;
	Page<Post> page=postRepo.findByUser(user,p);
	List<Post> post=page.getContent();
	List<PostDto> getPostByUser=post.
			stream().
			map((posts)->modelmapper.map(posts, PostDto.class)).collect(Collectors.toList());
	
	PostResponse postResponse=new PostResponse ();
	postResponse.setContent(getPostByUser);
	postResponse.setPageNumber(page.getNumber());
	postResponse.setPageSize(page.getSize());
	postResponse.setTotalPage(page.getTotalPages());
	postResponse.setTotalElement(page.getTotalElements());
	postResponse.setLastPage(page.isLast());
		return postResponse;
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		List<Post> posts=postRepo.findByTitleContaining(keyword);
		
	List<PostDto> poDto=	posts.stream().
			map((po)->modelmapper.map(po,PostDto.class)).
			collect(Collectors.toList());
	
	
		
		
		return poDto;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	

}
