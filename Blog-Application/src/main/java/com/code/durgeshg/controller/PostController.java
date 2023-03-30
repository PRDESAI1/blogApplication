package com.code.durgeshg.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.code.durgeshg.config.AppConstants;
import com.code.durgeshg.exception.ApiResponse;
import com.code.durgeshg.payloads.PostDto;
import com.code.durgeshg.payloads.PostResponse;
import com.code.durgeshg.service.FileService;
import com.code.durgeshg.service.PostService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class PostController {
	@Autowired
	private PostService postservice;
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	@PostMapping("/user/{userId}/categoryId/{categoryId}/post")
	public ResponseEntity<PostDto>  createPost(@RequestBody PostDto PostDto,@PathVariable int userId
			,@PathVariable int categoryId ) {
		
		
		PostDto addedPost =postservice.createPost(PostDto, userId, categoryId);
		return new ResponseEntity<>(addedPost,HttpStatus.CREATED);
	}
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<PostResponse>  GetPostByUser(@PathVariable int userId ,
			@RequestParam (value="pageNumber",defaultValue=AppConstants.PAGE_NUMBER,required=false)int pageNumber,
			@RequestParam (value="pageSize",defaultValue=AppConstants.PAGE_SIZE,required=false)int pageSize
	) {
		
		
		PostResponse getPost =postservice.getPOstByUser(userId,pageNumber,pageSize);
		return new ResponseEntity<>(getPost,HttpStatus.OK);
	}
	
	@GetMapping("/category/{catogoryId}/posts")
	public ResponseEntity<List<PostDto>>  GetPostByCategory(@PathVariable int catogoryId ) {
		
		
		List<PostDto> getPost =postservice.getPostByCategoryPost(catogoryId);
		return new ResponseEntity<>(getPost,HttpStatus.OK);
	}
	
	//pagenumber== current page number start with 0
	//pageSize== how much records you want
	@GetMapping("/posts")
	public ResponseEntity<PostResponse>  GetAllPost(
			@RequestParam (value="pageNumber",defaultValue=AppConstants.PAGE_NUMBER,required=false) int pageNumber,
			@RequestParam (value="pageSize",defaultValue=AppConstants.PAGE_SIZE,required=false) int pageSize,
			@RequestParam(value ="sortBy", defaultValue=AppConstants.SORT_BY,required=false) String sortBy,
			@RequestParam(value="sortDir" ,defaultValue=AppConstants.SORT_DIR,required=false) String sortDir
			) {
		
		
		PostResponse postResponse =postservice.getAllPost(pageNumber,pageSize,sortBy,sortDir);
		return new ResponseEntity<>(postResponse,HttpStatus.OK);
	}
	
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse>  deletePost(@PathVariable int postId) {
		
		
		postservice.deletePost(postId);
		return new ResponseEntity<>(new ApiResponse("post is deleted",true),HttpStatus.OK);
	}
	
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto>  updatePost(@RequestBody PostDto post,@PathVariable int postId) {
		
		
	PostDto updatedPost =	postservice.UpdatePost(post, postId);
		return new ResponseEntity<>(updatedPost,HttpStatus.OK);
	}
	
	
	
	//search
	@GetMapping("/posts/serch/{keyword}")
	public ResponseEntity<List<PostDto>> serchPost(@PathVariable String keyword){
	List<PostDto> poDto=	postservice.searchPost(keyword);
	
	return new ResponseEntity<>(poDto,HttpStatus.OK);
	}
	
	
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> fileUpload(@RequestParam ("image")MultipartFile image,
			@PathVariable int postId) throws IOException{
	String fileName =	fileService.uploadImage(path,image );
	
	PostDto postDto =postservice.getPostById(postId);
	postDto.setImage(fileName);
	
	PostDto updatedPost=postservice.UpdatePost(postDto, postId);
	
	return new ResponseEntity<>(updatedPost,HttpStatus.OK);
	}
	
	
	
	//serve File
	@GetMapping(value="/profiles/{imageName}",produces=MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable ("imageName") String imageName,
			HttpServletResponse response ) throws IOException{
		InputStream resourse=fileService.getResourse(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resourse, response.getOutputStream());
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
