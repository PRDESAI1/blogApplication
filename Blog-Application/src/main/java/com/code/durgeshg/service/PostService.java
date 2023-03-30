package com.code.durgeshg.service;

import java.util.List;

import com.code.durgeshg.entity.Category;
import com.code.durgeshg.entity.Post;
import com.code.durgeshg.payloads.PostDto;
import com.code.durgeshg.payloads.PostResponse;

public interface PostService {
//create post
	PostDto createPost(PostDto postDto,int userId,int categoryId);
	PostDto UpdatePost(PostDto postDto,int postid);
	void deletePost(int id);
	PostResponse getAllPost(int pageNumber ,int pageSixe ,String sortBy,String sortDir);
	PostDto getPostById(int PostId);
	List<PostDto> getPostByCategoryPost(int categoryId);
	PostResponse getPOstByUser(int userId,int pageNumber,int pageSize);
	List<PostDto> searchPost(String keyword);

}
